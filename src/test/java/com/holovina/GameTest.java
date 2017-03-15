package com.holovina;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    private Game game;
    @Spy
    private Game spyGame;

    @Before
    public void setUp() throws Exception {
        game = new Game();
    }

    private void kickMany(int team, int [] kicks){
        for (int i = 0; i < kicks.length; ++i){
            try {
                game.kick("",team, kicks[i]);
            } catch (IllegalKickException illegalKickException) {
                illegalKickException.printStackTrace();
            }
        }
    }

    private void kickSpyMany(int team, int [] kicks){
        for (int i = 0; i < kicks.length; ++i){
            try {
                spyGame.kick("",team, kicks[i]);
            } catch (IllegalKickException illegalKickException) {
                illegalKickException.printStackTrace();
            }
        }
    }

    @Test
    public void firstEasyWin() throws Exception {
        kickMany(0,new int[]{1,1,1,1,1});
        kickMany(1,new int[]{0,0,0,0,0});

        assertEquals("5[0.0]:0[0.0]", game.score());
    }

    @Test
    public void notFinished() throws Exception {
        kickMany(0,new int[]{1,1,1,1,1,1});
        kickMany(1,new int[]{1,1,1,1,1});

        assertEquals(false, game.finished());
    }

    @Test
    public void noWinnerYet() throws Exception {

        kickMany(0, new int[]{1,1,1,1,1});
        kickMany(1, new int[]{1,1,1,1,1});

        assertEquals(-1, game.winner());
    }

    @Test
    public void leftWinner() throws Exception {
        kickMany(0, new int[]{1,1,1,1,1,1});
        kickMany(1, new int[]{1,1,1,1,1,0});

        assertEquals(0, game.winner());
    }

    @Test(expected=IllegalKickException.class)
    public void illegalKick() throws Exception {
        kickMany(0, new int[]{1,1,1,1,1,1});
        kickMany(1, new int[]{1,1,1,1,1,0});

        game.kick("",0, 1);
    }

    @Test
    public void messiHistory() throws Exception {
        Integer [] kicks = new Integer[]{1,0,1,0,1,0,1,0,1,0};

        when(spyGame.loadLast10("Messi")).thenReturn(kicks);
        assertThat(spyGame.kick("Messi",1,0), equalTo(kicks));
    }

    @Test
    public void scoreWithPriceBefore7() throws Exception {
        kickSpyMany(0, new int[]{0});
        kickSpyMany(1, new int[]{0});

        when(spyGame.totalPlayerPrice(0, 1)).thenReturn(0.0);
        when(spyGame.totalPlayerPrice(1, 1)).thenReturn(0.0);

        assertThat(spyGame.score(), equalTo("0[0.0]:0[0.0]"));
    }

    @Test
    public void scoreWithPrice7() throws Exception {
        kickSpyMany(0, new int[]{0,0,0,0,0,0,0});
        kickSpyMany(1, new int[]{0,0,0,0,0,0,0});

        when(spyGame.totalPlayerPrice(0, 7)).thenReturn(50000.0);
        when(spyGame.totalPlayerPrice(1, 7)).thenReturn(60000.0);

        assertThat(spyGame.score(), equalTo("0[50000.0]:0[60000.0]"));
    }
}