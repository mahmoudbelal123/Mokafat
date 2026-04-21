package rx.internal.util.unsafe;

import kotlin.jvm.internal.LongCompanionObject;
import rx.internal.util.SuppressAnimalSniffer;

/* JADX INFO: loaded from: classes.dex */
@SuppressAnimalSniffer
public class MpmcArrayQueue<E> extends MpmcArrayQueueConsumerField<E> {
    long p30;
    long p31;
    long p32;
    long p33;
    long p34;
    long p35;
    long p36;
    long p37;
    long p40;
    long p41;
    long p42;
    long p43;
    long p44;
    long p45;
    long p46;

    public MpmcArrayQueue(int capacity) {
        super(Math.max(2, capacity));
    }

    @Override // java.util.Queue, rx.internal.util.unsafe.MessagePassingQueue
    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        long capacity = this.mask + 1;
        long[] lSequenceBuffer = this.sequenceBuffer;
        long cIndex = LongCompanionObject.MAX_VALUE;
        while (true) {
            long cIndex2 = cIndex;
            long currentProducerIndex = lvProducerIndex();
            long seqOffset = calcSequenceOffset(currentProducerIndex);
            long seq = lvSequence(lSequenceBuffer, seqOffset);
            long delta = seq - currentProducerIndex;
            if (delta == 0) {
                long seq2 = currentProducerIndex + 1;
                if (casProducerIndex(currentProducerIndex, seq2)) {
                    long elementOffset = calcElementOffset(currentProducerIndex);
                    spElement(elementOffset, e);
                    soSequence(lSequenceBuffer, seqOffset, currentProducerIndex + 1);
                    return true;
                }
            } else if (delta < 0 && currentProducerIndex - capacity <= cIndex2) {
                long j = currentProducerIndex - capacity;
                long jLvConsumerIndex = lvConsumerIndex();
                cIndex2 = jLvConsumerIndex;
                if (j <= jLvConsumerIndex) {
                    return false;
                }
            }
            cIndex = cIndex2;
        }
    }

    @Override // java.util.Queue, rx.internal.util.unsafe.MessagePassingQueue
    public E poll() {
        long[] lSequenceBuffer = this.sequenceBuffer;
        long seq = -1;
        while (true) {
            long pIndex = seq;
            long currentConsumerIndex = lvConsumerIndex();
            long seqOffset = calcSequenceOffset(currentConsumerIndex);
            long seq2 = lvSequence(lSequenceBuffer, seqOffset);
            long delta = seq2 - (currentConsumerIndex + 1);
            if (delta == 0) {
                if (casConsumerIndex(currentConsumerIndex, currentConsumerIndex + 1)) {
                    long offset = calcElementOffset(currentConsumerIndex);
                    E e = lpElement(offset);
                    spElement(offset, null);
                    soSequence(lSequenceBuffer, seqOffset, currentConsumerIndex + this.mask + 1);
                    return e;
                }
            } else if (delta < 0 && currentConsumerIndex >= pIndex) {
                long pIndex2 = lvProducerIndex();
                if (currentConsumerIndex == pIndex2) {
                    return null;
                }
                seq = pIndex2;
            }
            seq = pIndex;
        }
    }

    @Override // java.util.Queue, rx.internal.util.unsafe.MessagePassingQueue
    public E peek() {
        long currConsumerIndex;
        E e;
        do {
            currConsumerIndex = lvConsumerIndex();
            e = lpElement(calcElementOffset(currConsumerIndex));
            if (e != null) {
                break;
            }
        } while (currConsumerIndex != lvProducerIndex());
        return e;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, rx.internal.util.unsafe.MessagePassingQueue
    public int size() {
        long before;
        long currentProducerIndex;
        long after = lvConsumerIndex();
        do {
            before = after;
            currentProducerIndex = lvProducerIndex();
            after = lvConsumerIndex();
        } while (before != after);
        return (int) (currentProducerIndex - after);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, rx.internal.util.unsafe.MessagePassingQueue
    public boolean isEmpty() {
        return lvConsumerIndex() == lvProducerIndex();
    }
}
