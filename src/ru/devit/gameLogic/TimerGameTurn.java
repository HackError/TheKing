package ru.devit.gameLogic;

import java.util.TimerTask;

/**
 * Created by user on 08.05.2015.
 */
public class TimerGameTurn {

    public static void start()
    {
        java.util.Timer timer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            public void run()
            {
                //working
                GameLogic.turn();
            }
        };
        timer.schedule( task, 0L, 60L * 1000 );
    }

}
