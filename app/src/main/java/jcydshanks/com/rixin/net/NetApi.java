package jcydshanks.com.rixin.net;

import android.media.Image;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetApi {

//    获取bing每日一图
    @GET("v1")
    Call<ResponseBody> getBingWallPaper(@Query("w") int w, @Query("h") int h);
}
