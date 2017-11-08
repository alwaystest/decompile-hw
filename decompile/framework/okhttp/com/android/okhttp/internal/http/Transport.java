package com.android.okhttp.internal.http;

import com.android.okhttp.Request;
import com.android.okhttp.Response;
import com.android.okhttp.Response.Builder;
import com.android.okhttp.ResponseBody;
import com.android.okhttp.okio.Sink;
import java.io.IOException;

public interface Transport {
    public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;

    boolean canReuseConnection();

    Sink createRequestBody(Request request, long j) throws IOException;

    void disconnect(HttpEngine httpEngine) throws IOException;

    void finishRequest() throws IOException;

    ResponseBody openResponseBody(Response response) throws IOException;

    Builder readResponseHeaders() throws IOException;

    void releaseConnectionOnIdle() throws IOException;

    void writeRequestBody(RetryableSink retryableSink) throws IOException;

    void writeRequestHeaders(Request request) throws IOException;
}
