package com.gmail.nossr50.util;

import com.gmail.nossr50.api.exceptions.IncompleteNamespacedKeyRegister;
import com.gmail.nossr50.config.PersistentDataConfig;
import com.gmail.nossr50.mcMMO;
import com.gmail.nossr50.metadata.MobMetaFlagType;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.List;

import static com.gmail.nossr50.util.MetadataService.NSK_COTW_SUMMONED_MOB;
import static com.gmail.nossr50.util.MetadataService.NSK_EGG_MOB;
import static com.gmail.nossr50.util.MetadataService.NSK_EXPLOITED_ENDERMEN;
import static com.gmail.nossr50.util.MetadataService.NSK_MOB_SPAWNER_MOB;
import static com.gmail.nossr50.util.MetadataService.NSK_NETHER_GATE_MOB;
import static com.gmail.nossr50.util.MetadataService.NSK_PLAYER_BRED_MOB;
import static com.gmail.nossr50.util.MetadataService.NSK_PLAYER_TAMED_MOB;

//TODO: Use SpawnReason where appropriate instead of MobMetaFlagType
public final class MobMetadataUtils {
    private static final String METADATA_KEY = "mcMMO-Metadata";
    private static final @NotNull EnumMap<MobMetaFlagType, NamespacedKey> mobFlagKeyMap; //used for persistent data
    private static boolean isUsingPersistentData = false;

    private MobMetadataUtils() {
        // private ctor
    }

    static {
        mobFlagKeyMap = new EnumMap<>(MobMetaFlagType.class);
        initMobFlagKeyMap();

        for (MobMetaFlagType metaFlagType : MobMetaFlagType.values()) {
            if (PersistentDataConfig.getInstance().isMobPersistent(metaFlagType))
                isUsingPersistentData = true;
        }
    }

    /**
     * Registers the namespaced keys required by the API (CB/Spigot)
     * Used primarily for persistent data
     */
    private static void initMobFlagKeyMap() throws IncompleteNamespacedKeyRegister {
        for (MobMetaFlagType mobMetaFlagType : MobMetaFlagType.values()) {
            switch (mobMetaFlagType) {
                case MOB_SPAWNER_MOB -> mobFlagKeyMap.put(mobMetaFlagType, NSK_MOB_SPAWNER_MOB);
                case EGG_MOB -> mobFlagKeyMap.put(mobMetaFlagType, NSK_EGG_MOB);
                case NETHER_PORTAL_MOB -> mobFlagKeyMap.put(mobMetaFlagType, NSK_NETHER_GATE_MOB);
                case COTW_SUMMONED_MOB -> mobFlagKeyMap.put(mobMetaFlagType, NSK_COTW_SUMMONED_MOB);
                case PLAYER_BRED_MOB -> mobFlagKeyMap.put(mobMetaFlagType, NSK_PLAYER_BRED_MOB);
                case EXPLOITED_ENDERMEN -> mobFlagKeyMap.put(mobMetaFlagType, NSK_EXPLOITED_ENDERMEN);
                case PLAYER_TAMED_MOB -> mobFlagKeyMap.put(mobMetaFlagType, NSK_PLAYER_TAMED_MOB);
                default ->
                    throw new IncompleteNamespacedKeyRegister("missing namespaced key register for type: " + mobMetaFlagType);
            }
        }
    }

    /**
     * Whether a target {@link LivingEntity} has a specific mcMMO mob flags
     *
     * @param flag         the type of mob flag to check for
     * @param livingEntity the living entity to check for metadata
     * @return true if the mob has metadata values for target {@link MobMetaFlagType}
     */
    public static boolean hasMobFlag(@NotNull MobMetaFlagType flag, @NotNull LivingEntity livingEntity) {
        //if (PersistentDataConfig.getInstance().isMobPersistent(flag)) {
        //    return livingEntity.getPersistentDataContainer().has(mobFlagKeyMap.get(flag), PersistentDataType.BYTE);
        //} else {
        //    final List<MetadataValue> values = livingEntity.getMetadata(METADATA_KEY);
        //    if (values.isEmpty()) {
        //        return false;
        //    }
        //    return values.stream().anyMatch(value -> value.asString().equals(flag.name()));
        //}
        return false;
    }

