package com.tuff.hyldium.factory;

import com.tuff.hyldium.contract.ICache;
import com.tuff.hyldium.contract.IComm;
import com.tuff.hyldium.contract.IDatabase;
import com.tuff.hyldium.manager.CacheManager;
import com.tuff.hyldium.manager.CommManager;
import com.tuff.hyldium.manager.DatabaseManager;

/**
 * Created by tuffery on 20/07/17.
 */

public enum Factory {
    build;
    private static CacheManager cacheManager;
    private static CommManager commManager;
    private static DatabaseManager databaseManager;

    public ICache getICache() {
        if (cacheManager == null) {
            cacheManager = CacheManager.instance;
        }
        return cacheManager;
    }

    public IComm getIComm() {
        if (commManager == null) {
            commManager = CommManager.instance;
        }
        return commManager;
    }

    public IDatabase getIDatabase() {
        if (databaseManager == null) {
            databaseManager = DatabaseManager.instance;
        }
        return databaseManager;
    }
}
