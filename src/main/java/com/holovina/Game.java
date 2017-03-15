package com.holovina;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleksandra_Holovina on 3/15/2017.
 */
public class Game {
    private List<Integer> left = new ArrayList<Integer>();
    private List<Integer> right = new ArrayList<Integer>();


    private int sum(List<Integer> lst) {
        int score = 0;
        for (Integer l : lst) {
            score += l;
        }
        return score;
    }

    public boolean finished() {
        if (left.size() < 5 || right.size() < 5) {
            return false;
        }

        if (Math.abs(left.size()-right.size()) == 1){
            return false;
        }

        if (sum(left) != sum(right)) {
            return true;
        }

        return false;
    }

    public Integer [] kick(String playerName, int team, int kick) throws IllegalKickExecption {
        if (finished()){
            throw new IllegalKickExecption();
        }
        if (team == 0) {
            left.add(kick);
        } else {
            right.add(kick);
        }

        return loadLast10(playerName);
    }


    public int winner() {
        if (finished()) {
            int leftScore = sum(left);
            int rightScore = sum(right);
            return leftScore > rightScore ? 0 : 1;
        }
        return -1;
    }


    public String score() {
        return sum(left)+"["+ totalPlayerPriceNotKicked(0, left.size())+"]" + ":"
                + sum(right)+"["+ totalPlayerPriceNotKicked(1, right.size())+"]";
    }

    public Integer [] loadLast10(String player) {
        return null;
    }

    public double totalPlayerPriceNotKicked(int team, int serie){
        return 0;
    }
}
