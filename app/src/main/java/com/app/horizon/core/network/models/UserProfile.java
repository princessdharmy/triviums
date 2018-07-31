package com.app.horizon.core.network.models;

import com.app.horizon.utils.Utils;


public class UserProfile {

    private String name;
    private String email;
    private String profilePicture;

    private UserProfile(){}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public static class Builder{

        private Builder(){}

        private String name;
        private String email;
        private String profilePicture;

        public Builder(String name){
            this.name = name;
        }

        public Builder setEmail(String email){
            this.email = email;
            return this;
        }

        public Builder setProfilePicture(String profilePicture){
            this.profilePicture = profilePicture;
            return this;
        }

        public UserProfile build(){
            UserProfile profile = new UserProfile();
            profile.name = Utils.checkNotNull(name);
            profile.email = email;
            profile.profilePicture = profilePicture;

            return profile;
        }
    }

}
