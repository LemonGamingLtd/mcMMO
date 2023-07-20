package com.gmail.nossr50.runnables.skills;

import com.gmail.nossr50.datatypes.BlockSnapshot;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;

import java.util.ArrayList;

public class DelayedHerbalismXPCheckTask implements Runnable {

    private final McMMOPlayer mcMMOPlayer;
    private final ArrayList<BlockSnapshot> chorusBlocks;

    public DelayedHerbalismXPCheckTask(McMMOPlayer mcMMOPlayer, ArrayList<BlockSnapshot> chorusBlocks) {
        this.mcMMOPlayer = mcMMOPlayer;
        this.chorusBlocks = chorusBlocks;
    }

    @Override
    public void run() {
        mcMMOPlayer.getHerbalismManager().awardXPForBlockSnapshots(chorusBlocks);
    }
}
