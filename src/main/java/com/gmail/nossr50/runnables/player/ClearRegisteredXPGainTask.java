package com.gmail.nossr50.runnables.player;

import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.util.player.UserManager;

public class ClearRegisteredXPGainTask implements Runnable {
    @Override
    public void run() {
        for (McMMOPlayer mcMMOPlayer : UserManager.getPlayers()) {
            mcMMOPlayer.getProfile().purgeExpiredXpGains();
        }
    }
}
