//tool test https://studio.uilicious.com/
// ============ LOGIN ==============
// Test Case 1:  Kiểm tra đăng nhập với username và password hợp lệ
I.goTo("https://mwc.com.vn/")
I.click("a.account-handle-icon")
I.fill("UserName", "dinhnhan12345")
I.fill("Password", "Dinhthanhnhan12345")
I.click("input[type='submit']")
I.wait(2)

I.click("a.account-handle-icon[href='/profile']")
I.wait(2) 

I.click("a.profile-sidebar--menu-item[href='/logout']")
I.wait(2) 

I.goTo("https://mwc.com.vn/") 
I.wait(2) 

// Test Case 2: Kiểm tra trường hợp nhập đúng username nhưng sai password
I.click("a.account-handle-icon")
I.fill("UserName", "dinhnhan12345")
I.fill("Password", "000000")
I.click("input[type='submit']")
I.wait(2) 
I.see("Tên đăng nhập hoặc mật khẩu không đúng!")

// Test Case 3: Kiểm tra trường hợp người dùng để trống username
I.click("a.account-handle-icon")
I.fill("UserName", ""); 
I.fill("Password", "123456");
I.click("input[type='submit']");
I.wait(2);

UI.execute(() => {
    let input = document.querySelector("[name='UserName']");
    let msg = input.validationMessage;
    
    // Tạo hoặc chọn span để hiển thị message
    let span = document.querySelector("#validationMsg");
    if (!span) {
        span = document.createElement("span");
        span.id = "validationMsg";
        input.parentNode.appendChild(span);
    }
    span.textContent = msg;
});

I.see("Please fill in this field", "#validationMsg");


// Test Case 4: Kiểm tra trường hợp người dùng không nhập password
I.click("a.account-handle-icon")
I.fill("UserName", "dinhnhan12345");
I.fill("Password", "");            
I.click("input[type='submit']");
I.wait(2);

UI.execute(() => {
    let input = document.querySelector("[name='Password']");
    let msg = input.validationMessage;

    // Tạo hoặc chọn span để hiển thị message
    let span = document.querySelector("#passwordValidationMsg");
    if (!span) {
        span = document.createElement("span");
        span.id = "passwordValidationMsg";
        input.parentNode.appendChild(span);
    }
    span.textContent = msg;
});

I.see("Please fill in this field", "#passwordValidationMsg");
// ============ END LOGIN ==============

// ================ REGISTER =================
// Test Case 1: Đăng ký hợp lệ
I.goTo("https://mwc.com.vn/")  // Truy cập vào trang
I.click("a.account-handle-icon")   // Truy cập trang register
I.fill("#form_register input[name='UserName']", "dinhnhan5678899035")
I.fill("#form_register input[name='Phone']", "0867764035")
I.fill("#form_register input[name='Password']", "Dinhthanhnhan12345")
I.fill("#form_register input[name='PasswordConfirm']", "Dinhthanhnhan12345")
I.click("#form_register input[type='submit']")
I.wait(2)  // Chờ 2 giây để đăng nhập hoàn tất

// Nhấn vào icon tài khoản để vào trang profile
I.click("a.account-handle-icon[href='/profile']")
I.wait(2)  // Chờ 2 giây để chuyển sang trang profile

// Nhấn vào nút Đăng xuất
I.click("a.profile-sidebar--menu-item[href='/logout']")
I.wait(2)  // Chờ 2 giây để quá trình đăng xuất hoàn tất

// Truy cập vào trang đăng nhập lại
I.goTo("https://mwc.com.vn/") 
I.wait(2)  // Chờ 2 giây để trang đăng nhập tải lại

// Kiểm tra trường hợp nhập đúng username nhưng sai password
I.click("a.account-handle-icon")


// Test Case 2: Tên đăng nhập đã tồn tại
I.fill("#form_register input[name='UserName']", "dinhnhan12345")
I.fill("#form_register input[name='Phone']", "0987654321")
I.fill("#form_register input[name='Password']", "Dinhthanhnhan12345")
I.fill("#form_register input[name='PasswordConfirm']", "Dinhthanhnhan12345")
I.click("#form_register input[type='submit']")
I.wait(2)
I.see("Tài khoản đã tồn tại trong hệ thống")  // Kiểm tra thông báo lỗi

// Test Case 3: Số điện thoại không hợp lệ
I.fill("#form_register input[name='UserName']", "dinhthanhnhan1234")
I.fill("#form_register input[name='Phone']", "0987654321235")  // Số điện thoại sai
I.fill("#form_register input[name='Password']", "Dinhthanhnhan12345")
I.fill("#form_register input[name='PasswordConfirm']", "Dinhthanhnhan12345")
I.click("#form_register input[type='submit']")
I.wait(2)
I.see("Số điện thoại không đúng định dạng")  // Kiểm tra thông báo lỗi

// Test Case 4: Test Case 4: Mật khẩu và xác nhận mật khẩu không khớp
I.fill("#form_register input[name='UserName']", "user03")
I.fill("#form_register input[name='Phone']", "0987654321")
I.fill("#form_register input[name='Password']", "Dinhnhan123456")
I.fill("#form_register input[name='PasswordConfirm']", "Dinhnhan654321")  // Không khớp
I.click("#form_register input[type='submit']")
I.wait(2)
I.see("Mật khẩu không giống nhau")

// Test Case 5: Mật khẩu quá ngắn
I.fill("#form_register input[name='UserName']", "user999")
I.fill("#form_register input[name='Phone']", "0867764890")
I.fill("#form_register input[name='Password']", "123")              // Quá ngắn
I.fill("#form_register input[name='PasswordConfirm']", "123")
I.click("#form_register input[type='submit']")
I.wait(2)
I.see("Mật khẩu phải lớn hơn 8 ký tự và nhỏ hơn 20 ký tự!")

// ================== END REGISTER ================= file của mình như này làm sao lưu thành js studio.uilicious