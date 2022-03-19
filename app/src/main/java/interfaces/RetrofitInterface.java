package interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @POST("API.php")
    @FormUrlEncoded
    Call<ResponseBody> addUser(
            @Field("userName") String userName,
            @Field("nrc") String nrc,
            @Field("phoneNumber") String phoneNumber,
            @Field("password") String password,
            @Field("email") String email,
            @Field("action") String action);

}