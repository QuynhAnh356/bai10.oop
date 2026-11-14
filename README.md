
## 1. VỊ TRÍ SỬ DỤNG VÀ LỢI ÍCH CỦA CÁC DESIGN PATTERN

### 1.1. Factory Pattern
* **Vị trí sử dụng:** Lớp `DeviceFactory` (phương thức static `create(String type, String name)`).
* **Lợi ích đạt được:**
  * Che giấu việc gọi constructor cụ thể (`SmartLight`, `SmartFan`, `SmartDoor`).
  * Giảm phụ thuộc (loose coupling): Client chỉ làm việc qua abstraction `IDevice`.
  * Xử lý chuẩn hóa đầu vào (không phân biệt hoa/thường, cắt khoảng trắng) và kiểm soát lỗi tập trung.

### 1.2. Singleton Pattern
* **Vị trí sử dụng:** Lớp `HomeConfiguration` (phương thức static `getInstance()`).
* **Lợi ích đạt được:**
  * Đảm bảo cấu hình ngôi nhà (`homeName`, `nightLightLevel`) chỉ có duy nhất một thể hiện trong suốt vòng đời ứng dụng.
  * Cung cấp điểm truy cập toàn cục an toàn đa luồng (Double-Checked Locking).
  * Tránh lãng phí bộ nhớ và xung đột dữ liệu cấu hình.

### 1.3. Facade Pattern
* **Vị trí sử dụng:** Lớp `SmartHomeFacade` (phương thức `goToWork()`, `goToSleep()`).
* **Lợi ích đạt được:**
  * Cung cấp giao diện mức cao đơn giản cho một tập hợp thao tác phức tạp trên nhiều thiết bị.
  * Client không cần nhớ và gọi thủ công từng lệnh riêng lẻ của từng thiết bị.
  * Không làm mất tính linh hoạt: Client vẫn có thể điều khiển riêng từng thiết bị khi cần.

---

## 2. MỘT HẠN CHẾ CỦA THIẾT KẾ
* **Hạn chế chính:** Lớp `SmartHomeFacade` bị **cố định số lượng thiết bị** (chỉ nhận đúng 1 `light`, 1 `fan`, 1 `door` qua constructor).
* **Hệ quả thực tế:** Nếu ngôi nhà mở rộng có nhiều phòng với hàng chục thiết bị (ví dụ 10 đèn, 4 quạt, 3 cửa), Facade hiện tại không thể co giãn (scale).
* **Hướng cải tiến:** Cho phép `SmartHomeFacade` quản lý thiết bị dưới dạng danh sách (`List<IDevice>`, `List<ILockable>`) hoặc kết hợp Composite Pattern để điều khiển linh hoạt nhiều nhóm thiết bị.

---

## 3. TRẢ LỜI CÁC CÂU HỎI (PHẦN G)

### Câu 1: Factory đang che giấu quyết định thiết kế nào?
> **Trả lời:** Factory che giấu **cách thức và lớp cụ thể được dùng để tạo đối tượng**. Client không cần biết lớp con nào (`SmartLight`, `SmartFan` hay `SmartDoor`) được khởi tạo, cũng không cần biết tham số constructor nội bộ, mà chỉ cần gửi yêu cầu bằng chuỗi định danh và nhận về giao diện trừu tượng `IDevice`.

### Câu 2: Singleton bảo đảm điều gì, nhưng tạo ra rủi ro nào?
> **Trả lời:**
> * **Bảo đảm:** Chỉ tồn tại **duy nhất một thể hiện (instance)** của lớp trong toàn bộ chương trình và có một điểm truy cập toàn cục.
> * **Rủi ro:** Tạo ra trạng thái toàn cục (Global State), làm các thành phần bị liên kết chặt (tight coupling) và **gây khó khăn lớn cho Unit Test** (khó mock đối tượng, dữ liệu test case trước dễ gây tác dụng phụ làm sai lệch test case sau).

### Câu 3: Facade khác Factory ở mục đích chính như thế nào?
> **Trả lời:**
> * **Factory (Creational Pattern):** Tập trung vào việc **tạo mới đối tượng** (How objects are created) mà không để lộ logic khởi tạo cho client.
> * **Facade (Structural Pattern):** Tập trung vào việc **cung cấp giao diện đơn giản hóa cho một hệ thống con (subsystem) đã có sẵn** (How objects work together), giúp client tương tác mức cao dễ dàng hơn.

### Câu 4: Nếu bỏ `SmartHomeFacade`, client sẽ phải biết thêm những chi tiết nào?
> **Trả lời:** Nếu không có Facade, client sẽ phải:
> 1. Tự nắm giữ tham chiếu trực tiếp đến từng thiết bị (`light`, `fan`, `door`).
> 2. Phải biết chi tiết từng phương thức cần gọi và thứ tự gọi chuẩn xác khi đi làm hoặc đi ngủ (ví dụ: muốn đi ngủ phải tự gọi quạt tắt, tự gọi cửa khóa, tự gọi đèn bật).
> 3. Phải tự truy cập `HomeConfiguration` để lấy giá trị `nightLightLevel` và tự in thông báo cấu hình.

---

## 4. CÂU HỎI MỞ RỘNG (PHẦN C & D)
* **Khi thêm `SmartCamera`, cần sửa những gì?**
  * Tạo class mới: `SmartCamera implements IDevice` (hoặc interface bổ sung).
  * Sửa `DeviceFactory.java`: Thêm `case "camera" => new SmartCamera(name)`. (Lưu ý: Việc phải sửa code Factory thể hiện hạn chế vi phạm nguyên lý Open/Closed - OCP).
* **Vì sao `HomeConfiguration` có constructor `private` và được đánh dấu `final`?**
  * Constructor `private`: Chặn bên ngoài dùng từ khóa `new` để tạo instance tự do.
  * Đánh dấu `final`: Ngăn chặn việc kế thừa (subclassing) tạo ra lớp con phá vỡ tính duy nhất của Singleton.
* **Nếu dùng Dependency Injection (DI) với lifetime Singleton, có cần tự cài Singleton Pattern không?**
  * **Không nhất thiết.** Container DI (như Spring Framework trong Java hoặc .NET DI) sẽ tự quản lý vòng đời và đảm bảo chỉ tiêm một thể hiện duy nhất (`IoC container manages lifecycle`), giúp code dễ viết unit test và tránh bị phụ thuộc cứng vào static class.
