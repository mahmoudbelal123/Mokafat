package com.google.android.gms.measurement.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes.dex */
final class zzfm extends SSLSocket {
    private final SSLSocket zzaun;

    zzfm(zzfl zzflVar, SSLSocket sSLSocket) {
        this.zzaun = sSLSocket;
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setEnabledProtocols(String[] strArr) {
        if (strArr != null && Arrays.asList(strArr).contains("SSLv3")) {
            ArrayList arrayList = new ArrayList(Arrays.asList(this.zzaun.getEnabledProtocols()));
            if (arrayList.size() > 1) {
                arrayList.remove("SSLv3");
            }
            strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        this.zzaun.setEnabledProtocols(strArr);
    }

    @Override // javax.net.ssl.SSLSocket
    public final String[] getSupportedCipherSuites() {
        return this.zzaun.getSupportedCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocket
    public final String[] getEnabledCipherSuites() {
        return this.zzaun.getEnabledCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setEnabledCipherSuites(String[] strArr) {
        this.zzaun.setEnabledCipherSuites(strArr);
    }

    @Override // javax.net.ssl.SSLSocket
    public final String[] getSupportedProtocols() {
        return this.zzaun.getSupportedProtocols();
    }

    @Override // javax.net.ssl.SSLSocket
    public final String[] getEnabledProtocols() {
        return this.zzaun.getEnabledProtocols();
    }

    @Override // javax.net.ssl.SSLSocket
    public final SSLSession getSession() {
        return this.zzaun.getSession();
    }

    @Override // javax.net.ssl.SSLSocket
    public final void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.zzaun.addHandshakeCompletedListener(handshakeCompletedListener);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.zzaun.removeHandshakeCompletedListener(handshakeCompletedListener);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void startHandshake() throws IOException {
        this.zzaun.startHandshake();
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setUseClientMode(boolean z) {
        this.zzaun.setUseClientMode(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public final boolean getUseClientMode() {
        return this.zzaun.getUseClientMode();
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setNeedClientAuth(boolean z) {
        this.zzaun.setNeedClientAuth(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setWantClientAuth(boolean z) {
        this.zzaun.setWantClientAuth(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public final boolean getNeedClientAuth() {
        return this.zzaun.getNeedClientAuth();
    }

    @Override // javax.net.ssl.SSLSocket
    public final boolean getWantClientAuth() {
        return this.zzaun.getWantClientAuth();
    }

    @Override // javax.net.ssl.SSLSocket
    public final void setEnableSessionCreation(boolean z) {
        this.zzaun.setEnableSessionCreation(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public final boolean getEnableSessionCreation() {
        return this.zzaun.getEnableSessionCreation();
    }

    @Override // java.net.Socket
    public final void bind(SocketAddress socketAddress) throws IOException {
        this.zzaun.bind(socketAddress);
    }

    @Override // java.net.Socket, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() throws IOException {
        this.zzaun.close();
    }

    @Override // java.net.Socket
    public final void connect(SocketAddress socketAddress) throws IOException {
        this.zzaun.connect(socketAddress);
    }

    @Override // java.net.Socket
    public final void connect(SocketAddress socketAddress, int i) throws IOException {
        this.zzaun.connect(socketAddress, i);
    }

    @Override // java.net.Socket
    public final SocketChannel getChannel() {
        return this.zzaun.getChannel();
    }

    @Override // java.net.Socket
    public final InetAddress getInetAddress() {
        return this.zzaun.getInetAddress();
    }

    @Override // java.net.Socket
    public final InputStream getInputStream() throws IOException {
        return this.zzaun.getInputStream();
    }

    @Override // java.net.Socket
    public final boolean getKeepAlive() throws SocketException {
        return this.zzaun.getKeepAlive();
    }

    @Override // java.net.Socket
    public final InetAddress getLocalAddress() {
        return this.zzaun.getLocalAddress();
    }

    @Override // java.net.Socket
    public final int getLocalPort() {
        return this.zzaun.getLocalPort();
    }

    @Override // java.net.Socket
    public final SocketAddress getLocalSocketAddress() {
        return this.zzaun.getLocalSocketAddress();
    }

    @Override // java.net.Socket
    public final boolean getOOBInline() throws SocketException {
        return this.zzaun.getOOBInline();
    }

    @Override // java.net.Socket
    public final OutputStream getOutputStream() throws IOException {
        return this.zzaun.getOutputStream();
    }

    @Override // java.net.Socket
    public final int getPort() {
        return this.zzaun.getPort();
    }

    @Override // java.net.Socket
    public final synchronized int getReceiveBufferSize() throws SocketException {
        return this.zzaun.getReceiveBufferSize();
    }

    @Override // java.net.Socket
    public final SocketAddress getRemoteSocketAddress() {
        return this.zzaun.getRemoteSocketAddress();
    }

    @Override // java.net.Socket
    public final boolean getReuseAddress() throws SocketException {
        return this.zzaun.getReuseAddress();
    }

    @Override // java.net.Socket
    public final synchronized int getSendBufferSize() throws SocketException {
        return this.zzaun.getSendBufferSize();
    }

    @Override // java.net.Socket
    public final int getSoLinger() throws SocketException {
        return this.zzaun.getSoLinger();
    }

    @Override // java.net.Socket
    public final synchronized int getSoTimeout() throws SocketException {
        return this.zzaun.getSoTimeout();
    }

    @Override // java.net.Socket
    public final boolean getTcpNoDelay() throws SocketException {
        return this.zzaun.getTcpNoDelay();
    }

    @Override // java.net.Socket
    public final int getTrafficClass() throws SocketException {
        return this.zzaun.getTrafficClass();
    }

    @Override // java.net.Socket
    public final boolean isBound() {
        return this.zzaun.isBound();
    }

    @Override // java.net.Socket
    public final boolean isClosed() {
        return this.zzaun.isClosed();
    }

    @Override // java.net.Socket
    public final boolean isConnected() {
        return this.zzaun.isConnected();
    }

    @Override // java.net.Socket
    public final boolean isInputShutdown() {
        return this.zzaun.isInputShutdown();
    }

    @Override // java.net.Socket
    public final boolean isOutputShutdown() {
        return this.zzaun.isOutputShutdown();
    }

    @Override // java.net.Socket
    public final void sendUrgentData(int i) throws IOException {
        this.zzaun.sendUrgentData(i);
    }

    @Override // java.net.Socket
    public final void setKeepAlive(boolean z) throws SocketException {
        this.zzaun.setKeepAlive(z);
    }

    @Override // java.net.Socket
    public final void setOOBInline(boolean z) throws SocketException {
        this.zzaun.setOOBInline(z);
    }

    @Override // java.net.Socket
    public final void setPerformancePreferences(int i, int i2, int i3) {
        this.zzaun.setPerformancePreferences(i, i2, i3);
    }

    @Override // java.net.Socket
    public final synchronized void setReceiveBufferSize(int i) throws SocketException {
        this.zzaun.setReceiveBufferSize(i);
    }

    @Override // java.net.Socket
    public final void setReuseAddress(boolean z) throws SocketException {
        this.zzaun.setReuseAddress(z);
    }

    @Override // java.net.Socket
    public final synchronized void setSendBufferSize(int i) throws SocketException {
        this.zzaun.setSendBufferSize(i);
    }

    @Override // java.net.Socket
    public final void setSoLinger(boolean z, int i) throws SocketException {
        this.zzaun.setSoLinger(z, i);
    }

    @Override // java.net.Socket
    public final synchronized void setSoTimeout(int i) throws SocketException {
        this.zzaun.setSoTimeout(i);
    }

    @Override // java.net.Socket
    public final void setTcpNoDelay(boolean z) throws SocketException {
        this.zzaun.setTcpNoDelay(z);
    }

    @Override // java.net.Socket
    public final void setTrafficClass(int i) throws SocketException {
        this.zzaun.setTrafficClass(i);
    }

    @Override // java.net.Socket
    public final void shutdownInput() throws IOException {
        this.zzaun.shutdownInput();
    }

    @Override // java.net.Socket
    public final void shutdownOutput() throws IOException {
        this.zzaun.shutdownOutput();
    }

    @Override // javax.net.ssl.SSLSocket, java.net.Socket
    public final String toString() {
        return this.zzaun.toString();
    }

    public final boolean equals(Object obj) {
        return this.zzaun.equals(obj);
    }
}
