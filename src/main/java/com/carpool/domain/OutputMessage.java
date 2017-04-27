package com.carpool.domain;

/**
 * Created by Crawlers on 4/26/2017.
 */
public class OutputMessage extends Message{
    private String time;

    public OutputMessage(String from, String text, String time) {
        super(from, text);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
