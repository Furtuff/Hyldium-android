package com.tuff.hyldium.manager;

import android.content.Context;

import com.tuff.hyldium.R;
import com.tuff.hyldium.contract.IServiceManager;
import com.tuff.hyldium.factory.Factory;
import com.tuff.hyldium.model.ItemModel;
import com.tuff.hyldium.model.SearchDataModel;
import com.tuff.hyldium.service.WebServiceInterface;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tuffery on 14/09/17.
 */

public enum ServiceManager implements IServiceManager {
    instance;
    private static WebServiceInterface servicesInterface;

    private void init(Context context) {
        if (servicesInterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(context.getString(R.string.base_url))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            servicesInterface = retrofit.create(WebServiceInterface.class);
        }
    }

    public void searchItem(Context context, String query) {
        init(context);
        Response<List<ItemModel>> response = null;
        SearchDataModel searchDataModel = new SearchDataModel();
        searchDataModel.text = query;
        retrofit2.Call<List<ItemModel>> call = servicesInterface.search(searchDataModel);
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null) {
            if (response.headers().get("X-HM-RC") == null) {
                if (response.body() != null) {
                    Factory.build.getIComCallback().searchResult(response.body());
                }

            }
        }
    }
}
