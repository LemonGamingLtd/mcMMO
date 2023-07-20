package com.gmail.nossr50.runnables.player;

import org.bukkit.entity.Player;

@SuppressWarnings("deprecation")
public class PlayerUpdateInventoryTask implements Runnable {
    private final Player player;

    public PlayerUpdateInventoryTask(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        player.updateInventory();
    }
}
