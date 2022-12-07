package com.team11.UTATrivia.utils;

import java.util.Locale;


public class Stopwatch {

    // Variables
    private long startTime;
    private long stopTime = 0;
    private boolean running = false;


    public void start() {
        this.running = true;
    }


    public void stop() {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }


    public void setCurrentTime(long starttime) {
        this.startTime = starttime;
    }



    public long getElapsedTime() {
        if (running) {
            return System.currentTimeMillis() - startTime;
        }
        return stopTime - startTime;
    }


    public long getElapsedTimeSecs() {
        if (running) {
            return ((System.currentTimeMillis() - startTime) / 1000);
        }
        return ((stopTime - startTime) / 1000);
    }


    public String getStringTime() {
        long currentTime = getElapsedTime();
        int seconds = (int) currentTime / 1000;
        int minutes = seconds / 60;
        int hours = minutes / 60;
        seconds -= minutes * 60;
        minutes -= hours * 60;
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }
}