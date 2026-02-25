### Danh sách thành viên
Nguyễn Đức Huy       MSSV: 2311176   Lớp L02

### Link trang web : https://student-management-sr6q.onrender.com/students

# Hướng Dẫn Chạy Dự Án Student Management

## 📋 Thông Tin Dự Án

- **Tên dự án**: Student Management System
- **Framework**: Spring Boot 4.0.2
- **Java Version**: 17
- **Build Tool**: Maven
- **Database**: PostgreSQL
- **Mục đích**: Xây dựng hệ thống quản lý sinh viên với Spring Boot Web MVC

---

## ✅ Yêu Cầu Hệ Thống

Trước khi chạy dự án, hãy cài đặt:

1. **Java Development Kit (JDK) 17 trở lên**
   - Kiểm tra: `java -version`
   - Tải tại: https://www.oracle.com/java/technologies/downloads/

2. **PostgreSQL Database** (hoặc sử dụng online)
   - Tải tại: https://www.postgresql.org/download/
   - Hoặc dùng Neon (PostgreSQL free online): https://neon.tech/

3. **Maven** (đã bao gồm trong project qua `mvnw`)
   - Nếu cài riêng: `mvn --version`

---

## 🚀 Các Bước Cài Đặt

### Bước 1: Lấy Project

```bash
cd student-management
```

### Bước 2: Tạo và Cấu Hình File `.env`

Tạo file `.env` tại **thư mục gốc** của project (cùng cấp với `pom.xml`) như file envexample:

**Tùy chọn A: PostgreSQL trên máy tính:**
```env
POSTGRES_URL=jdbc:postgresql://localhost:5432/student_db
POSTGRES_USER=postgres
POSTGRES_PASSWORD=your_password
```

**Tùy chọn B: PostgreSQL online (Neon):**
```env
POSTGRES_URL=postgresql://username:password@ep-xxxx.neon.tech/neondb
POSTGRES_USER=username
POSTGRES_PASSWORD=password
```

**Lưu ý:**
- Thay `your_password`, `username`, `password` bằng giá trị thực tế

### Bước 3: Tải Dependencies

Chạy lệnh để tải tất cả thư viện từ `pom.xml`:

```bash
./mvnw dependency:resolve
```

(Trên Windows nếu cần: `mvnw.cmd dependency:resolve`)

---

## ⚡ Chạy Ứng Dụng

### Chạy bằng Maven 

```bash
./mvnw spring-boot:run
```


## 🌐 Kiểm Tra Ứng Dụng

Mở trình duyệt web và truy cập:

```
http://localhost:8080
```

Bạn sẽ thấy giao diện quản lý sinh viên.

### Các URL chính:

| URL | Chức năng |
|-----|----------|
| `/students` | Danh sách sinh viên |
| `/students/add` | Thêm sinh viên mới |
| `/students/{id}` | Xem chi tiết sinh viên |
| `/students/update/{id}` | Cập nhật thông tin sinh viên |


### Trả lời câu hỏi

1. Khóa chính (Primary Key): Mỗi bản ghi phải là duy nhất. Nếu có hai sinh viên cùng id = 1 khi bạn muốn thao tác với sinh viên số 1, Database sẽ không biết phải sửa hàng nào.

2. Toàn vẹn dữ liệu: 
   - Khi thêm sinh viên nhưng để trống 1 cột thì mặc định về null nếu ko để not null.
   - Lỗi Logic: Khi hiển thị lên giao diện (Web/App), người dùng sẽ thấy tên sinh viên bị để trống hoặc chữ "null" vô nghĩa, gây trải nghiệm tệ.
   - Khi lỡ tay thao tác với giá trị null có thể gây sập hệ thống

3. Tại sao mỗi lần tắt ứng dụng và chạy lại, dữ liệu cũ trong Database lại bị mất hết?
   - vấn đề: spring.jpa.hibernate.ddl-auto trong file application.properties

   - ***.\src\main\resources\application.properties***

   - Khi bật auto thì mỗi lần chạy sẽ xóa bảng cũ và tự tạo lại bảng mới dẫn đến mất dữ liệu
   -> khắc phục bằng cách để none hoặc update


##### Screenshot cho các module trong Lab 4.
danh sách sinh viên
![danh sách sinh viên](img/danh_sach.png)


| thêm sinh viên | danh sách sinh viên sau thêm |
| :---: | :---: |
| ![AST](img/them.png) | ![DB](img/sau_them.png) |

| sửa thông tin | sau khi sửa |
| :---: | :---: |
| ![AST](img/sua.png) | ![DB](img/sau_sua.png) |

| xác nhận xóa | sau khi xóa |
| :---: | :---: |
| ![AST](img/xac_nhan_xoa.png) | ![DB](img/sau_xoa.png) |