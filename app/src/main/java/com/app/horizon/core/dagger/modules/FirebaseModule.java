package com.app.horizon.core.dagger.modules;

import com.app.horizon.core.dagger.scopes.MainAppScope;
import com.app.horizon.core.network.models.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseModule {


    @Singleton
    @Provides
    FirebaseFirestore providesFirestore() {
        FirebaseFirestore.setLoggingEnabled(true);
        return FirebaseFirestore.getInstance();
    }

    @Singleton
    @Provides
    FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Singleton
    @Provides
    FirebaseUser provideFirebaseUser(FirebaseAuth firebaseAuth) {
        return firebaseAuth.getCurrentUser();
    }

    @Singleton
    @Provides
    public UserProfile provideUserProfile(FirebaseUser user) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserUid(user.getUid());
        userProfile.setEmail(user.getEmail());
        userProfile.setName(user.getDisplayName());
        userProfile.setProfilePicture(user.getPhotoUrl() != null ? user.getPhotoUrl().toString() : null);

        return userProfile;
    }

}
