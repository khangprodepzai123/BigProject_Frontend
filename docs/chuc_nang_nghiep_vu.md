# Tài liệu Chức năng Nghiệp vụ
## Hệ Thống Phòng Khám Online

---

## 1. TỔNG QUAN

Hệ thống Phòng Khám Online được thiết kế để quản lý toàn bộ quy trình khám chữa bệnh, từ đăng ký khám đến thanh toán và quản lý hồ sơ bệnh án. Hệ thống hỗ trợ 3 đối tượng chính: Bệnh nhân, Bác sĩ và Nhân viên.

---

## 2. CHỨC NĂNG NGHIỆP VỤ THEO ĐỐI TƯỢNG

### 2.1. BỆNH NHÂN (PATIENT)

#### 2.1.1. Quản lý Tài khoản
- **Đăng ký tài khoản**: Bệnh nhân có thể tạo tài khoản mới trên ứng dụng Android bằng cách nhập tên đăng nhập và mật khẩu.
- **Đăng nhập**: Xác thực danh tính bằng tên đăng nhập và mật khẩu, hệ thống cấp JWT token để truy cập các chức năng.
- **Đăng xuất**: Kết thúc phiên làm việc và xóa token khỏi thiết bị.
- **Xem thông tin cá nhân**: Hiển thị thông tin tài khoản, mã bệnh nhân (nếu đã liên kết), họ tên, điểm tích lũy.

#### 2.1.2. Đăng ký Khám bệnh
- **Đăng ký khám trực tuyến**: Bệnh nhân điền form đăng ký khám với các thông tin:
  - Họ tên (bắt buộc)
  - Số điện thoại (bắt buộc)
  - Ngày sinh (tùy chọn)
  - Giới tính (tùy chọn)
  - Đối tượng (tùy chọn)
  - Địa chỉ (tùy chọn)
  - BHYT (tùy chọn)
  - Lý do khám (tùy chọn)
  - Thời gian hẹn khám (bắt buộc)
- **Tự động liên kết tài khoản**: Nếu bệnh nhân đã đăng nhập, hệ thống tự động liên kết tài khoản với bệnh nhân sau khi đăng ký khám.
- **Tạo phiếu khám**: Hệ thống tự động tạo phiếu khám mới với trạng thái "Đang khám".

#### 2.1.3. Xem Bệnh án
- **Xem danh sách bệnh án**: Hiển thị tất cả các bệnh án đã được lưu của bệnh nhân, bao gồm:
  - Mã bệnh án
  - Ngày khám
  - Ngày lưu
  - Bác sĩ khám
  - Chuẩn đoán
- **Xem chi tiết bệnh án**: Xem đầy đủ thông tin khám, quá trình bệnh lý, tiền sử, và toa thuốc kèm theo.

#### 2.1.4. Xem Toa thuốc
- **Xem toa thuốc hiện tại**: Hiển thị toa thuốc từ lần khám đã thanh toán gần nhất, bao gồm:
  - Thông tin phiếu khám
  - Bác sĩ kê toa
  - Chuẩn đoán
  - Danh sách thuốc với:
    * Tên thuốc
    * Số lượng
    * Liều dùng
    * Cách dùng
    * Số lần uống mỗi ngày (tự động parse từ liều dùng)
- **Xem lịch sử toa thuốc**: Xem các toa thuốc từ các lần khám trước (thông qua bệnh án).

#### 2.1.5. Xem Hóa đơn
- **Xem danh sách hóa đơn**: Hiển thị tất cả các hóa đơn đã thanh toán của bệnh nhân.
- **Xem chi tiết hóa đơn**: Xem chi tiết từng hóa đơn bao gồm:
  - Mã hóa đơn
  - Ngày lập
  - Tiền khám
  - Danh sách thuốc và giá
  - Giảm giá BHYT (nếu có)
  - Giảm giá điểm tích lũy (nếu có)
  - Tổng thành tiền

#### 2.1.6. Điểm tích lũy và Ưu đãi
- **Xem điểm tích lũy**: Hiển thị số điểm hiện tại của tài khoản.
- **Xem mức ưu đãi**: Hiển thị mức ưu đãi hiện tại dựa trên số điểm:
  - 1 điểm = Đồng
  - 2 điểm = Bạc
  - 3 điểm = Vàng
  - >3 điểm = Kim cương
- **Sử dụng điểm giảm giá**: Tự động áp dụng giảm giá 10% cho lần khám tiếp theo khi có ít nhất 1 điểm.
- **Tích điểm**: Tự động nhận 1 điểm sau mỗi lần thanh toán thành công.

#### 2.1.7. Thông tin Bác sĩ
- **Xem danh sách bác sĩ**: Hiển thị danh sách tất cả bác sĩ trong phòng khám với thông tin:
  - Họ tên
  - Trình độ học vấn
  - Chuyên khoa
  - Tuổi
  - Kinh nghiệm
  - Chứng chỉ hành nghề
