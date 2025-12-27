package com.example.a65131433_bigproject.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static SharedPrefManager instance;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(
                Constants.PREF_NAME,
                Context.MODE_PRIVATE
        );
        editor = sharedPreferences.edit();
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    // ===== SAVE =====
    public void saveToken(String token) {
        editor.putString(Constants.KEY_TOKEN, token).apply();
    }

    public void saveUsername(String username) {
        editor.putString(Constants.KEY_USERNAME, username).apply();
    }

    public void saveMaBn(String maBn) {
        editor.putString(Constants.KEY_MA_BN, maBn).apply();
    }

    public void saveHoTen(String hoTen) {
        editor.putString(Constants.KEY_HO_TEN, hoTen).apply();
    }

    public void saveDiemTichLuy(int diem) {
        editor.putInt(Constants.KEY_DIEM_TICH_LUY, diem).apply();
    }

    public void saveMaTk(String maTk) {
        editor.putString(Constants.KEY_MA_TK, maTk).apply();
    }

    public void saveUserInfo(String maTk, String username, String maBn, String hoTen, int diem) {
        editor.putString(Constants.KEY_MA_TK, maTk).apply();
        editor.putString(Constants.KEY_USERNAME, username).apply();
        editor.putString(Constants.KEY_MA_BN, maBn).apply();
        editor.putString(Constants.KEY_HO_TEN, hoTen).apply();
        editor.putInt(Constants.KEY_DIEM_TICH_LUY, diem).apply();
    }

    // ===== GET =====
    public String getToken() {
        return sharedPreferences.getString(Constants.KEY_TOKEN, "");
    }

    public String getUsername() {
        return sharedPreferences.getString(Constants.KEY_USERNAME, "");
    }

    public String getMaBn() {
        return sharedPreferences.getString(Constants.KEY_MA_BN, "");
    }

    public String getHoTen() {
        return sharedPreferences.getString(Constants.KEY_HO_TEN, "");
    }

    public int getDiemTichLuy() {
        return sharedPreferences.getInt(Constants.KEY_DIEM_TICH_LUY, 0);
    }

    public String getMaTk() {
        return sharedPreferences.getString(Constants.KEY_MA_TK, "");
    }

    // ===== CHECK =====
    public boolean isLoggedIn() {
        return !getToken().isEmpty();
    }

    // ===== CLEAR =====
    public void logout() {
        editor.clear().apply();
    }
}