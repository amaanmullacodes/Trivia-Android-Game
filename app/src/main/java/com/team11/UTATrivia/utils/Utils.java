package com.team11.UTATrivia.utils;

import static com.team11.UTATrivia.utils.Constants.VALID_EMAIL_ADDRESS_REGEX;
import static com.team11.UTATrivia.utils.Constants.VALID_PASSWORD_REGEX;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;

import es.dmoral.toasty.Toasty;


public class Utils {


    public static boolean validateEmail(CharSequence emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }


    public static boolean validatePassword(CharSequence passwordStr) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(passwordStr);
        return matcher.find();
    }

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }


    public static void loadImage(Context context, String userId, ImageView imageView) {
        final StorageReference mStorageRef = FirebaseStorage.getInstance().getReference()
                .child("user_profile_pictures/" + userId + ".png");

        mStorageRef.getDownloadUrl().addOnSuccessListener(uri -> {
            Glide.with(context)
                    .load(uri.toString())
                    .into(imageView);
        });
    }

    public static void uploadImage(String id, Uri imageUri) {
        final StorageReference mStorageRef = FirebaseStorage.getInstance().getReference()
                .child("user_profile_pictures/" + id + ".png");

        mStorageRef.putFile(imageUri);
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context != null && context.getSystemService(Context.CONNECTIVITY_SERVICE) != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (null != activeNetwork) {
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        return true;
                    }
                    return activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
                }
            }
        }
        return false;
    }


    public static void getDate(Activity context, EditText editText) {
        Calendar myCalendar = Calendar.getInstance();


        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "dd-MM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            String startDateStr = sdf.format(myCalendar.getTime());
            editText.setText(startDateStr);

        };
        new DatePickerDialog(Objects.requireNonNull(context), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    public static void getTime(Context context, EditText editText) {
        Calendar mCurrentTime = Calendar.getInstance();
        int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(context, (timePicker, hourOfDay, minute1) -> {
            boolean isPM = (hourOfDay >= 12);
            String time = " " + String.format("%02d:%02d %s",
                    (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute1, isPM ? "PM" : "AM");
            editText.setText(time);
        }, hour, minute, false);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }


    public static void success(Context context, String message) {
        Toasty.success(context, message, Toasty.LENGTH_SHORT, true).show();
    }

    public static void error(Context context, String message) {
        Toasty.error(context, message, Toasty.LENGTH_SHORT, true).show();
    }


    public static void info(Context context, String message) {
        Toasty.info(context, message, Toasty.LENGTH_SHORT, true).show();
    }


    public static void warning(Context context, String message) {
        Toasty.warning(context, message, Toasty.LENGTH_SHORT, true).show();
    }


    public static void saveDataInSharedPrefs(Context context, String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply();
    }

    public static String getDataFromSharedPrefs(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, "");
    }


    public static void clearSharedPrefs(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("email", "").apply();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("password", "").apply();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("type", "").apply();
    }


    public static boolean isFirstLogin(Context applicationContext) {
        if (getDataFromSharedPrefs(applicationContext, "LOGIN").isEmpty()) {
            saveDataInSharedPrefs(applicationContext, "LOGIN", "FALSE");
            return true;
        } else return false;
    }


    public static int randomNumberGenerator(int minimum, int maximum) {
        int i = (int) (Math.random() * maximum);
        if (i < minimum) {
            return i + minimum;
        } else {
            return i;
        }
    }
}
