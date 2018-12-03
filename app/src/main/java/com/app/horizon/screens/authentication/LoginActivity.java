package com.app.horizon.screens.authentication;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.horizon.HorizonMainApplication;
import com.app.horizon.R;
import com.app.horizon.core.base.BaseActivity;
import com.app.horizon.core.network.models.UserProfile;
import com.app.horizon.databinding.ActivityLoginBinding;
import com.app.horizon.screens.main.MainActivity;
import com.app.horizon.utils.ConnectivityReceiver;
import com.app.horizon.utils.Utils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import javax.inject.Inject;


public class LoginActivity extends BaseActivity<LoginActivityViewModel> {

    //Firebase declaration
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private FirebaseUser user;

    //Google initialisation
    private final static int RC_SIGN_IN = 123;
    GoogleSignInClient mGoogleSignInClient;

    @Inject
    Utils utils;
    ActivityLoginBinding binding;
    UserProfile userProfile;
    LoginActivityViewModel viewModel;
    @Inject
    ViewModelProvider.Factory factory;

    public static final int MobileData = 2;
    public static final int WifiData = 1;
    boolean isConnected;
    @Inject
    ConnectivityReceiver connectivityReceiver;


    @Override
    public LoginActivityViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(LoginActivityViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        //Initialise UserProfile
        userProfile = new UserProfile();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        binding.signInButton.setOnClickListener(view -> {
            connectivityReceiver.observe(this, connectionModel -> {
                try {
                    if (connectionModel.isConnected()) {
                        isConnected = true;
                        signIn();
                    } else {
                        isConnected = false;
                        utils.showSnackbar(this, getResources().getString(R.string.newtwork_unavailable));
                    }
                } catch(Exception e){
                    utils.showSnackbar(this, "Error fetching data");
                }
            });
        });

        firebaseAuthListener = firebaseAuth -> {
            user = firebaseAuth.getCurrentUser();
            if (user != null) {
                setProfile();
                goMainScreen();
            }
        };

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
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

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
            }

        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        binding.signInButton.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        viewModel.setLoggedIn(true);
                        // Sign in success, update UI with the signed-in user's information
                        binding.progressBar.setVisibility(View.GONE);
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }

                });
    }

    //Set user profile to UserProfile
    private void setProfile() {
        userProfile.setUserUid(user.getUid());
        userProfile.setEmail(user.getEmail());
        userProfile.setName(user.getDisplayName());
        userProfile.setProfilePicture(user.getPhotoUrl() != null ? user.getPhotoUrl().toString() : null);

        viewModel.addUserToCloud(userProfile);
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
