package netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;
import netty.Filter.HeaderHttpRequestFilter;
import netty.Filter.HeaderHttpResponseFilter;
import netty.Filter.HttpRequestFilter;
import netty.Filter.HttpResponseFilter;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpHandler extends ChannelInboundHandlerAdapter {
    private HttpRequestFilter requestFilter = new HeaderHttpRequestFilter(); // 必做题第二题: 添加RequestFilter
    private HttpResponseFilter responseFilter = new HeaderHttpResponseFilter(); // 必做题第二题: 添加ResponseFilter
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            handlerTest(fullHttpRequest, ctx);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private void handlerTest(FullHttpRequest request, ChannelHandlerContext ctx) {
        FullHttpResponse response = null;
        try {
            requestFilter.filter(request, ctx); // 必做题第二题: 添加RequestFilter
            String value = HttpServerHelper.getResponseContent(); // 必做题第一题：在netty里面向后台服务发起请求
            System.out.println(Thread.currentThread().getName() + "  value: " + value);
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
            responseFilter.filter(response); // 必做题第二题: 添加ResponseFilter
        } catch (Exception ex) {
            System.out.println("处理出错: " + ex.getMessage());
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (request != null) {
                if (!HttpUtil.isKeepAlive(request)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
                ctx.flush();
            }
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