- **Xem chi tiết bác sĩ**: Xem đầy đủ thông tin của từng bác sĩ.
- **Tìm kiếm bác sĩ**: Tìm kiếm bác sĩ theo tên hoặc chuyên khoa.

---

### 2.2. BÁC SĨ (DOCTOR)

#### 2.2.1. Quản lý Phiên làm việc
- **Đăng nhập**: Xác thực danh tính để truy cập hệ thống web.
- **Đăng xuất**: Kết thúc phiên làm việc.

#### 2.2.2. Khám bệnh
- **Xem danh sách bệnh nhân cần khám**: Xem danh sách các bệnh nhân đã đăng ký khám.
- **Khám bệnh**: Thực hiện khám bệnh cho bệnh nhân, bao gồm:
  - Nhập lý do khám
  - Ghi nhận quá trình bệnh lý
  - Khai thác tiền sử bệnh nhân và gia đình
  - Khám bộ phận
  - Chọn chuẩn đoán
  - Chọn loại khám và xử trí
- **Chẩn đoán**: Đưa ra chuẩn đoán dựa trên kết quả khám.

#### 2.2.3. Kê Toa thuốc
- **Chọn thuốc**: Chọn thuốc từ danh sách thuốc có sẵn trong kho.
- **Nhập thông tin toa**: Nhập số lượng, liều dùng, cách dùng cho từng thuốc.
- **Lưu toa thuốc**: Lưu toa thuốc vào hệ thống.

#### 2.2.4. Quản lý Bệnh án
- **Lưu bệnh án**: Lưu thông tin khám và toa thuốc vào bệnh án sau khi hoàn tất khám.
- **Xem bệnh án**: Xem lại các bệnh án đã lưu.
- **Tìm kiếm bệnh án**: Tìm kiếm bệnh án theo mã bệnh nhân, ngày khám, hoặc chuẩn đoán.

#### 2.2.5. Quản lý Thông tin cá nhân
- **Xem thông tin bác sĩ**: Xem thông tin cá nhân đã được cập nhật.
- **Cập nhật thông tin**: Cập nhật thông tin cá nhân (nếu được phép).

---

### 2.3. NHÂN VIÊN (STAFF)

#### 2.3.1. Quản lý Phiên làm việc
- **Đăng nhập**: Xác thực danh tính để truy cập hệ thống web.
- **Đăng xuất**: Kết thúc phiên làm việc.

#### 2.3.2. Quản lý Bệnh nhân
- **Xem danh sách bệnh nhân**: Hiển thị danh sách tất cả bệnh nhân trong hệ thống với trạng thái khám.
- **Tìm kiếm bệnh nhân**: Tìm kiếm bệnh nhân theo mã, tên, số điện thoại.
- **Xem thông tin bệnh nhân**: Xem chi tiết thông tin bệnh nhân.
- **Tạo phiếu khám mới**: Tạo phiếu khám mới cho bệnh nhân mới.
- **Khám lại**: Cho phép bệnh nhân khám lại bằng cách xóa phiếu khám cũ và tạo mới (nếu bệnh án đã được lưu).

#### 2.3.3. Hỗ trợ Khám bệnh
- **Khám bệnh**: Hỗ trợ bác sĩ trong quá trình khám bệnh.
- **Kê toa thuốc**: Hỗ trợ kê toa thuốc cho bệnh nhân.
- **Lưu bệnh án**: Lưu bệnh án sau khi khám xong.

#### 2.3.4. Quản lý Hóa đơn
- **Xem danh sách hóa đơn**: Xem tất cả các hóa đơn đã tạo.
- **Xem chi tiết hóa đơn**: Xem chi tiết từng hóa đơn.
- **Thanh toán hóa đơn**: Thực hiện thanh toán cho bệnh nhân, bao gồm:
  - Tính toán chi phí (tiền khám + tiền thuốc)
  - Áp dụng giảm giá BHYT (80% nếu có)
  - Áp dụng giảm giá điểm tích lũy (10% nếu có điểm >= 1)
  - Tạo hóa đơn và chi tiết
  - Cập nhật số lượng thuốc trong kho
  - Cập nhật điểm tích lũy cho tài khoản
- **Tìm kiếm hóa đơn**: Tìm kiếm hóa đơn theo mã, ngày, bệnh nhân.

#### 2.3.5. Quản lý Bệnh án
- **Xem tủ bệnh án**: Xem tất cả các bệnh án đã được lưu, được nhóm theo bệnh nhân.
- **Xem chi tiết bệnh án**: Xem chi tiết từng bệnh án.
- **Tìm kiếm bệnh án**: Tìm kiếm bệnh án theo các tiêu chí khác nhau.

