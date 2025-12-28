# UML Diagrams - Phòng Khám Online

Thư mục này chứa các file PlantUML để mô tả kiến trúc và chức năng của hệ thống Phòng Khám Online.

## Cài đặt PlantUML

### Cách 1: Sử dụng VS Code Extension
1. Cài đặt extension "PlantUML" trong VS Code
2. Mở file `.plantuml` và nhấn `Alt + D` để preview

### Cách 2: Sử dụng Online Editor
1. Truy cập: https://www.plantuml.com/plantuml/uml/
2. Copy nội dung file `.plantuml` và paste vào editor
3. Xem kết quả render

### Cách 3: Sử dụng IntelliJ IDEA / Android Studio
1. Cài đặt plugin "PlantUML integration"
2. Mở file `.plantuml` và preview trực tiếp trong IDE

## Danh sách Diagrams

### 1. Use Case Diagrams (tách theo từng loại user)

#### 1.1. Use Case - Bệnh nhân (`use_case_patient.plantuml`)
Mô tả các use case dành cho **Bệnh nhân**:
- **Actor:** Bệnh nhân (Patient)
- **Use Cases chính:**
  - Xác thực (Đăng nhập, Đăng xuất, Đăng ký tài khoản)
  - Quản lý thông tin cá nhân
  - Đăng ký khám bệnh (qua Android)
  - Xem bệnh án và chi tiết
  - Xem toa thuốc hiện tại và lịch sử
  - Xem hóa đơn và chi tiết
  - Xem điểm tích lũy và mức ưu đãi
  - Xem thông tin bác sĩ và tìm kiếm

#### 1.2. Use Case - Bác sĩ (`use_case_doctor.plantuml`)
Mô tả các use case dành cho **Bác sĩ**:
- **Actor:** Bác sĩ (Doctor)
- **Use Cases chính:**
  - Xác thực (Đăng nhập, Đăng xuất)
  - Khám bệnh và chẩn đoán
  - Kê toa thuốc
  - Lưu bệnh án
  - Xem và tìm kiếm bệnh án
  - Quản lý thông tin cá nhân

#### 1.3. Use Case - Nhân viên (`use_case_staff.plantuml`)
Mô tả các use case dành cho **Nhân viên**:
- **Actor:** Nhân viên (Staff)
- **Use Cases chính:**
  - Xác thực (Đăng nhập, Đăng xuất)
  - Quản lý bệnh nhân (Xem, Tìm kiếm, Tạo phiếu khám, Khám lại)
  - Hỗ trợ khám bệnh và kê toa
  - Quản lý hóa đơn và thanh toán
  - Quản lý tủ bệnh án
  - Quản lý tài khoản bệnh nhân
  - Quản lý thông tin bác sĩ (CRUD)

### 2. Activity Diagrams (Quy trình nghiệp vụ)

#### 2.1. Đăng nhập và Đăng ký (`activity_login_signup.plantuml`)
Mô tả quy trình đăng nhập và đăng ký tài khoản:
- Đăng nhập với tài khoản đã có
- Đăng ký tài khoản mới
- Xử lý JWT token
- Lưu thông tin vào SharedPreferences

#### 2.2. Đăng ký khám bệnh (`activity_register_kham.plantuml`)
Mô tả quy trình đăng ký khám bệnh từ Android:
- Nhập thông tin bệnh nhân
- Validation dữ liệu
- Tạo bệnh nhân mới hoặc sử dụng bệnh nhân đã có
- Tạo phiếu khám
- Liên kết với tài khoản (nếu có)

#### 2.3. Khám bệnh (`activity_kham_benh.plantuml`)
Mô tả quy trình khám bệnh trên web:
- Tạo phiếu khám mới hoặc khám lại
- Nhập thông tin khám
- Kê toa thuốc
- Lưu bệnh án

#### 2.4. Thanh toán (`activity_thanh_toan.plantuml`)
Mô tả quy trình thanh toán hóa đơn:
- Tính toán chi phí
- Áp dụng giảm giá BHYT (nếu có)
- Áp dụng giảm giá điểm tích lũy (nếu có)
- Tạo hóa đơn và chi tiết
- Cập nhật số lượng thuốc trong kho
- Cập nhật điểm tích lũy

#### 2.5. Xem Bệnh án và Toa thuốc (`activity_view_benh_an.plantuml`)
Mô tả quy trình xem bệnh án và toa thuốc trên Android:
- Xem danh sách bệnh án
- Xem chi tiết bệnh án
- Xem toa thuốc hiện tại (từ lần khám đã thanh toán gần nhất)
- Parse liều dùng để hiển thị số lần uống mỗi ngày

### 3. Data Flow Diagrams (DFD)

#### 3.1. DFD Mức Ngữ cảnh (`dfd_context.plantuml`)
Mô tả hệ thống ở mức tổng quan nhất:
- **External Entities**: Bệnh nhân, Bác sĩ, Nhân viên
- **System**: Hệ thống Phòng Khám Online
- **Data Flows**: Các luồng dữ liệu giữa external entities và system

