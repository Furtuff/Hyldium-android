package com.tuff.hyldium.service;

import com.tuff.hyldium.model.ItemModel;
import com.tuff.hyldium.model.SearchDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by tuffery on 14/09/17.
 */

public interface WebServiceInterface {
    @POST("product/search")
    Call<List<ItemModel>> search(@Body SearchDataModel text);

}
