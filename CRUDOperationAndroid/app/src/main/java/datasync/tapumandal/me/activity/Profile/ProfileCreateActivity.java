package datasync.tapumandal.me.activity.Profile;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import datasync.tapumandal.me.R;
import datasync.tapumandal.me.storage.dao.ProfileDao;
import datasync.tapumandal.me.storage.database.ProfileDatabase;
import datasync.tapumandal.me.storage.entity.Data;
import datasync.tapumandal.me.storage.entity.ProfileModel;
import datasync.tapumandal.me.storage.entity.ProfileModelLocal;

public class ProfileCreateActivity extends AppCompatActivity {

    private EditText etName, etEmail, etGender, etPhone, etCountry;
    private String name, email, gender, phone, country;

    private ProfileModel profileModel;
    private ProfileModelLocal profileModelLocal;

    private ProfileDao profileDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile_create);




        ProfileDatabase db = ProfileDatabase.getProfileDatabase(this);
        profileDao = db.profileDao();

    }

    public void createProfile(View view) {

        profileModelLocal = new ProfileModelLocal();

        Data data = new Data();

        name = ((EditText) findViewById(R.id.et_name)).getText().toString();
        email = ((EditText) findViewById(R.id.et_email)).getText().toString();
        gender = ((EditText) findViewById(R.id.et_gender)).getText().toString();
        phone = ((EditText) findViewById(R.id.et_phone)).getText().toString();
        country = ((EditText) findViewById(R.id.et_country)).getText().toString();

        profileModelLocal.setName(name);
        profileModelLocal.setEmail(email);
        profileModelLocal.setGender(gender);
        profileModelLocal.setPhone(phone);
        profileModelLocal.setCountry(country);
        profileModelLocal.setSyncStatus("false");



//        new RemoteStorage().saveProfileInRemoteServer(profileModel);

        new insertInAsyncTask().execute(profileModelLocal);

        startActivity(new Intent(this, ProfileActivity.class));
    }


    class insertInAsyncTask extends AsyncTask<ProfileModelLocal, Void, Void> {


        private List<ProfileModelLocal> profileModelLocalRead;

        @Override
        protected Void doInBackground(ProfileModelLocal... profileModelsLocal) {

            profileDao.insertAll(profileModelsLocal[0]);

            profileModelLocalRead = profileDao.getAll();


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Log.d("profile", new Gson().toJson(profileModelLocalRead));
            Toast.makeText(ProfileCreateActivity.this, "FromLOCAL # " + profileModelLocalRead.get(0).getName(), Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
