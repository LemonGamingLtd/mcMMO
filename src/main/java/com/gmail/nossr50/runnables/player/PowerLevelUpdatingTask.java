package com.gmail.nossr50.runnables.player;

import com.gmail.nossr50.util.scoreboards.ScoreboardManager;

public class PowerLevelUpdatingTask implements Runnable {
    @Override
    public void run() {
        if (!ScoreboardManager.powerLevelHeartbeat()) {
//            this.cancel();
        }
    }
}
