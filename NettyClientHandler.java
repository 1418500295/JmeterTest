package com.im.socket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    public static void heartBeat(ChannelHandlerContext channelHandlerContext){
        ScheduledExecutorService  service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                ByteBuf byteBuf = Unpooled.copiedBuffer((LocalDateTime.now()+"心跳检测\n").getBytes());
                channelHandlerContext.writeAndFlush(byteBuf);
            }
        },0,5, TimeUnit.SECONDS);
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(("Client,channelActive"));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] buffer = new byte[buf.readableBytes()];
        buf.readBytes(buffer);
        String message = new String(buffer, StandardCharsets.UTF_8);
        System.out.println(("Client,接收到服务端发来的消息:" + message));

        Thread.sleep(2000);
        ByteBuf byteBuf = Unpooled.copiedBuffer(Integer.toString(new Random().nextInt(100000-1)+1), StandardCharsets.UTF_8);
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(("Client,exceptionCaught"));
        cause.printStackTrace();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(("Client,channelInactive"));
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event = (IdleStateEvent) evt;
        System.out.println(("Client,Idle:" + event.state()));
        heartBeat(ctx);

        // switch (event.state()) {
        //     case READER_IDLE:
        //         break;
        //     case WRITER_IDLE:
        //         ByteBuf byteBuf = Unpooled.copiedBuffer("***心跳检测***", StandardCharsets.UTF_8);
        //         ctx.writeAndFlush(byteBuf);
        //         break;
        //     case ALL_IDLE:
        //         break;
        //     default:
        //         super.userEventTriggered(ctx, evt);
        //         break;
        // }
    }



}
