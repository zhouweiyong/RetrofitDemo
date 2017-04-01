package com.vst.retrofitdemo;

import com.vst.retrofitdemo.bean.Result;
import com.vst.retrofitdemo.bean.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Query\Path\Field
 */
public interface VstService {

    public static final String HOST = "http://192.168.3.155/index.php/";

    @GET("account/getinfo")
    Call<ResponseBody> getInfo();

    @GET("account/get2")
    Call<ResponseBody> get2(@Query("userName") String userName, @Query("age") int age, @Query("gender") String gender);

    @POST("account/postinfo")
    @FormUrlEncoded
    Call<String> post1(@Field("userName") String userName, @Field("age") int age, @Field("gender") String gender);

    @POST("account/postinfo")
    @FormUrlEncoded
    Call<HashMap<String, String>> post2(@Field("userName") String userName, @Field("age") int age, @Field("gender") String gender);

    @POST("account/post2")
    @FormUrlEncoded
    Call<Result<ArrayList<User>>> post3(@Field("msg") String msg);

    @POST("{module}/post2")
    @FormUrlEncoded
    Call<Result<ArrayList<User>>> post4(@Path("module") String module, @Field("msg") String msg);

    @FormUrlEncoded
    @POST("account/postinfo")
    Call<HashMap<String, String>> post6(@FieldMap Map<String, String> map);

    @Multipart
    @POST("account/postFile")
    Call<ResponseBody> upload(@Part("msg") RequestBody msg, @Part MultipartBody.Part file);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("account/post3")
    Call<ResponseBody> post8(@Body RequestBody params);

    @GET("account/getinfo")
    Observable<ResponseBody> getInfo2();
}
