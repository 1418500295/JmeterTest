package com.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class HostUtil {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("hosts", Locale.CHINA);

    public static String getHost( ){

       return resourceBundle.getString("host");
    }



}
