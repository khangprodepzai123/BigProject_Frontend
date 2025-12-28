# Tài liệu Yêu cầu Phi chức năng
## Hệ Thống Phòng Khám Online

---

## 1. TỔNG QUAN

Tài liệu này mô tả các yêu cầu phi chức năng (Non-functional Requirements) của hệ thống Phòng Khám Online, bao gồm hiệu năng, bảo mật, khả năng mở rộng, khả năng sử dụng, và các yêu cầu khác.

---

## 2. HIỆU NĂNG (PERFORMANCE)

### 2.1. Thời gian Phản hồi
- **API Response Time**: 
  - Thời gian phản hồi trung bình: < 500ms
  - Thời gian phản hồi tối đa: < 2s (cho các query phức tạp)
  - Thời gian phản hồi cho các API đơn giản: < 200ms

- **Page Load Time**:
  - Thời gian tải trang web: < 2s
  - Thời gian tải màn hình Android: < 1s

- **Database Query**:
  - Thời gian thực thi query đơn giản: < 100ms
  - Thời gian thực thi query phức tạp (join nhiều bảng): < 500ms

### 2.2. Throughput
- **Concurrent Users**: Hệ thống hỗ trợ tối thiểu 50 người dùng đồng thời
- **API Requests**: Hỗ trợ tối thiểu 100 requests/giây
- **Database Transactions**: Hỗ trợ tối thiểu 50 transactions/giây

### 2.3. Resource Usage
- **Memory**: Sử dụng bộ nhớ hợp lý, không vượt quá 512MB cho mỗi instance
- **CPU**: Sử dụng CPU hiệu quả, tối ưu hóa các query và xử lý

---

## 3. BẢO MẬT (SECURITY)

### 3.1. Authentication & Authorization
- **JWT Token**:
  - Token có thời gian hết hạn (ví dụ: 24 giờ)
  - Token được ký bằng secret key
  - Token được validate ở mỗi API request

- **Password Security**:
  - Mật khẩu được hash (không lưu plain text)
  - Yêu cầu mật khẩu tối thiểu 6 ký tự
  - Khuyến khích mật khẩu mạnh (có chữ hoa, chữ thường, số, ký tự đặc biệt)

- **Access Control**:
  - Bệnh nhân chỉ có thể xem dữ liệu của chính mình
  - Bác sĩ và Nhân viên có quyền truy cập phù hợp với vai trò

### 3.2. Data Protection
- **SQL Injection Prevention**:
  - Sử dụng Entity Framework parameterized queries
  - Không cho phép raw SQL queries từ user input

- **XSS Prevention**:
  - Razor Pages tự động encode output
  - Validate và sanitize user input

- **Data Encryption**:
  - Mật khẩu được hash trước khi lưu
  - Token được ký bằng secret key
  - HTTPS cho các kết nối (khuyến khích)

### 3.3. Session Management
- **Token Storage**:
  - Android: Lưu trong SharedPreferences (không mã hóa, nhưng không lưu mật khẩu)
  - Web: Có thể lưu trong HttpOnly cookie

- **Token Expiration**:
  - Token tự động hết hạn sau thời gian quy định
  - Yêu cầu đăng nhập lại khi token hết hạn

---

## 4. KHẢ NĂNG MỞ RỘNG (SCALABILITY)

### 4.1. Horizontal Scaling
- **Stateless Design**: API không lưu trữ state, có thể scale ngang
- **Database**: Có thể scale database bằng cách:
  - Tách đọc/ghi (read replicas)
  - Partitioning tables nếu cần

### 4.2. Vertical Scaling
- **Server Resources**: Có thể tăng CPU, RAM, storage khi cần
- **Database**: Có thể tăng resources cho database server

### 4.3. Caching Strategy
- **Application Cache**: Cache danh sách bác sĩ, thuốc (ít thay đổi)
- **Database Query Cache**: Cache kết quả query thường dùng
- **CDN**: Có thể sử dụng CDN cho static assets (nếu cần)

---

## 5. KHẢ NĂNG SỬ DỤNG (USABILITY)

### 5.1. User Interface
- **Web Interface**:
  - Giao diện rõ ràng, dễ sử dụng
  - Responsive design (tương thích mobile)
  - Thông báo lỗi rõ ràng, dễ hiểu

- **Android Interface**:
  - Material Design guidelines
  - Navigation rõ ràng với nút "Quay lại"
  - Loading indicators khi xử lý
  - Toast messages cho feedback

### 5.2. Error Messages
- **User-friendly Messages**: Thông báo lỗi bằng tiếng Việt, dễ hiểu
- **Error Codes**: Có mã lỗi để debug (trong log, không hiển thị cho user)
- **Validation Feedback**: Hiển thị lỗi validation ngay khi nhập

### 5.3. Accessibility
- **Text Size**: Hỗ trợ điều chỉnh kích thước chữ (Android)
- **Color Contrast**: Đảm bảo độ tương phản màu sắc đủ
- **Touch Targets**: Kích thước nút đủ lớn để dễ nhấn (tối thiểu 48dp)

---

## 6. KHẢ NĂNG BẢO TRÌ (MAINTAINABILITY)

### 6.1. Code Quality
- **Code Structure**: Code được tổ chức rõ ràng, dễ đọc
- **Naming Conventions**: Đặt tên biến, hàm, class rõ ràng, có ý nghĩa
- **Comments**: Code có comments giải thích logic phức tạp
- **Documentation**: Có tài liệu mô tả API, database schema

