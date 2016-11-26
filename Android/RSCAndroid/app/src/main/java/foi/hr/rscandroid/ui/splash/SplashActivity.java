package foi.hr.rscandroid.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import foi.hr.rscandroid.R;
import foi.hr.rscandroid.ui.BaseActivity;
import foi.hr.rscandroid.ui.login.LoginActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }, 2000);
    }
}
