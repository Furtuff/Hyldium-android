package com.tuff.hyldium.manager;

import android.content.Context;

import com.tuff.hyldium.contract.IComm;
import com.tuff.hyldium.service.SearchService;

/**
 * Created by tuffery on 20/07/17.
 */

public enum CommManager implements IComm {
    instance;

    public void searchItem(Context context, String query) {
        context.startService(SearchService.intentBuildSearchService(context, query));
    }
}
