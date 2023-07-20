package com.gmail.nossr50.runnables;

import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.mcMMO;
import com.gmail.nossr50.party.PartyManager;
import com.gmail.nossr50.runnables.player.PlayerProfileSaveTask;
import com.gmail.nossr50.util.LogUtils;
import com.gmail.nossr50.util.player.UserManager;

import java.util.concurrent.TimeUnit;

public class SaveTimerTask implements Runnable {
    @Override
    public void run() {
        LogUtils.debug(mcMMO.p.getLogger(), "[User Data] Saving...");
        // All player data will be saved periodically through this
        int count = 1;

        for (McMMOPlayer mcMMOPlayer : UserManager.getPlayers()) {
            mcMMO.getScheduler().getImpl().runLaterAsync(new PlayerProfileSaveTask(mcMMOPlayer.getProfile(), false), count * 50L, TimeUnit.MILLISECONDS);
            count++;
        }


        PartyManager.saveParties();
    }
}
