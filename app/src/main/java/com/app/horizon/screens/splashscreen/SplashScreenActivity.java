package com.app.horizon.screens.splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.horizon.R;
import com.app.horizon.screens.main.MainActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;


public class SplashScreenActivity extends AppCompatActivity {

    private Subscription subscription;

    Observer observer = new Observer() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Object o) {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    @Override
    public void onBackPressed() {
        subscription.unsubscribe();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        subscription.unsubscribe();
        super.onPause();
    }

    @Override
    protected void onResume() {
        subscription = Observable.timer(3, TimeUnit.SECONDS).subscribe(observer);
        super.onResume();
    }
}
