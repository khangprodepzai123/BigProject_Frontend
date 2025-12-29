# Hướng dẫn Sửa lỗi Kết nối Mạng - Android

## Vấn đề
Khi chạy app Android trên điện thoại thật (không phải emulator), gặp lỗi "Kết nối mạng" khi đăng nhập.

## Nguyên nhân
- File `Constants.java` đang dùng `10.0.2.2` - đây là IP đặc biệt chỉ dành cho **Android Emulator**
- Khi dùng điện thoại thật, cần dùng **IP thực của máy tính**

## Cách sửa

### Bước 1: Lấy IP của máy tính

**Windows:**
```cmd
ipconfig
```
Tìm dòng `IPv4 Address` (ví dụ: `192.168.1.100` hoặc `10.160.2.122`)

**Mac/Linux:**
```bash
ifconfig
# hoặc
ip addr show
```

### Bước 2: Sửa file Constants.java

Mở file: `app/src/main/java/com/example/a65131433_bigproject/utils/Constants.java`

**Trước (cho Emulator):**
```java
public static final String BASE_URL = "http://10.0.2.2:5237/";
```

**Sau (cho điện thoại thật):**
```java
public static final String BASE_URL = "http://10.160.2.122:5237/";  // ← Thay bằng IP của bạn
```

### Bước 3: Đảm bảo Backend đang chạy

1. Chạy backend trên máy tính:
   ```bash
   cd 65131433_BTL1/65131433_BTL1
   dotnet run
   ```

2. Kiểm tra backend chạy trên port nào (thường là `5237` hoặc `5000`)

3. Kiểm tra URL trong browser:
   - `http://localhost:5237` (trên máy tính)
   - `http://10.160.2.122:5237` (từ điện thoại, nếu cùng mạng)

### Bước 4: Đảm bảo cùng mạng WiFi

- **Điện thoại** và **Máy tính** phải cùng kết nối một mạng WiFi
- Không dùng dữ liệu di động trên điện thoại

### Bước 5: Kiểm tra Firewall

**Windows:**
1. Mở Windows Defender Firewall
2. Cho phép ứng dụng qua firewall
3. Hoặc tạm thời tắt firewall để test

**Mac:**
1. System Preferences → Security & Privacy → Firewall
2. Cho phép ứng dụng

### Bước 6: Rebuild và chạy lại app

1. Clean project trong Android Studio
2. Rebuild project
3. Chạy lại app trên điện thoại

## Kiểm tra kết nối

### Cách 1: Dùng Browser trên điện thoại
Mở browser trên điện thoại và truy cập:
```
http://10.160.2.122:5237
```
Nếu thấy trang web backend → Kết nối OK

### Cách 2: Dùng Ping
Trên điện thoại (nếu có terminal):
```bash
ping 10.160.2.122
```

### Cách 3: Xem Logcat
Trong Android Studio, mở Logcat và filter theo tag:
- `OkHttp` - để xem request/response
- `Retrofit` - để xem API calls
- Tìm lỗi `UnknownHostException` hoặc `ConnectException`

## Lưu ý

1. **IP có thể thay đổi**: Nếu router cấp IP động, IP có thể thay đổi mỗi lần kết nối WiFi. Cần cập nhật lại trong `Constants.java`.

2. **Port**: Đảm bảo port trong `BASE_URL` khớp với port backend đang chạy (thường là `5237` hoặc `5000`).

3. **HTTPS**: Nếu backend dùng HTTPS, cần thay `http://` thành `https://`.

4. **CORS**: Đảm bảo backend đã cấu hình CORS để cho phép request từ Android app.

## Debug thêm

Thêm logging trong Retrofit để xem chi tiết request/response:

File `RetrofitClient.java` đã có `HttpLoggingInterceptor` với level `BODY`. Xem log trong Logcat với filter:
```
tag:OkHttp
```

Nếu thấy request được gửi nhưng không có response → Vấn đề kết nối mạng
Nếu thấy response lỗi (400, 401, 500) → Vấn đề backend/authentication

