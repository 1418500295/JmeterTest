package com.im.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyClient {
    private String mHost;

    private int mPort;

    private NettyClientHandler mClientHandler;

    private ChannelFuture mChannelFuture;

    public NettyClient(String host, int port) {
        this.mHost = host;
        this.mPort = port;
    }

    public void connect() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            mClientHandler = new NettyClientHandler();
            b.group(workerGroup).channel(NioSocketChannel.class)
                    // KeepAlive
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    // Handler
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            //心跳检测机制需放在第一行
                            channel.pipeline().addLast(new IdleStateHandler(3, 3, 6));
                            channel.pipeline().addLast(mClientHandler);
//                            channel.pipeline().addLast("decoder",new StringDecoder());
//                            channel.pipeline().addLast("encoder",new StringEncoder());
                        }
                    });
            mChannelFuture = b.connect(mHost, mPort).sync();
            if (mChannelFuture.isSuccess()) {
                System.out.println(("Client,连接服务端成功"));
            }
            mChannelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }


}
