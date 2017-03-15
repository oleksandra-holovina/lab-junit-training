package com.holovina;

public class IllegalKickExecption extends Exception{
    @Override
    public String toString() {
        return "The game is already finished. No more kicks allowed!";
    }
}
