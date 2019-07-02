package datasync.tapumandal.me.Interface;

import java.util.List;

import datasync.tapumandal.me.storage.entity.Data;
import datasync.tapumandal.me.storage.entity.ProfileModel;
import datasync.tapumandal.me.storage.entity.RemoteProfileListModel;
import datasync.tapumandal.me.storage.entity.ServerResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("profile")
    Call<ServerResponse> saveProfile(@Body ProfileModel profileModel);

    @GET("profile")
    Call<List<RemoteProfileListModel>> getAllProfile();
}
