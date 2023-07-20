package com.gmail.nossr50.runnables;

import com.gmail.nossr50.mcMMO;
import com.gmail.nossr50.util.BlockUtils;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class StickyPistonTrackerTask implements Runnable {
    private final BlockFace direction;
    private final Block block;
    private final Block movedBlock;

    public StickyPistonTrackerTask(BlockFace direction, Block block, Block movedBlock) {
        this.direction = direction;
        this.block = block;
        this.movedBlock = movedBlock;
    }

    @Override
    public void run() {
        if (!mcMMO.getPlaceStore().isTrue(movedBlock.getRelative(direction))) {
            return;
        }

        if (!BlockUtils.isPistonPiece(movedBlock.getState())) {
            // The block didn't move
            return;
        }

        // The sticky piston actually pulled the block so move the PlaceStore data
        mcMMO.getPlaceStore().setFalse(movedBlock.getRelative(direction));
        BlockUtils.setUnnaturalBlock(movedBlock);
    }
}
