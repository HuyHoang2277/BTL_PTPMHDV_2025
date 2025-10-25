function requireAuth(roleRequired) {
  const token = localStorage.getItem("token");
  const role = localStorage.getItem("role");

  if (!token) {
    alert("Vui lòng đăng nhập!");
    window.location.href = "/frontend/pages/admin/admilogin.html";
    return;
  }

  if (roleRequired && role !== roleRequired) {
    alert("Bạn không có quyền truy cập trang này!");
    window.location.href = "/";
    return;
  }
}
