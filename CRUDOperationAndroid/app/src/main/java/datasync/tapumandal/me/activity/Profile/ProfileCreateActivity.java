package datasync.tapumandal.me.activity.Profile;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import datasync.tapumandal.me.Interface.ApiInterface;
import datasync.tapumandal.me.R;
import datasync.tapumandal.me.retrofit.RetrofitApiClient;
import datasync.tapumandal.me.storage.dao.ProfileDao;
import datasync.tapumandal.me.storage.database.ProfileDatabase;
import datasync.tapumandal.me.storage.entity.Data;
import datasync.tapumandal.me.storage.entity.ProfileModel;
import datasync.tapumandal.me.storage.entity.ServerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileCreateActivity extends AppCompatActivity {

    private EditText etName, etEmail, etGender, etPhone, etCountry;
    private String name, email, gender, phone, country;

    private ProfileModel profileModel;

    private ProfileDao profileDao;

    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile_create);

        apiInterface = null;
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);


//        ProfileDatabase db = ProfileDatabase.getProfileDatabase(this);
//        profileDao = db.profileDao();

    }

    public void createProfile(View view) {

        profileModel = new ProfileModel();

        Data data = new Data();

        name = ((EditText) findViewById(R.id.et_name)).getText().toString();
        email = ((EditText) findViewById(R.id.et_email)).getText().toString();
        gender = ((EditText) findViewById(R.id.et_gender)).getText().toString();
        phone = ((EditText) findViewById(R.id.et_phone)).getText().toString();
        country = ((EditText) findViewById(R.id.et_country)).getText().toString();

        data.setName(name);
        data.setEmail(email);
        profileModel.setGender(gender);
        profileModel.setPhone(phone);
        data.setCountry(country);





        Toast.makeText(getApplicationContext(), name + "-" + email, Toast.LENGTH_LONG).show();

        saveProfileInRemoteServer(data);
        //profileDao.insertAll((ProfileActivity) profileDao);

//        new insertInAsyncTask().execute(profileModel);
    }

    private void saveProfileInRemoteServer(Data data) {
        Call<ServerResponse> call = apiInterface.saveProfile(data);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {


                if (response.isSuccessful())

                    Toast.makeText(ProfileCreateActivity.this, "Call successfull", Toast.LENGTH_SHORT).show();

                else {
                    Log.d("response", response.toString());
                }

                Log.d("response", response.toString());


            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(ProfileCreateActivity.this, "Call faill", Toast.LENGTH_SHORT).show();

                Log.d("response", t.getMessage());

            }
        });
    }


    class insertInAsyncTask extends AsyncTask<ProfileModel, Void, Void> {


        private List<ProfileModel> profileModelRead;

        @Override
        protected Void doInBackground(ProfileModel... profileModels) {

            profileDao.insertAll(profileModels[0]);

            profileModelRead = profileDao.getAll();


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(ProfileCreateActivity.this, "" + profileModelRead.get(0).getCountry(), Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