### 6.2. Modularity
- **Separation of Concerns**: Tách biệt các layer (Presentation, Business Logic, Data Access)
- **Reusability**: Code có thể tái sử dụng (Services, Repositories)
- **Dependency Injection**: Sử dụng DI để dễ test và maintain

### 6.3. Version Control
- **Git**: Sử dụng Git để quản lý version
- **Branching Strategy**: Có strategy rõ ràng (main, develop, feature branches)
- **Commit Messages**: Commit messages rõ ràng, mô tả thay đổi

---

## 7. KHẢ NĂNG TIN CẬY (RELIABILITY)

### 7.1. Error Handling
- **Try-Catch Blocks**: Xử lý exception đầy đủ
- **Graceful Degradation**: Hệ thống vẫn hoạt động một phần khi có lỗi
- **Error Logging**: Ghi log lỗi để debug và monitor

### 7.2. Data Integrity
- **Database Constraints**: Foreign keys, unique constraints, not null
- **Transaction Management**: Sử dụng transactions cho các thao tác phức tạp
- **Data Validation**: Validate dữ liệu ở cả client và server

### 7.3. Backup & Recovery
- **Database Backup**: Có kế hoạch backup database định kỳ
- **Data Recovery**: Có thể khôi phục dữ liệu từ backup
- **Disaster Recovery**: Có kế hoạch xử lý khi có sự cố lớn

---

## 8. KHẢ NĂNG TƯƠNG THÍCH (COMPATIBILITY)

### 8.1. Platform Compatibility
- **Web Browsers**: 
  - Chrome (latest)
  - Firefox (latest)
  - Edge (latest)
  - Safari (latest)

- **Android**:
  - Android 6.0 (API level 23) trở lên
  - Hỗ trợ cả phone và tablet

### 8.2. Database Compatibility
- **SQL Server**: Hỗ trợ SQL Server 2016 trở lên
- **Connection String**: Hỗ trợ các loại authentication (Windows, SQL Server)

### 8.3. API Compatibility
- **RESTful API**: Tuân thủ chuẩn REST
- **JSON Format**: Sử dụng JSON cho request/response
- **HTTP Methods**: Sử dụng đúng HTTP methods (GET, POST, PUT, DELETE)

---

## 9. KHẢ NĂNG KIỂM THỬ (TESTABILITY)

### 9.1. Unit Testing
- **Test Coverage**: Khuyến khích đạt ít nhất 70% code coverage
- **Test Frameworks**: Có thể sử dụng xUnit (C#), JUnit (Java)
- **Mocking**: Sử dụng mocking cho dependencies

### 9.2. Integration Testing
- **API Testing**: Test các API endpoints
- **Database Testing**: Test với test database
- **End-to-End Testing**: Test các quy trình nghiệp vụ hoàn chỉnh

### 9.3. Manual Testing
- **Test Cases**: Có test cases cho các chức năng chính
- **Test Data**: Có dữ liệu test để kiểm thử
- **Bug Tracking**: Có hệ thống theo dõi bugs

---

## 10. KHẢ NĂNG TRIỂN KHAI (DEPLOYABILITY)

### 10.1. Build & Deployment
- **Build Automation**: Có thể build tự động (CI/CD)
- **Environment Configuration**: Cấu hình riêng cho dev, staging, production
- **Deployment Scripts**: Có scripts để deploy tự động

### 10.2. Configuration Management
- **Connection Strings**: Lưu trong configuration files, không hardcode
- **Secrets Management**: Không lưu secrets trong code
- **Environment Variables**: Sử dụng environment variables cho config

### 10.3. Monitoring & Logging
- **Application Logging**: Ghi log các hoạt động quan trọng
- **Error Logging**: Ghi log lỗi với stack trace
- **Performance Monitoring**: Monitor hiệu năng (có thể sử dụng Application Insights, hoặc tương tự)

---

## 11. YÊU CẦU PHÁP LÝ VÀ TUÂN THỦ (COMPLIANCE)

### 11.1. Data Privacy
- **Personal Data**: Bảo vệ thông tin cá nhân của bệnh nhân
- **Medical Records**: Tuân thủ quy định về bảo mật hồ sơ y tế
- **Data Retention**: Có chính sách lưu trữ và xóa dữ liệu

### 11.2. Healthcare Regulations
- **Medical Data**: Tuân thủ các quy định về dữ liệu y tế (nếu có)
- **Audit Trail**: Ghi lại các thao tác quan trọng (audit log)

---

## 12. KẾT LUẬN

Tài liệu này mô tả đầy đủ các yêu cầu phi chức năng của hệ thống Phòng Khám Online. Các yêu cầu này đảm bảo hệ thống hoạt động hiệu quả, an toàn, dễ sử dụng, và có thể mở rộng trong tương lai.

### Ưu tiên Triển khai
1. **Cao**: Bảo mật, Hiệu năng cơ bản, Khả năng sử dụng
2. **Trung bình**: Khả năng mở rộng, Khả năng bảo trì, Monitoring
3. **Thấp**: Advanced caching, CDN, Advanced testing

---

## PHỤ LỤC

### A. Công nghệ sử dụng
- **Backend**: ASP.NET Core 8.0, Entity Framework Core
- **Frontend Web**: Razor Pages, HTML, CSS, JavaScript
- **Mobile**: Android (Java), Retrofit, Gson
- **Database**: SQL Server
- **Authentication**: JWT

### B. Tools & Libraries
- **IDE**: Visual Studio, Android Studio
- **Version Control**: Git
- **API Testing**: Postman, cURL
- **Database Management**: SQL Server Management Studio

