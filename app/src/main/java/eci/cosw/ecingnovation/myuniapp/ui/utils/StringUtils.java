package eci.cosw.ecingnovation.myuniapp.ui.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class StringUtils {
    public static boolean isValidEmail( CharSequence target ) {
        return ( !TextUtils.isEmpty( target ) && Patterns.EMAIL_ADDRESS.matcher( target ).matches() );
    }
}
