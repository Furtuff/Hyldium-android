package com.tuff.hyldium.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.tuff.hyldium.factory.Factory;

/**
 * Created by tuffery on 14/09/17.
 */

public class SearchService extends IntentService {
    public static final String SEARCH_TEXT = "SEARCH_TEXT";

    public SearchService() {
        super("SearchService");
    }

    public static Intent intentBuildSearchService(Context context, String textResearch) {
        Intent intent = new Intent(context, SearchService.class);
        intent.putExtra(SEARCH_TEXT, textResearch);
        return intent;
    }

    ;

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Factory.build.getIServiceManager().searchItem(this, (String) intent.getExtras().get(SEARCH_TEXT));
    }
}
