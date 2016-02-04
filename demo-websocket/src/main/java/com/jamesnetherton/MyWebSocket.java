package com.jamesnetherton;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.github.jamesnetherton.lolcat4j.Lol;

@ServerEndpoint("/ws")
public class MyWebSocket {

    private Lol lol;

    public MyWebSocket() {
        lol = Lol.builder().build();
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        lol.setText(message);
        lol.cat();
    }
}
