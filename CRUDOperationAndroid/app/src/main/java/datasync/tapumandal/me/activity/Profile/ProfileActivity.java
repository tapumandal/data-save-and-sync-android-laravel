package datasync.tapumandal.me.activity.Profile;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import datasync.tapumandal.me.Interface.ApiInterface;
import datasync.tapumandal.me.R;
import datasync.tapumandal.me.retrofit.RetrofitApiClient;
import datasync.tapumandal.me.service.RemoteStorage;
import datasync.tapumandal.me.storage.dao.ProfileDao;
import datasync.tapumandal.me.storage.database.ProfileDatabase;
import datasync.tapumandal.me.storage.entity.Data;
import datasync.tapumandal.me.storage.entity.ProfileModel;
import datasync.tapumandal.me.storage.entity.ProfileModelLocal;
import datasync.tapumandal.me.storage.entity.RemoteProfileListModel;
import datasync.tapumandal.me.storage.entity.ServerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {


    private ApiInterface apiInterface;

    private ProfileDao profileDao;

    List<ProfileModelLocal> profileModelLocalUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_profile);



        apiInterface = null;
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        ProfileDatabase db = ProfileDatabase.getProfileDatabase(this);
        profileDao = db.profileDao();


        new insertInAsyncTask().execute();

        getRemoteProfileData();
    }

    public void profileCreateActivity(View view) {
        startActivity(new Intent(this, ProfileCreateActivity.class));
    }



    private void loadRemoteProfle(List<RemoteProfileListModel> profiles) {


        ArrayList<String> pro = new ArrayList<String>();

        for (int i=0; i<profiles.size(); i++){
            pro.add(profiles.get(i).getName()+" "+profiles.get(i).getEmail());
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.profile_list_view,pro);

        ListView listView = (ListView) findViewById(R.id.tv_profile_list);
        listView.setAdapter(adapter);


    }




    private void getRemoteProfileData() {


        Call<List<RemoteProfileListModel>> call = apiInterface.getAllProfile();

        call.enqueue(new Callback<List<RemoteProfileListModel>>() {
            @Override
            public void onResponse(Call<List<RemoteProfileListModel>> call, Response<List<RemoteProfileListModel>> response) {


                if (response.isSuccessful()){

                    Toast.makeText(ProfileActivity.this, "Remote Call successfull", Toast.LENGTH_SHORT).show();
                    loadRemoteProfle(response.body());

                 }else {
                    Log.d("response", response.toString());
                }

                Log.d("response", response.toString());


            }

            @Override
            public void onFailure(Call<List<RemoteProfileListModel>> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Remote Call faill", Toast.LENGTH_SHORT).show();

                Log.d("response", t.getMessage());

            }
        });
    }




    class insertInAsyncTask extends AsyncTask<ProfileModelLocal, Void, Void> {


        private List<ProfileModelLocal> profileModelLocalRead;

        @Override
        protected Void doInBackground(ProfileModelLocal... profileModelsLocal) {

            profileModelLocalRead = profileDao.getAll();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(ProfileActivity.this, "FromLOCAL # " + profileModelLocalRead.get(0).getName(), Toast.LENGTH_SHORT).show();

            loadLocalProfile(profileModelLocalRead);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    private void loadLocalProfile(List<ProfileModelLocal> profileModelLocalRead) {

        profileModelLocalUpload = profileModelLocalRead;
        ArrayList<String> pro = new ArrayList<String>();

        for (int i=0; i<profileModelLocalRead.size(); i++){
            if(profileModelLocalRead.get(i).getSyncStatus().equals("false")) {
                pro.add(profileModelLocalRead.get(i).getName() + " " + profileModelLocalRead.get(i).getEmail() + " #" + profileModelLocalRead.get(i).getSyncStatus());
            }else{
            }
        }


        ArrayAdapter adapter2 = new ArrayAdapter<String>(this,R.layout.profile_list_view,pro);

        ListView listView2 = (ListView) findViewById(R.id.tv_local_profile_list);
        listView2.setAdapter(adapter2);


    }


    public void syncLocalData(View view) {
        Toast.makeText(this, "synced", Toast.LENGTH_SHORT).show();


//        List<ProfileModel> profileModelUpload = (List<ProfileModel>) new ProfileModel();
        ProfileModel profileModelUpload = new ProfileModel();



        for (int i=0; i<profileModelLocalUpload.size(); i++){
            profileModelUpload.setName(profileModelLocalUpload.get(i).getName());
            profileModelUpload.setEmail(profileModelLocalUpload.get(i).getEmail());
            profileModelUpload.setGender(profileModelLocalUpload.get(i).getGender());
            profileModelUpload.setPhone(profileModelLocalUpload.get(i).getPhone());
            profileModelUpload.setCountry(profileModelLocalUpload.get(i).getCountry());

            new RemoteStorage().saveProfileInRemoteServer(profileModelUpload);

            new updateInAsyncTask().execute((Integer) profileModelLocalUpload.get(i).getId());
        }


        startActivity(new Intent(this, ProfileActivity.class));

    }



    class updateInAsyncTask extends AsyncTask<Integer, Void, Void> {


//        private List<ProfileModelLocal> profileModelLocalRead;


        private List<ProfileModelLocal> profileModelLocalRead;

        @Override
        protected Void doInBackground(Integer... integers) {


            profileDao.update(integers[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

//            Log.d(   "profile", new Gson().toJson(profileModelLocalRead));
//            Toast.makeText(ProfileActivity.this, "XXXXXX # " + profileModelLocalRead.get(0).getName(), Toast.LENGTH_SHORT).show();

//            loadLocalProfile(profileModelLocalRead);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
