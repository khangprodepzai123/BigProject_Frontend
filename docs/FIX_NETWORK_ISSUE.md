# Sửa lỗi Kết nối Mạng - Backend chỉ listen trên localhost

## Vấn đề
Backend đang chỉ listen trên `127.0.0.1:5237` (localhost), nên điện thoại không thể truy cập được.

## Giải pháp
Sửa `launchSettings.json` để backend listen trên `0.0.0.0` (tất cả interfaces).

## Đã sửa
File `65131433_BTL1/65131433_BTL1/Properties/launchSettings.json`:
- Đổi `http://localhost:5237` → `http://0.0.0.0:5237`

## Các bước tiếp theo

### 1. Restart Backend
Dừng backend hiện tại (Ctrl+C) và chạy lại:
```bash
cd 65131433_BTL1/65131433_BTL1
dotnet run
```

### 2. Kiểm tra Backend đang listen trên tất cả interfaces
Chạy lệnh:
```cmd
netstat -an | findstr ":5237"
```

Bạn sẽ thấy:
```
TCP    0.0.0.0:5237         0.0.0.0:0              LISTENING
```
Thay vì chỉ:
```
TCP    127.0.0.1:5237         0.0.0.0:0              LISTENING
```

### 3. Kiểm tra Firewall
Windows Firewall có thể chặn port 5237. Cần:
1. Mở Windows Defender Firewall
2. Advanced Settings
3. Inbound Rules → New Rule
4. Port → TCP → Specific local ports: 5237
5. Allow the connection
6. Apply to all profiles
7. Name: "Allow ASP.NET Core Port 5237"

Hoặc tạm thời tắt firewall để test.

### 4. Test từ điện thoại
Mở browser trên điện thoại Samsung và truy cập:
```
http://10.160.2.122:5237
```

Nếu thấy trang web backend → ✅ Kết nối OK!

### 5. Rebuild và chạy lại Android app
- Clean project
- Rebuild
- Chạy lại trên điện thoại

## Lưu ý
- Đảm bảo điện thoại và máy tính cùng mạng WiFi
- Không dùng dữ liệu di động trên điện thoại
- IP có thể thay đổi nếu router cấp IP động

## Kiểm tra IP mới (nếu cần)
```cmd
ipconfig | findstr /i "IPv4"
```

Cập nhật IP trong `Constants.java` nếu IP thay đổi.

