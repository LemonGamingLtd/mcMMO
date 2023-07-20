package com.gmail.nossr50.skills.taming;

import com.gmail.nossr50.datatypes.skills.subskills.taming.CallOfTheWildType;
import com.gmail.nossr50.mcMMO;
import com.gmail.nossr50.util.Misc;
import com.tcoded.folialib.wrapper.WrappedTask;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.TimeUnit;

public class TrackedTamingEntity implements Runnable {
    private final @NotNull LivingEntity livingEntity;
    private final @NotNull CallOfTheWildType callOfTheWildType;
    private final @NotNull Player player;

    private @Nullable WrappedTask wrappedTask;

    protected TrackedTamingEntity(@NotNull LivingEntity livingEntity, @NotNull CallOfTheWildType callOfTheWildType, @NotNull Player player) {
        this.player = player;
        this.callOfTheWildType = callOfTheWildType;
        this.livingEntity = livingEntity;

        int tamingCOTWLength = mcMMO.p.getGeneralConfig().getTamingCOTWLength(callOfTheWildType.getConfigEntityTypeEntry());

        if (tamingCOTWLength > 0) {
            wrappedTask = mcMMO.getScheduler().getImpl().runAtEntityLater(livingEntity, this, tamingCOTWLength, TimeUnit.SECONDS);
        }
    }

    @Override
    public void run() {
        mcMMO.getTransientEntityTracker().removeSummon(this.getLivingEntity(), player, true);
        if (wrappedTask != null) {
            wrappedTask.cancel();
        }
    }

    public @NotNull CallOfTheWildType getCallOfTheWildType() {
        return callOfTheWildType;
    }

    public @NotNull LivingEntity getLivingEntity() {
        return livingEntity;
    }
}
