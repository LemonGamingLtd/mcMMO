package com.gmail.nossr50.skills.archery;

import com.gmail.nossr50.mcMMO;
import com.tcoded.folialib.wrapper.WrappedTask;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TrackedEntity implements Runnable {
    private final LivingEntity livingEntity;
    private final UUID id;
    private int arrowCount;

    private final WrappedTask wrappedTask;

    protected TrackedEntity(LivingEntity livingEntity) {
        this.livingEntity = livingEntity;
        this.id = livingEntity.getUniqueId();

        wrappedTask = mcMMO.getScheduler().getImpl().runAtEntityTimer(livingEntity, this, 10L, 10L, TimeUnit.MINUTES);
    }

    @Override
    public void run() {
        if (!livingEntity.isValid()) {
            Archery.removeFromTracker(this);
            wrappedTask.cancel();
        }
    }

    protected LivingEntity getLivingEntity() {
        return livingEntity;
    }

    protected UUID getID() {
        return id;
    }

    protected int getArrowCount() {
        return arrowCount;
    }

    protected void incrementArrowCount() {
        arrowCount++;
    }
}
