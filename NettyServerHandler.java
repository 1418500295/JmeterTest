package com.im.socket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(("Server,channelActive"));
        ByteBuf byteBuf = Unpooled.copiedBuffer("你好啊", StandardCharsets.UTF_8);
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] buffer = new byte[buf.readableBytes()];
        buf.readBytes(buffer);
        String message = new String(buffer, StandardCharsets.UTF_8);
        System.out.println(("Server,接收到客户端发来的消息:" + message));

        Thread.sleep(2000);

        ByteBuf byteBuf = Unpooled.copiedBuffer(message, StandardCharsets.UTF_8);
        ctx.writeAndFlush(byteBuf);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(("Server,exceptionCaught"));
        cause.printStackTrace();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(("Server,channelInactive"));
    }

}