#### 2.3.6. Quản lý Tài khoản
- **Xem danh sách tài khoản**: Hiển thị danh sách tất cả tài khoản bệnh nhân đã đăng ký, bao gồm mã bệnh nhân và tên (nếu đã liên kết).
- **Tìm kiếm tài khoản**: Tìm kiếm tài khoản theo tên đăng nhập, mã bệnh nhân.
- **Xem thông tin tài khoản**: Xem chi tiết tài khoản và điểm tích lũy.

#### 2.3.7. Quản lý Bác sĩ
- **Xem danh sách bác sĩ**: Hiển thị danh sách tất cả bác sĩ.
- **Thêm bác sĩ mới**: Thêm bác sĩ mới vào hệ thống với đầy đủ thông tin.
- **Cập nhật thông tin bác sĩ**: Cập nhật thông tin bác sĩ (trình độ, chuyên khoa, kinh nghiệm, v.v.).
- **Xóa bác sĩ**: Xóa bác sĩ khỏi hệ thống (nếu không có dữ liệu liên quan).
- **Tìm kiếm bác sĩ**: Tìm kiếm bác sĩ theo tên, chuyên khoa.

---

## 3. QUY TRÌNH NGHIỆP VỤ CHÍNH

### 3.1. Quy trình Khám bệnh hoàn chỉnh

1. **Bệnh nhân đăng ký khám** (Android hoặc tại phòng khám)
2. **Nhân viên tạo phiếu khám** (nếu chưa có)
3. **Bác sĩ/Nhân viên khám bệnh** và nhập thông tin
4. **Bác sĩ kê toa thuốc**
5. **Lưu bệnh án** (tùy chọn)
6. **Nhân viên thanh toán** hóa đơn
7. **Cập nhật điểm tích lũy** cho tài khoản (nếu có)

### 3.2. Quy trình Thanh toán

1. **Tính toán chi phí**: Tiền khám (100,000 VNĐ) + Tổng tiền thuốc
2. **Áp dụng giảm giá BHYT**: Giảm 80% nếu có BHYT
3. **Áp dụng giảm giá điểm tích lũy**: Giảm 10% nếu có điểm >= 1
4. **Tạo hóa đơn** và chi tiết
5. **Cập nhật số lượng thuốc** trong kho
6. **Cập nhật điểm tích lũy**: +1 điểm sau mỗi lần thanh toán

### 3.3. Quy trình Quản lý Điểm tích lũy

1. **Tích điểm**: Tự động +1 điểm sau mỗi lần thanh toán
2. **Xác định mức ưu đãi**: Dựa trên số điểm hiện tại
3. **Áp dụng giảm giá**: Tự động giảm 10% cho lần khám tiếp theo khi có điểm >= 1

---

## 4. RÀNG BUỘC NGHIỆP VỤ

### 4.1. Ràng buộc về Dữ liệu
- Mỗi bệnh nhân chỉ có thể có 1 phiếu khám chưa thanh toán tại một thời điểm (UNIQUE constraint trên MaBn trong KhamBenh).
- Tài khoản có thể liên kết với tối đa 1 bệnh nhân.
- Mỗi phiếu khám chỉ có thể có 1 hóa đơn.

### 4.2. Ràng buộc về Quy trình
- Chỉ có thể thanh toán khi phiếu khám đã được khám (trạng thái "Đã khám").
- Chỉ có thể khám lại khi bệnh án của lần khám trước đã được lưu.
- Điểm tích lũy chỉ được cộng sau khi thanh toán thành công.

### 4.3. Ràng buộc về Quyền hạn
- Bệnh nhân chỉ có thể xem dữ liệu của chính mình.
- Bác sĩ có thể xem và chỉnh sửa thông tin khám bệnh.
- Nhân viên có quyền quản lý toàn bộ hệ thống.

---

## 5. YÊU CẦU ĐẶC BIỆT

### 5.1. Tự động hóa
- Tự động liên kết tài khoản với bệnh nhân khi đăng ký khám (nếu đã đăng nhập).
- Tự động áp dụng giảm giá điểm tích lũy khi thanh toán (nếu có điểm >= 1).
- Tự động cộng điểm sau mỗi lần thanh toán.

### 5.2. Tính toán
- Parse liều dùng từ chuỗi văn bản để tính số lần uống mỗi ngày (ví dụ: "1 viên/lần" → 1 lần/ngày).
- Tính toán giảm giá BHYT: 80% giảm giá, còn lại 20% phải trả.
- Tính toán giảm giá điểm tích lũy: 10% giảm giá.

---

## 6. KẾT LUẬN

Tài liệu này mô tả đầy đủ các chức năng nghiệp vụ của hệ thống Phòng Khám Online, bao gồm các chức năng dành cho Bệnh nhân, Bác sĩ và Nhân viên, cùng với các quy trình nghiệp vụ chính và các ràng buộc cần tuân thủ.

