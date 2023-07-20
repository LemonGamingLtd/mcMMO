package com.gmail.nossr50.runnables.database;

import com.gmail.nossr50.mcMMO;

import java.util.concurrent.locks.ReentrantLock;

public class UserPurgeTask implements Runnable {
    private final ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        lock.lock();
        mcMMO.getDatabaseManager().purgePowerlessUsers();

        if (mcMMO.p.getGeneralConfig().getOldUsersCutoff() != -1) {
            mcMMO.getDatabaseManager().purgeOldUsers();
        }
        lock.unlock();
    }
}