    /**
     * Whether a target {@link LivingEntity} has any mcMMO mob flags
     *
     * @param livingEntity the living entity to check for metadata
     * @return true if the mob has any mcMMO mob related metadata values
     */
    public static boolean hasMobFlags(@NotNull LivingEntity livingEntity) {
        //if (isUsingPersistentData) {
        //    for (MobMetaFlagType metaFlagType : MobMetaFlagType.values()) {
        //        if (hasMobFlag(metaFlagType, livingEntity))
        //            return true;
        //    }
        //    return false;
        //} else {
        //    return !livingEntity.getMetadata(METADATA_KEY).isEmpty();
        //}
        return false;
    }

    /**
     * Copies all mcMMO mob flags from one {@link LivingEntity} to another {@link LivingEntity}
     * This does not clear existing mcMMO mob flags on the target
     *
     * @param sourceEntity entity to copy from
     * @param targetEntity entity to copy to
     */
    public static void addMobFlags(@NotNull LivingEntity sourceEntity, @NotNull LivingEntity targetEntity) {
        //if (!hasMobFlags(sourceEntity))
        //    return;
        //if (isUsingPersistentData) {
        //    for (MobMetaFlagType flag : MobMetaFlagType.values()) {
        //        if (hasMobFlag(flag, sourceEntity)) {
        //            flagMetadata(flag, targetEntity);
        //        }
        //    }
        //} else {
        //    for (final MetadataValue value : sourceEntity.getMetadata(METADATA_KEY)) {
        //        targetEntity.setMetadata(METADATA_KEY, value);
        //    }
        //}
    }

    /**
     * Adds a mob flag to a {@link LivingEntity} which effectively acts a true/false boolean
     * Existence of the flag can be considered a true value, non-existence can be considered false for all intents and purposes
     *
     * @param flag         the desired flag to assign
     * @param livingEntity the target living entity
     */
    public static void flagMetadata(@NotNull MobMetaFlagType flag, @NotNull LivingEntity livingEntity) {
        //if (PersistentDataConfig.getInstance().isMobPersistent(flag)) {
        //    if (!hasMobFlag(flag, livingEntity)) {
        //        PersistentDataContainer persistentDataContainer = livingEntity.getPersistentDataContainer();
        //        persistentDataContainer.set(mobFlagKeyMap.get(flag), PersistentDataType.BYTE, MetadataConstants.SIMPLE_FLAG_VALUE);
        //    }
        //} else {
        //    livingEntity.setMetadata(METADATA_KEY, new FixedMetadataValue(mcMMO.p, flag.name()));
        //}
    }

    /**
     * Removes a specific mob flag from target {@link LivingEntity}
     *
     * @param flag         desired flag to remove
     * @param livingEntity the target living entity
     */
    public static void removeMobFlag(@NotNull MobMetaFlagType flag, @NotNull LivingEntity livingEntity) {
        //if (PersistentDataConfig.getInstance().isMobPersistent(flag)) {
        //    if (hasMobFlag(flag, livingEntity)) {
        //        PersistentDataContainer persistentDataContainer = livingEntity.getPersistentDataContainer();
        //        persistentDataContainer.remove(mobFlagKeyMap.get(flag));
        //    }
        //} else {
        //    livingEntity.removeMetadata(METADATA_KEY, mcMMO.p);
        //}
    }

    /**
     * Remove all mcMMO related mob flags from the target {@link LivingEntity}
     *
     * @param livingEntity target entity
     */
    public static void removeMobFlags(@NotNull LivingEntity livingEntity) {
        //mcMMO.p.getFoliaLib().getImpl().runAtEntity(livingEntity, __ -> {
        //    if (isUsingPersistentData) {
        //        for (MobMetaFlagType flag : MobMetaFlagType.values()) {
        //            removeMobFlag(flag, livingEntity);
        //        }
        //    } else {
        //        livingEntity.removeMetadata(METADATA_KEY, mcMMO.p);
        //    }
        //});
    }
}
