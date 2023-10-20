function validateForm() {
  var user = document.getElementsByName("user")[0].value; // lấy giá trị của input "user"
  var email = document.getElementsByName("email")[0].value; // lấy giá trị của input "email"
  var pass = document.getElementsByName("pass")[0].value; // lấy giá trị của input "pass"
  var repass = document.getElementsByName("repass")[0].value; // lấy giá trị của input "repass"

  if (user.trim() === "" || email.trim() === "" || pass.trim() === "" || repass.trim() === "") { // kiểm tra xem các giá trị có rỗng hay không
    alert("Please fill in all required fields!"); // hiển thị thông báo nếu có giá trị rỗng
    return false;
  } else {
    return true;
  }
}
