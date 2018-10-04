package com.app.horizon.utils;


public class Utils {

    public static <T> T checkNotNull(T object){
        if (object == null){
            throw new NullPointerException("Object cannot be null");
        }

        return object;
    }

}
