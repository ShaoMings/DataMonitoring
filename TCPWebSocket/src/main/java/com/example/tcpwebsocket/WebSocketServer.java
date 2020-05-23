package com.example.tcpwebsocket;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/index")
@Component
public class WebSocketServer extends Thread {


    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    Session session;
    int count = 0;
    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入待监听的端口:");
        int port = scanner.nextInt();
        WebSocketServer server = new WebSocketServer(port, session);
        server.start();
    }


    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message) throws IOException {
        System.out.println("来自客户端的消息:" + message);

    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
    }

    /**
     * @param message
     * @throws IOException
     */


    //给客户端传递消息
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);

    }


    ServerSocket server = null;
    Socket socket = null;

    public WebSocketServer() {

    }

    public WebSocketServer(int port, Session session) {
        try {
            this.session = session;
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                socket = server.accept();
                if (socket != null) {
                    InputStream in = socket.getInputStream();
                    int len = 0;
                    byte[] buf = new byte[1024];
                    while ((len = in.read(buf)) != -1) {
                        sendMessage(new String(buf, 0, len));
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
