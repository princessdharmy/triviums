package com.app.horizon.utils;

/**
 * Created by Ayokunle Paul on 7/19/18.
 */
public class Utils {

    public static <T> T checkNotNull(T object){
        if (object == null){
            throw new NullPointerException("Object cannot be null");
        }

        return object;
    }

}
