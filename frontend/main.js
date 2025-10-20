    const courses = [
    {
        id: 1,
        title: "Lập trình Web với React.js",
        instructor: "Nguyễn Văn A",
        category: "programming",
        rating: 4.8,
        students: 2500,
        icon: "💻",
    },
    {
        id: 2,
        title: "Thiết kế UI/UX chuyên nghiệp",
        instructor: "Trần Thị B",
        category: "design",
        rating: 4.9,
        students: 1800,
        icon: "🎨",
    },
    {
        id: 3,
        title: "Digital Marketing từ A-Z",
        instructor: "Lê Văn C",
        category: "marketing",
        rating: 4.7,
        students: 3200,
        icon: "📱",
    },
    {
        id: 4,
        title: "Python cho Data Science",
        instructor: "Phạm Thị D",
        category: "programming",
        rating: 4.9,
        students: 2100,
        icon: "🐍",
    },
    {
        id: 5,
        title: "Tiếng Anh giao tiếp thực tế",
        instructor: "Hoàng Văn E",
        category: "language",
        rating: 4.6,
        students: 4500,
        icon: "🗣️",
    },
    {
        id: 6,
        title: "Quản trị kinh doanh hiện đại",
        instructor: "Đỗ Thị F",
        category: "business",
        rating: 4.8,
        students: 1900,
        icon: "💼",
    },
    ];

    let currentFilter = "all";

    function renderCourses(coursesToRender) {
    const coursesGrid = document.getElementById("coursesGrid");
    coursesGrid.innerHTML = "";

    coursesToRender.forEach((course, index) => {
        const courseCard = document.createElement("div");
        courseCard.className = "course-card";
        courseCard.style.animationDelay = `${index * 0.1}s`;

        courseCard.innerHTML = `
                <div class="course-image">${course.icon}</div>
                <div class="course-content">
                    <span class="course-category">${getCategoryName(
                        course.category
                    )}</span>
                    <h3 class="course-title">${course.title}</h3>
                    <p class="course-instructor">👨‍🏫 ${course.instructor}</p>
                    <div class="course-footer">
                        <div class="course-rating">
                            ⭐ ${course.rating}
                        </div>
                        <div class="course-students">
                            👥 ${course.students.toLocaleString()}
                        </div>
                    </div>
                </div>
            `;

        courseCard.onclick = () => {
        alert(`Đang mở khóa học: ${course.title}`);
        };

        coursesGrid.appendChild(courseCard);
    });
    }

    function getCategoryName(category) {
    const categories = {
        programming: "Lập trình",
        design: "Thiết kế",
        business: "Kinh doanh",
        language: "Ngoại ngữ",
        marketing: "Marketing",
    };
    return categories[category] || category;
    }

    function filterCourses(category) {
    currentFilter = category;
    if (category === "all") {
        renderCourses(courses);
    } else {
        const filtered = courses.filter(
        (course) => course.category === category
        );
        renderCourses(filtered);
    }
    }

    // Search functionality
    document.getElementById("searchInput").addEventListener("input", (e) => {
    const searchTerm = e.target.value.toLowerCase();
    let filtered = courses;

    if (currentFilter !== "all") {
        filtered = courses.filter(
        (course) => course.category === currentFilter
        );
    }

    if (searchTerm) {
        filtered = filtered.filter(
        (course) =>
            course.title.toLowerCase().includes(searchTerm) ||
            course.instructor.toLowerCase().includes(searchTerm)
        );
    }

    renderCourses(filtered);
    });

    // Initial render
    renderCourses(courses);

    // Login/Register Modal Functions
    function openLoginModal() {
    document.getElementById("loginModal").classList.add("active");
    document.body.style.overflow = "hidden";
    }

    function closeLoginModal() {
    document.getElementById("loginModal").classList.remove("active");
    document.body.style.overflow = "auto";
    }

    function openRegisterModal() {
    closeLoginModal();
    document.getElementById("registerModal").classList.add("active");
    document.body.style.overflow = "hidden";
    }

    function closeRegisterModal() {
    document.getElementById("registerModal").classList.remove("active");
    document.body.style.overflow = "auto";
    }

    window.onclick = function (event) {
    const loginModal = document.getElementById("loginModal");
    const registerModal = document.getElementById("registerModal");

    if (event.target === loginModal) {
        closeLoginModal();
    }
    if (event.target === registerModal) {
        closeRegisterModal();
    }
    };

    function handleLogin(event) {
    event.preventDefault();
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const remember = document.getElementById("remember").checked;

    console.log("Login:", { email, password, remember });

    alert(`Đăng nhập thành công!\nEmail: ${email}`);
    closeLoginModal();

    updateUIAfterLogin(email);
    }

    function handleRegister(event) {
    event.preventDefault();
    const fullname = document.getElementById("fullname").value;
    const email = document.getElementById("reg-email").value;
    const password = document.getElementById("reg-password").value;
    const confirmPassword =
        document.getElementById("confirm-password").value;

    if (password !== confirmPassword) {
        alert("Mật khẩu xác nhận không khớp!");
        return;
    }

    console.log("Register:", { fullname, email, password });

    alert(`Đăng ký thành công!\nChào mừng ${fullname}!`);
    closeRegisterModal();

    // Auto login after register
    updateUIAfterLogin(email);
    }

    // Social Login
    function loginWithGoogle() {
    alert("Đăng nhập với Google\n(Tính năng demo - cần tích hợp OAuth)");
    closeLoginModal();
    closeRegisterModal();
    }

    function loginWithFacebook() {
    alert("Đăng nhập với Facebook\n(Tính năng demo - cần tích hợp OAuth)");
    closeLoginModal();
    closeRegisterModal();
    }

    // Update UI after login
    function updateUIAfterLogin(email) {
    const userSection = document.querySelector(".user-section");
    userSection.innerHTML = `
            <span style="color: white; margin-right: 1rem;">👤 ${email}</span>
            <button class="btn btn-primary" onclick="logout()">Đăng xuất</button>
        `;
    }

    // Logout
    function logout() {
    const userSection = document.querySelector(".user-section");
    userSection.innerHTML = `
            <button class="btn btn-outline" onclick="openLoginModal()">Đăng nhập</button>
            <button class="btn btn-primary" onclick="openRegisterModal()">Đăng ký</button>
        `;
    alert("Đã đăng xuất thành công!");
    }
