package com.darkovr.patm.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.darkovr.patm.BuildConfig;
import com.darkovr.patm.Fragments.LoginFragment;
import com.darkovr.patm.Fragments.SplashScreenFragment;
import com.darkovr.patm.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(savedInstanceState == null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new SplashScreenFragment())
                    .commit();
        else
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new LoginFragment())
                    .commit();

        //Toast.makeText(this, BuildConfig.PROTOCOL+BuildConfig.BASE_URL+BuildConfig.PORT,Toast.LENGTH_LONG).show();
    }
}
