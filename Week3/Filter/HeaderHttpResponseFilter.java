package netty.Filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFilter implements HttpResponseFilter {
    public void filter(FullHttpResponse response) {
        response.headers().set("AddedBy", "ResponseFilter");
    }
}
