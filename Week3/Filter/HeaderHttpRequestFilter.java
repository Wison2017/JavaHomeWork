package netty.Filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class HeaderHttpRequestFilter implements HttpRequestFilter{
    public void filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        fullHttpRequest.headers().set("AddedBy", "RequestFilter");
    }
}
