# Hướng dẫn Debug "Toa thuốc hiện tại" trên Android

## Vấn đề: API test thành công nhưng Android không hiển thị

## Các bước debug:

### 1. Kiểm tra Logcat trong Android Studio

Mở **Logcat** trong Android Studio và filter theo tag:
- `ToaThuocHienTai`
- `BenhAnRepository`

Bạn sẽ thấy các log như:
```
D/ToaThuocHienTai: Token from SharedPref: exists
D/ToaThuocHienTai: Calling API with token: Bearer eyJhbGci...
D/BenhAnRepository: Calling API with token: Bearer eyJhbGci...
D/BenhAnRepository: Response code: 200
D/BenhAnRepository: Response success: true
D/BenhAnRepository: Data not null, toaThuoc size: 1
D/ToaThuocHienTai: Response received: not null
D/ToaThuocHienTai: Response success: true
D/ToaThuocHienTai: Displaying toa thuoc with 1 items
```

### 2. Các trường hợp có thể xảy ra:

#### Trường hợp 1: Token null hoặc rỗng
**Log sẽ hiển thị:**
```
D/ToaThuocHienTai: Token from SharedPref: null
```
**Giải pháp:**
- Đăng nhập lại trong app
- Kiểm tra xem token có được lưu đúng không

#### Trường hợp 2: API call thất bại
**Log sẽ hiển thị:**
```
E/BenhAnRepository: Network error
```
hoặc
```
D/BenhAnRepository: Response code: 401
```
**Giải pháp:**
- Kiểm tra kết nối mạng
- Đăng nhập lại để lấy token mới
- Kiểm tra BASE_URL trong Constants.java

#### Trường hợp 3: Response thành công nhưng data null
**Log sẽ hiển thị:**
```
D/BenhAnRepository: Response success: true
D/BenhAnRepository: Data is null
```
**Giải pháp:**
- Kiểm tra xem API có trả về `data: null` không
- Có thể là chưa có toa thuốc (đúng behavior)

#### Trường hợp 4: Response thành công, có data nhưng toaThuoc rỗng
**Log sẽ hiển thị:**
```
D/BenhAnRepository: Data not null, toaThuoc size: 0
D/ToaThuocHienTai: No toa thuoc data or empty list
```
**Giải pháp:**
- Kiểm tra database xem có toa thuốc không
- Chạy script `CheckToaThuocHienTai.sql`

#### Trường hợp 5: Response parse lỗi
**Log sẽ hiển thị:**
```
D/BenhAnRepository: Response code: 200
```
Nhưng không có log "Response success"
**Giải pháp:**
- Có thể JSON structure không khớp với model
- Kiểm tra response body trong logcat

### 3. Kiểm tra Token

**Cách 1: Xem trong Logcat**
- Tìm log: `D/ToaThuocHienTai: Calling API with token: Bearer ...`
- Copy token và test trên Postman

**Cách 2: Thêm log tạm thời**
Trong `ToaThuocHienTaiActivity.java`, thêm:
```java
String token = prefManager.getToken();
Log.d("DEBUG", "Full token: " + token);
```

### 4. Test trực tiếp với Postman

1. Lấy token từ Logcat hoặc từ SharedPreferences
2. Test API trên Postman với token đó
3. So sánh response với response trong Logcat

### 5. Kiểm tra Response Body

Nếu cần xem full response body, thêm log trong `BenhAnRepository.java`:
```java
if (response.isSuccessful() && response.body() != null) {
    // Log full response
    android.util.Log.d("BenhAnRepository", "Full response: " + 
        new com.google.gson.Gson().toJson(response.body()));
    responseLiveData.setValue(response.body());
}
```

### 6. Kiểm tra Observer

Đảm bảo observer được đăng ký trước khi gọi API:
```java
observeViewModel(); // Phải gọi trước
loadToaThuoc();     // Sau đó mới load
```

## Checklist Debug:

- [ ] Logcat hiển thị token không null
- [ ] Logcat hiển thị "Calling API with token"
- [ ] Logcat hiển thị "Response code: 200"
- [ ] Logcat hiển thị "Response success: true"
- [ ] Logcat hiển thị "Data not null"
- [ ] Logcat hiển thị "toaThuoc size: > 0"
- [ ] Logcat hiển thị "Displaying toa thuoc with X items"
- [ ] RecyclerView được hiển thị (không phải tvEmpty)

## Nếu vẫn không hiển thị:

1. **Kiểm tra Layout:**
   - Đảm bảo `recyclerViewToaThuoc` có trong layout
   - Đảm bảo `item_toa_thuoc_hien_tai.xml` tồn tại

2. **Kiểm tra Adapter:**
   - Đảm bảo `updateList()` được gọi
   - Đảm bảo `notifyDataSetChanged()` được gọi

3. **Kiểm tra Visibility:**
   - `recyclerView.setVisibility(View.VISIBLE)`
   - `tvEmpty.setVisibility(View.GONE)`

## Gửi Logcat cho tôi:

Nếu vẫn không được, vui lòng:
1. Mở Logcat
2. Filter: `ToaThuocHienTai|BenhAnRepository`
3. Copy toàn bộ log từ khi mở activity đến khi có response
4. Gửi cho tôi để debug tiếp



