package com.chenxushao.common.i18n;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public abstract class MessagesCore {

//    private static Logger log = Logger.getLogger(MessagesCore.class);

    public static final String KEY_NOT_FOUND_PREFIX = "!!!";  

    public static final String KEY_NOT_FOUND_SUFFIX = "!!!";  
    public static String getString(String key, ResourceBundle resourceBundle) {
        if (resourceBundle == null) {
            return KEY_NOT_FOUND_PREFIX + key + KEY_NOT_FOUND_SUFFIX;
        }
        try {
            return resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            return KEY_NOT_FOUND_PREFIX + key + KEY_NOT_FOUND_SUFFIX;
        }
    }

    public static String getString(String key, ResourceBundle resourceBundle, Object... args) {
        return MessageFormat.format(getString(key, resourceBundle), args);
    }
}
