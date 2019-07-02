package datasync.tapumandal.me.service;

import android.util.Log;
import android.widget.Toast;

import datasync.tapumandal.me.Interface.ApiInterface;
import datasync.tapumandal.me.activity.Profile.ProfileCreateActivity;
import datasync.tapumandal.me.retrofit.RetrofitApiClient;
import datasync.tapumandal.me.storage.entity.ProfileModel;
import datasync.tapumandal.me.storage.entity.ServerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteStorage {



    private ApiInterface apiInterface;

    public void saveProfileInRemoteServer(ProfileModel profileModel) {

        apiInterface = null;
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        Call<ServerResponse> call = apiInterface.saveProfile(profileModel);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {


                if (response.isSuccessful()) {
//                    Toast.makeText(ProfileCreateActivity.this, "Remote Call successfull", Toast.LENGTH_SHORT).show();
                    Log.d("response", "Remote Call successfull");

                }else {
                    Log.d("response", response.toString());
                }

                Log.d("response", response.toString());


            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
//                Toast.makeText(ProfileCreateActivity.this, "Remote Call faill", Toast.LENGTH_SHORT).show();
                Log.d("response", "Remote Call faill");
                Log.d("response", t.getMessage());

            }
        });
    }
}