#### 3.2. DFD Mức 0 (`dfd_level0.plantuml`)
Mô tả hệ thống với các process chính:
- **Processes**:
  - 1.0 Quản lý Xác thực
  - 2.0 Quản lý Bệnh nhân
  - 3.0 Khám bệnh và Kê toa
  - 4.0 Thanh toán Hóa đơn
  - 5.0 Quản lý Bệnh án
  - 6.0 Quản lý Điểm tích lũy
- **Data Stores**: D1-D9 (Tài khoản, Bệnh nhân, Phiếu khám, Toa thuốc, Bệnh án, Hóa đơn, Thuốc, Bác sĩ, Mức ưu đãi)
- **Data Flows**: Luồng dữ liệu giữa processes, data stores và external entities

#### 3.3. DFD Mức 1 (tách thành nhiều file)
Chi tiết hóa các process từ mức 0, mỗi process một file riêng:

- **Process 1.0 - Quản lý Xác thực** (`dfd_level1_p1_xac_thuc.plantuml`):
  - 1.1 Xác thực Đăng nhập
  - 1.2 Đăng ký Tài khoản
  - 1.3 Tạo JWT Token

- **Process 2.0 - Quản lý Bệnh nhân** (`dfd_level1_p2_benh_nhan.plantuml`):
  - 2.1 Đăng ký Khám bệnh
  - 2.2 Tạo Bệnh nhân Mới
  - 2.3 Tạo Phiếu Khám
  - 2.4 Liên kết Tài khoản

- **Process 3.0 - Khám bệnh và Kê toa** (`dfd_level1_p3_kham_benh.plantuml`):
  - 3.1 Nhập thông tin Khám
  - 3.2 Kê Toa Thuốc
  - 3.3 Cập nhật Trạng thái

- **Process 4.0 - Thanh toán Hóa đơn** (`dfd_level1_p4_thanh_toan.plantuml`):
  - 4.1 Tính toán Chi phí
  - 4.2 Áp dụng Giảm giá
  - 4.3 Tạo Hóa đơn và Chi tiết
  - 4.4 Cập nhật Điểm tích lũy

- **Process 5.0 - Quản lý Bệnh án** (`dfd_level1_p5_benh_an.plantuml`):
  - 5.1 Lưu Bệnh án
  - 5.2 Xem Bệnh án
  - 5.3 Parse Liều Dùng

- **Process 6.0 - Quản lý Điểm tích lũy** (`dfd_level1_p6_diem_tich_luy.plantuml`):
  - 6.1 Xác định Mức ưu đãi
  - 6.2 Tính Giảm giá Điểm
- **1.0 Quản lý Xác thực**:
  - 1.1 Xác thực Đăng nhập
  - 1.2 Đăng ký Tài khoản
  - 1.3 Tạo JWT Token
- **2.0 Quản lý Bệnh nhân**:
  - 2.1 Đăng ký Khám bệnh
  - 2.2 Tạo Bệnh nhân Mới
  - 2.3 Tạo Phiếu Khám
  - 2.4 Liên kết Tài khoản
- **3.0 Khám bệnh và Kê toa**:
  - 3.1 Nhập thông tin Khám
  - 3.2 Kê Toa Thuốc
  - 3.3 Cập nhật Trạng thái
- **4.0 Thanh toán Hóa đơn**:
  - 4.1 Tính toán Chi phí
  - 4.2 Áp dụng Giảm giá
  - 4.3 Tạo Hóa đơn và Chi tiết
  - 4.4 Cập nhật Điểm tích lũy
- **5.0 Quản lý Bệnh án**:
  - 5.1 Lưu Bệnh án
  - 5.2 Xem Bệnh án
  - 5.3 Parse Liều Dùng
- **6.0 Quản lý Điểm tích lũy**:
  - 6.1 Xác định Mức ưu đãi
  - 6.2 Tính Giảm giá Điểm

## Cấu trúc thư mục

```
docs/
├── uml/
│   ├── README.md
│   ├── use_case_patient.plantuml
│   ├── use_case_doctor.plantuml
│   ├── use_case_staff.plantuml
│   ├── activity_login_signup.plantuml
│   ├── activity_register_kham.plantuml
│   ├── activity_kham_benh.plantuml
│   ├── activity_thanh_toan.plantuml
│   ├── activity_view_benh_an.plantuml
│   ├── dfd_context.plantuml
│   ├── dfd_level0.plantuml
│   ├── dfd_level1_p1_xac_thuc.plantuml
│   ├── dfd_level1_p2_benh_nhan.plantuml
│   ├── dfd_level1_p3_kham_benh.plantuml
│   ├── dfd_level1_p4_thanh_toan.plantuml
│   ├── dfd_level1_p5_benh_an.plantuml
│   └── dfd_level1_p6_diem_tich_luy.plantuml
├── chuc_nang_nghiep_vu.md
├── chuc_nang_he_thong.md
└── yeu_cau_phi_chuc_nang.md
```

## Ghi chú

- Tất cả các file PlantUML sử dụng encoding UTF-8
- Các use case được nhóm theo package để dễ quản lý
- Màu sắc được định nghĩa để phân biệt các actors

