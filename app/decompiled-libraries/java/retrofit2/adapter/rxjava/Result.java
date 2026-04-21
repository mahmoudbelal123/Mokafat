package retrofit2.adapter.rxjava;

import retrofit2.Response;

/* JADX INFO: loaded from: classes.dex */
public final class Result<T> {
    private final Throwable error;
    private final Response<T> response;

    public static <T> Result<T> error(Throwable error) {
        if (error == null) {
            throw new NullPointerException("error == null");
        }
        return new Result<>(null, error);
    }

    public static <T> Result<T> response(Response<T> response) {
        if (response == null) {
            throw new NullPointerException("response == null");
        }
        return new Result<>(response, null);
    }

    private Result(Response<T> response, Throwable error) {
        this.response = response;
        this.error = error;
    }

    public Response<T> response() {
        return this.response;
    }

    public Throwable error() {
        return this.error;
    }

    public boolean isError() {
        return this.error != null;
    }
}
