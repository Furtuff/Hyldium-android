package com.tuff.hyldium.factory;

import com.tuff.hyldium.contract.ICache;
import com.tuff.hyldium.contract.IComCallback;
import com.tuff.hyldium.contract.IComm;
import com.tuff.hyldium.contract.IDatabase;
import com.tuff.hyldium.contract.IServiceManager;
import com.tuff.hyldium.manager.CacheManager;
import com.tuff.hyldium.manager.CommManager;
import com.tuff.hyldium.manager.DatabaseManager;
import com.tuff.hyldium.manager.ServiceManager;

/**
 * Created by tuffery on 20/07/17.
 */

public enum Factory {
    build;
    private static CacheManager cacheManager;
    private static CommManager commManager;
    private static DatabaseManager databaseManager;
    private static ServiceManager serviceManager;

    public ICache getICache() {
        if (cacheManager == null) {
            cacheManager = CacheManager.instance;
        }
        return cacheManager;
    }

    public IComCallback getIComCallback() {
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

    public IServiceManager getIServiceManager() {
        if (serviceManager == null) {
            serviceManager = ServiceManager.instance;
        }
        return serviceManager;
    }
}
