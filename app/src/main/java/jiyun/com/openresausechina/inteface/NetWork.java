package jiyun.com.openresausechina.inteface;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 * Created by think on 2017/4/5.
 */

public interface NetWork {
    //action/api/news_list
    @GET("action/api/news_list")
    Call<ResponseBody> getData(@Query("catalog") String catalog, @Query("pageIndex") String pageIndex, @Query("pageSize") String pageSize);

    @GET("action/api/news_detail")
    Call<ResponseBody> getDatas(@Query("id") String id);

    @GET("action/api/search_list")
    Call<ResponseBody> getDatass(@Query("catalog") String catalog, @Query("content") String content, @Query("pageIndex") String pageIndex, @Query("pageSize") String pageSize);

    @GET("action/api/search_list")
    Call<ResponseBody> getDataBoKe(@Query("catalog") String catalog, @Query("content") String content, @Query("pageIndex") String pageIndex, @Query("pageSize") String pageSize);

    @GET("action/api/software_detail")
    Call<ResponseBody> getWebView(@Query("ident") String ident);

    @POST("action/api/login_validate")
    @FormUrlEncoded
    Call<ResponseBody> getLoginData(@Field("username")String username,@Field("pwd")String pwd,@Field("keep_login")String keep_login);

    @GET("action/api/my_information")
    Call<ResponseBody> getUserInfoData(@Query("uid") String uid);
}
