package datasync.tapumandal.me.activity.Profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import datasync.tapumandal.me.R;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_profile);
    }

    public void profileCreateActivity(View view) {
        startActivity(new Intent(this, ProfileCreateActivity.class));
    }
}
