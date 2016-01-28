package com.jamesnetherton;

import javax.inject.Named;

import com.jamesnetherton.lolcat4j.Lol;

@Named("rainbowBean")
public class RainbowBean {
    private Lol lol;

    public RainbowBean() {
        lol = Lol.builder().build();
    }

    public void paintRainbow(String body) {
        lol.setText(body);
        lol.cat();
    }
}
