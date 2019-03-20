package com.jenking.graduatesinternship.api;

import com.jenking.graduatesinternship.models.base.ResultModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhouzhenjian on 2018/3/26.
 */

public interface ApiService {

    //模板接口
    @FormUrlEncoded
    @POST("user/login")
    Observable<ResultModel> template(@FieldMap Map<String, String> body);

}
