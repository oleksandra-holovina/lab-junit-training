package com.holovina;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Integer> firTeamKicks = new ArrayList<>();
    private List<Integer> secTeamKicks = new ArrayList<>();

    private int sum(List<Integer> lst) {
       return lst.stream().mapToInt(i -> i).sum();
    }

    public boolean finished() {
        if (firTeamKicks.size() < 5 || secTeamKicks.size() < 5) {
            return false;
        }

        if (Math.abs(firTeamKicks.size()- secTeamKicks.size()) == 1){
            return false;
        }

        if (sum(firTeamKicks) != sum(secTeamKicks)) {
            return true;
        }

        return false;
    }

    public Integer [] kick(String playerName, int team, int kick) throws IllegalKickException {
        if (finished()){
            throw new IllegalKickException();
        }
        if (team == 0) {
            firTeamKicks.add(kick);
        } else {
            secTeamKicks.add(kick);
        }

        return loadLast10(playerName);
    }

    public int winner() {
        if (finished()) {
            int leftScore = sum(firTeamKicks);
            int rightScore = sum(secTeamKicks);
            return leftScore > rightScore ? 0 : 1;
        }
        return -1;
    }

    public String score() {
        return sum(firTeamKicks)+"["+ totalPlayerPrice(0, firTeamKicks.size())+"]" + ":"
                + sum(secTeamKicks)+"["+ totalPlayerPrice(1, secTeamKicks.size())+"]";
    }

    public Integer [] loadLast10(String player) {
        return null;
    }

    public double totalPlayerPrice(int team, int serie){
        return 0;
    }
}
