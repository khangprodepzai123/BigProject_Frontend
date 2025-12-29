package com.example.a65131433_bigproject.utils;

public class Constants {
    // Base URL của Web API
    // IP của máy tính (thay đổi nếu IP thay đổi)
    // Lưu ý: 
    // - Nếu dùng Emulator: dùng "http://10.0.2.2:5237/"
    // - Nếu dùng điện thoại thật: dùng IP thực của máy tính (ví dụ: "http://10.160.2.122:5237/")
    // - Đảm bảo điện thoại và máy tính cùng mạng WiFi
    public static final String BASE_URL = "http://10.160.2.122:5237/";  // ← IP máy tính của bạn

    // Endpoints
    public static final String API_SIGNUP = "api/auth/signup";
    public static final String API_LOGIN = "api/auth/login";
    public static final String API_ME = "api/auth/me";

    // SharedPreferences Keys
    public static final String PREF_NAME = "PhongKhamAppPref";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_MA_BN = "maBn";
    public static final String KEY_HO_TEN = "hoTen";
    public static final String KEY_DIEM_TICH_LUY = "diemTichLuy";
    public static final String KEY_MA_TK = "maTk";
}
