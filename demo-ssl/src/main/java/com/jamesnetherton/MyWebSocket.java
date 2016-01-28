package com.jamesnetherton;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws")
public class MyWebSocket {
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Got message: " + message);
    }

    @OnOpen
    public void open(Session session) {
        System.out.println("Session opened: " + session.getId());
    }

    @OnClose
    public void close(Session session, CloseReason reason) {
        System.out.println("Session closed: " + session.getId());
    }
}
