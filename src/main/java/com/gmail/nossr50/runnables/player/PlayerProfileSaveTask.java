package com.gmail.nossr50.runnables.player;

import com.gmail.nossr50.datatypes.player.PlayerProfile;

public class PlayerProfileSaveTask implements Runnable {
    private final PlayerProfile playerProfile;
    private final boolean isSync;

    public PlayerProfileSaveTask(PlayerProfile playerProfile, boolean isSync) {
        this.playerProfile = playerProfile;
        this.isSync = isSync;
    }

    @Override
    public void run() {
        playerProfile.save(isSync);
    }
}
