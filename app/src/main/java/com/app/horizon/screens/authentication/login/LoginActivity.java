package com.app.horizon.screens.authentication.login;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.horizon.R;
import com.app.horizon.core.base.BaseActivity;
import com.app.horizon.screens.main.MainActivity;
import com.app.horizon.screens.splashscreen.SplashScreenViewModel;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;


public class LoginActivity extends BaseActivity<LoginActivityViewModel> {

    private FirebaseAuth mAuth;
    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    private CallbackManager mCallbackManager;
    LoginButton loginButton;
    private ProgressBar progressBar;

    LoginActivityViewModel viewModel;
    @Inject
    ViewModelProvider.Factory factory;

    private static final String EMAIL = "email";
    private static final String PUBLIC_PROFILE = "public_profile";

    @Override
    public LoginActivityViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(LoginActivityViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progressBar);

        //Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(EMAIL, PUBLIC_PROFILE);

        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Login Cancel", Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("FACEBOOK LOGIN ERROR", error.toString());
                Toast.makeText(getApplicationContext(), "Login Error", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        firebaseAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                goMainScreen();
            }
        };

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        progressBar.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.GONE);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        viewModel.setLoggedIn(true);
                        Toast.makeText(getApplicationContext(), "Login Successful",
                                Toast.LENGTH_SHORT).show();
                        // Sign in success, update UI with the signed-in user's information
                        progressBar.setVisibility(View.GONE);
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }

                });
    }

    private void goMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
        Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }
}
