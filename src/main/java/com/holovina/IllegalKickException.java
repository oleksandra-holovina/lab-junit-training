package com.holovina;

public class IllegalKickException extends Exception{
    @Override
    public String toString() {
        return "The game is already finished. No more kicks allowed!";
    }
}
