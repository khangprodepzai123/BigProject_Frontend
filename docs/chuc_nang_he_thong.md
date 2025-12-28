# Tài liệu Chức năng Hệ thống
## Hệ Thống Phòng Khám Online

---

## 1. TỔNG QUAN

Tài liệu này mô tả chi tiết các chức năng kỹ thuật của hệ thống Phòng Khám Online, bao gồm các module, API, database, và các thành phần kỹ thuật khác.

---

## 2. KIẾN TRÚC HỆ THỐNG

### 2.1. Kiến trúc Tổng thể
- **Backend**: ASP.NET Core (C#) với Entity Framework Core
- **Frontend Web**: Razor Pages
- **Mobile App**: Android (Java) với Retrofit
- **Database**: SQL Server
- **Authentication**: JWT (JSON Web Tokens)

### 2.2. Các Layer
1. **Presentation Layer**: Razor Pages (Web), Android Activities (Mobile)
2. **Business Logic Layer**: Controllers, ViewModels, Services
3. **Data Access Layer**: Entity Framework Core, DbContext
4. **Database Layer**: SQL Server

---

## 3. CÁC MODULE CHỨC NĂNG

### 3.1. Module Xác thực (Authentication)

#### 3.1.1. API Endpoints
- `POST /api/Auth/login`: Đăng nhập
  - Input: `{ tenDangNhap, matKhau }`
  - Output: `{ success, message, data: { token, maTk, maBn, hoTen, diemTichLuy } }`
  
- `POST /api/Auth/signup`: Đăng ký tài khoản
  - Input: `{ tenDangNhap, matKhau }`
  - Output: `{ success, message, data: { token, maTk } }`
  
- `POST /api/Auth/logout`: Đăng xuất
  - Output: `{ success, message }`
  
- `GET /api/Auth/me`: Lấy thông tin tài khoản hiện tại
  - Header: `Authorization: Bearer {token}`
  - Output: `{ success, message, data: { maTk, tenDangNhap, maBn, hoTen, diemTichLuy } }`

#### 3.1.2. JWT Service
- **Tạo token**: Sinh JWT token với claims (maTk, tenDangNhap, exp, iss, aud)
- **Validate token**: Kiểm tra tính hợp lệ và thời gian hết hạn của token
- **Extract claims**: Lấy thông tin từ token (maTk, tenDangNhap)

#### 3.1.3. SharedPreferences (Android)
- Lưu token, maTk, maBn, hoTen, diemTichLuy
- Xóa dữ liệu khi đăng xuất

---

### 3.2. Module Quản lý Bệnh nhân

#### 3.2.1. API Endpoints
- `POST /api/BenhNhan/register`: Đăng ký khám bệnh
  - Header: `Authorization: Bearer {token}` (tùy chọn)
  - Input: `{ hoTenBn, sdt, ngaySinh, gt, doiTuong, diaChi, bhyt, lyDoKham, thoiGianHenKham }`
  - Output: `{ success, message, data: { maBn, maKham, hoTenBn, thoiGianHenKham } }`
  - Logic:
    - Kiểm tra bệnh nhân đã tồn tại (theo SDT)
    - Tạo mới hoặc sử dụng bệnh nhân đã có
    - Tạo phiếu khám mới
    - Liên kết tài khoản (nếu có token)

#### 3.2.2. Web Pages
- `/DanhSachBenhNhan`: Danh sách bệnh nhân
  - Hiển thị danh sách với trạng thái khám
  - Tìm kiếm bệnh nhân
  - Nút "Khám mới" và "Khám lại"

---

### 3.3. Module Khám bệnh

#### 3.3.1. Web Pages
- `/KhamBenh?maKham={maKham}`: Trang khám bệnh
  - Hiển thị thông tin bệnh nhân
  - Form nhập thông tin khám
  - Danh sách bác sĩ (dropdown)
  - Danh sách chuẩn đoán (dropdown)
  - Form kê toa thuốc
  - Nút "Lưu thông tin khám"

#### 3.3.2. Logic xử lý
- **Tạo phiếu khám mới**: Sinh mã KB001, KB002...
- **Khám lại**: Xóa phiếu khám cũ và dữ liệu liên quan (ToaThuoc, HoaDon, ChiTietHoaDon)
- **Lưu thông tin khám**: Cập nhật KhamBenh với trạng thái "Đã khám"
- **Kê toa thuốc**: Lưu danh sách thuốc vào ToaThuoc

---

### 3.4. Module Bệnh án

#### 3.4.1. API Endpoints
- `GET /api/BenhAn/me`: Lấy danh sách bệnh án của bệnh nhân
  - Header: `Authorization: Bearer {token}`
  - Output: `{ success, message, data: [ { maBenhAn, maKham, ngayKham, ngayLuu, bacSi, chuanDoan, toaThuoc: [...] } ] }`

- `GET /api/BenhAn/toathuoc-hientai`: Lấy toa thuốc hiện tại
  - Header: `Authorization: Bearer {token}`
  - Output: `{ success, message, data: { maBenhAn, maKham, ngayKham, ngayLuu, bacSi, chuanDoan, toaThuoc: [ { maThuoc, tenThuoc, soLuong, lieuDung, cachDung, soLanUongMoiNgay } ] } }`
  - Logic:
    - Tìm phiếu khám đã thanh toán gần nhất
    - Parse liều dùng để tính số lần uống mỗi ngày (Regex)

#### 3.4.2. Web Pages
- `/DanhSachBenhAn`: Tủ bệnh án
  - Hiển thị bệnh án nhóm theo bệnh nhân
  - Tìm kiếm bệnh án
  - Xem chi tiết bệnh án

#### 3.4.3. Android Activities
- `BenhAnActivity`: Hiển thị danh sách bệnh án
- `ToaThuocHienTaiActivity`: Hiển thị toa thuốc hiện tại

---

### 3.5. Module Hóa đơn

#### 3.5.1. API Endpoints
- `GET /api/HoaDon/me`: Lấy danh sách hóa đơn của bệnh nhân
  - Header: `Authorization: Bearer {token}`
  - Output: `{ success, message, data: [ { maHoaDon, ngayLap, tongTien, trangThai, chiTiet: [...] } ] }`

#### 3.5.2. Web Pages
- `/ThanhToan?maBn={maBn}`: Trang thanh toán
  - Hiển thị thông tin bệnh nhân
  - Tính toán chi phí
  - Áp dụng giảm giá BHYT (80%)
  - Áp dụng giảm giá điểm tích lũy (10%)
  - Tạo hóa đơn và chi tiết
  - Cập nhật số lượng thuốc trong kho
  - Cập nhật điểm tích lũy

#### 3.5.3. Android Activities
- `HoaDonActivity`: Hiển thị danh sách hóa đơn

---

### 3.6. Module Điểm tích lũy

#### 3.6.1. API Endpoints
- `GET /api/DiemTichLuy/me`: Lấy thông tin điểm tích lũy
  - Header: `Authorization: Bearer {token}`
  - Output: `{ success, message, data: { diemTichLuy, mucUuDai: { tenMuc, diemToiThieu, phanTramGiamGia, moTa }, phanTramGiamGiaHienTai } }`
  - Logic:
    - Lấy điểm tích lũy từ TaiKhoanBenhNhan
    - Xác định mức ưu đãi từ MucUuDai
    - Tính phần trăm giảm giá hiện tại (10% nếu có điểm >= 1)

#### 3.6.2. Database
- Bảng `MucUuDai`: Lưu các mức ưu đãi (Đồng, Bạc, Vàng, Kim cương)
- Cột `DiemTichLuy` trong `TaiKhoanBenhNhan`: Lưu điểm tích lũy

#### 3.6.3. Android Activities
- `DiemTichLuyActivity`: Hiển thị điểm tích lũy và mức ưu đãi

---

### 3.7. Module Bác sĩ

#### 3.7.1. API Endpoints
- `GET /api/BacSi`: Lấy danh sách tất cả bác sĩ
  - Output: `{ success, message, data: [ { maBs, hoTenBs, trinhDoHocVan, chuyenKhoa, tuoi, kinhNghiem, chungChiHanhNghe } ] }`

- `GET /api/BacSi/{maBs}`: Lấy thông tin chi tiết bác sĩ
  - Output: `{ success, message, data: { ... } }`

#### 3.7.2. Web Pages
- `/DanhSachBacSi`: Danh sách bác sĩ
  - Hiển thị thông tin bác sĩ
  - Tìm kiếm bác sĩ

#### 3.7.3. Android Activities
- `DanhSachBacSiActivity`: Hiển thị danh sách bác sĩ

---

### 3.8. Module Quản lý Tài khoản

#### 3.8.1. Web Pages
- `/DanhSachTaiKhoan`: Danh sách tài khoản
  - Hiển thị tất cả tài khoản đã đăng ký
  - Hiển thị mã bệnh nhân và tên (nếu đã liên kết)
  - Tìm kiếm tài khoản

---

## 4. DATABASE SCHEMA

### 4.1. Các Bảng chính

#### 4.1.1. TaiKhoanBenhNhan
- `MaTk` (PK): Mã tài khoản
- `TenDangNhap`: Tên đăng nhập
- `MatKhau`: Mật khẩu (đã mã hóa)
- `MaBn` (FK): Mã bệnh nhân (nullable)
- `DiemTichLuy`: Điểm tích lũy (default: 0)

#### 4.1.2. BenhNhan
- `MaBn` (PK): Mã bệnh nhân
- `HoTenBn`: Họ tên
- `SDT`: Số điện thoại
- `NgaySinh`: Ngày sinh
- `GT`: Giới tính
- `DoiTuong`: Đối tượng
- `DiaChi`: Địa chỉ
- `BHYT`: Bảo hiểm y tế

#### 4.1.3. KhamBenh
- `MaKham` (PK): Mã phiếu khám
- `MaBn` (FK): Mã bệnh nhân
- `MaBs` (FK): Mã bác sĩ
- `MaCd` (FK): Mã chuẩn đoán
- `NgayKham`: Ngày khám
- `TrangThai`: Trạng thái ("Đang khám", "Đã khám", "Đã thanh toán")
- Các trường thông tin khám khác

#### 4.1.4. ToaThuoc
- `MaKham` (FK): Mã phiếu khám
- `MaThuoc` (FK): Mã thuốc
- `SoLuong`: Số lượng
- `LieuDung`: Liều dùng
- `CachDung`: Cách dùng

#### 4.1.5. BenhAn
- `MaBenhAn` (PK): Mã bệnh án
- `MaKham` (FK): Mã phiếu khám
- `MaBn` (FK): Mã bệnh nhân
- `MaBs` (FK): Mã bác sĩ
- `NgayKham`: Ngày khám
- `NgayLuu`: Ngày lưu
- Các trường thông tin khám

#### 4.1.6. BenhAnToaThuoc
- `MaBenhAn` (FK): Mã bệnh án
- `MaThuoc` (FK): Mã thuốc
- `SoLuong`: Số lượng
- `LieuDung`: Liều dùng
- `CachDung`: Cách dùng

#### 4.1.7. HoaDon
- `MaHoaDon` (PK): Mã hóa đơn
- `MaKham` (FK): Mã phiếu khám
- `NgayLap`: Ngày lập
- `TongTien`: Tổng tiền
- `TrangThai`: Trạng thái

#### 4.1.8. ChiTietHoaDon
- `MaHoaDon` (FK): Mã hóa đơn
- `MaThuoc` (FK): Mã thuốc
- `SoLuong`: Số lượng
- `DonGia`: Đơn giá
- `ThanhTien`: Thành tiền

#### 4.1.9. Thuoc
- `MaThuoc` (PK): Mã thuốc
- `TenThuoc`: Tên thuốc
- `GiaBan`: Giá bán
- `SoLuong`: Số lượng trong kho

#### 4.1.10. BacSi
- `MaBs` (PK): Mã bác sĩ
- `HoTenBs`: Họ tên
- `TrinhDoHocVan`: Trình độ học vấn
- `ChuyenKhoa`: Chuyên khoa
- `Tuoi`: Tuổi
- `KinhNghiem`: Kinh nghiệm
- `ChungChiHanhNghe`: Chứng chỉ hành nghề

#### 4.1.11. MucUuDai
- `MaMuc` (PK): Mã mức
- `TenMuc`: Tên mức (Đồng, Bạc, Vàng, Kim cương)
- `DiemToiThieu`: Điểm tối thiểu
- `PhanTramGiamGia`: Phần trăm giảm giá
- `MoTa`: Mô tả

### 4.2. Constraints
- UNIQUE constraint trên `MaBn` trong `KhamBenh` (mỗi bệnh nhân chỉ có 1 phiếu khám chưa thanh toán)
- Foreign key constraints giữa các bảng
- NOT NULL constraints trên các trường bắt buộc

---

## 5. XỬ LÝ LỖI VÀ VALIDATION

### 5.1. Validation
- **Client-side**: Android và Web validate dữ liệu trước khi gửi
- **Server-side**: API validate dữ liệu và trả về lỗi chi tiết

### 5.2. Error Handling
- **HTTP Status Codes**: 200 (OK), 400 (Bad Request), 401 (Unauthorized), 500 (Internal Server Error)
- **Error Response Format**: `{ success: false, message: "Mô tả lỗi" }`
- **Logging**: Ghi log lỗi trên server để debug

---

## 6. BẢO MẬT

### 6.1. Authentication
- JWT token với thời gian hết hạn
- Token được lưu trong SharedPreferences (Android) và HttpOnly cookie (Web)

### 6.2. Authorization
- Kiểm tra token trong mỗi API request
- Bệnh nhân chỉ có thể xem dữ liệu của chính mình

### 6.3. Data Protection
- Mật khẩu được mã hóa (hash) trước khi lưu
- SQL injection prevention (Entity Framework parameterized queries)
- XSS prevention (Razor Pages auto-encoding)

---

## 7. PERFORMANCE

### 7.1. Database Optimization
- Indexes trên các cột thường query (MaBn, MaKham, MaTk)
- Eager loading với Include() để giảm số lần query

### 7.2. Caching
- Có thể cache danh sách bác sĩ, thuốc (ít thay đổi)

---

## 8. KẾT LUẬN

Tài liệu này mô tả đầy đủ các chức năng kỹ thuật của hệ thống, bao gồm API endpoints, database schema, và các thành phần kỹ thuật khác. Hệ thống được thiết kế với kiến trúc rõ ràng, dễ bảo trì và mở rộng.

