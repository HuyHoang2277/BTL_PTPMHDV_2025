        // Sample courses data based on ERD
        const coursesData = [
            {
                id: 1,
                title: "HTML CSS từ Zero đến Hero",
                description: "Học HTML CSS cơ bản cho người mới bắt đầu, xây dựng giao diện web chuyên nghiệp",
                category: "frontend",
                rating: 4.9,
                students: 12500,
                price: "Miễn phí",
                icon: "fa-html5"
            },
            {
                id: 2,
                title: "JavaScript Cơ Bản",
                description: "Nắm vững JavaScript từ cơ bản đến nâng cao, lập trình web động",
                category: "frontend",
                rating: 4.8,
                students: 10200,
                price: "599,000đ",
                icon: "fa-js"
            },
            {
                id: 3,
                title: "React JS - Thực Chiến",
                description: "Xây dựng ứng dụng web hiện đại với React, Redux và React Hooks",
                category: "frontend",
                rating: 4.9,
                students: 8900,
                price: "799,000đ",
                icon: "fa-react"
            },
            {
                id: 4,
                title: "Node.js & Express",
                description: "Lập trình Backend với Node.js, xây dựng RESTful API chuyên nghiệp",
                category: "backend",
                rating: 4.7,
                students: 7600,
                price: "699,000đ",
                icon: "fa-node"
            },
            {
                id: 5,
                title: "MongoDB Database",
                description: "Học database NoSQL MongoDB từ cơ bản đến nâng cao",
                category: "database",
                rating: 4.6,
                students: 5400,
                price: "499,000đ",
                icon: "fa-database"
            },
            {
                id: 6,
                title: "React Native Mobile App",
                description: "Xây dựng ứng dụng di động đa nền tảng với React Native",
                category: "mobile",
                rating: 4.8,
                students: 6200,
                price: "899,000đ",
                icon: "fa-mobile-alt"
            }
        ];

        // Render courses
        function renderCourses(courses) {
            const coursesList = document.getElementById('coursesList');
            coursesList.innerHTML = '';
            
            courses.forEach(course => {
                const courseCard = document.createElement('div');
                courseCard.className = 'course-card';
                courseCard.onclick = () => viewCourse(course.id);
                
                courseCard.innerHTML = `
                    <div class="course-image">
                        <i class="fab ${course.icon}"></i>
                    </div>
                    <div class="course-content">
                        <h3 class="course-title">${course.title}</h3>
                        <p class="course-description">${course.description}</p>
                        <div class="course-meta">
                            <div>
                                <div class="course-rating">
                                    <i class="fas fa-star"></i>
                                    <span>${course.rating}</span>
                                </div>
                                <div class="course-students">
                                    <i class="fas fa-user"></i> ${course.students.toLocaleString()} học viên
                                </div>
                            </div>
                            <div class="course-price ${course.price === 'Miễn phí' ? 'course-free' : ''}">
                                ${course.price}
                            </div>
                        </div>
                    </div>
                `;
                
                coursesList.appendChild(courseCard);
            });
        }

        // Filter courses by category
        function filterCourses(category) {
            const filtered = coursesData.filter(course => course.category === category);
            renderCourses(filtered);
            document.getElementById('courses').scrollIntoView({ behavior: 'smooth' });
        }

        // View course details
        function viewCourse(courseId) {
            const course = coursesData.find(c => c.id === courseId);
            alert(`Xem chi tiết khóa học: ${course.title}\n\nTính năng đang được phát triển!`);
        }

        // Modal functions
        function openModal(type) {
            const modal = document.getElementById(type + 'Modal');
            modal.style.display = 'flex';
        }

        function closeModal(type) {
            const modal = document.getElementById(type + 'Modal');
            modal.style.display = 'none';
        }

        // Handle login
        function handleLogin(e) {
            e.preventDefault();
            alert('Đăng nhập thành công!\n\nChức năng đang được phát triển.');
            closeModal('login');
        }

        // Handle register
        function handleRegister(e) {
            e.preventDefault();
            alert('Đăng ký thành công!\n\nChức năng đang được phát triển.');
            closeModal('register');
        }

        // Close modal when clicking outside
        window.onclick = function(event) {
            if (event.target.classList.contains('modal')) {
                event.target.style.display = 'none';
            }
        }

        // Initialize courses on page load
        renderCourses(coursesData);

        // Search functionality
        document.querySelector('.search-bar input').addEventListener('keyup', function(e) {
            const searchTerm = e.target.value.toLowerCase();
            const filtered = coursesData.filter(course => 
                course.title.toLowerCase().includes(searchTerm) ||
                course.description.toLowerCase().includes(searchTerm)
            );
            renderCourses(filtered);
        });

        // Smooth scroll for navigation
        document.querySelectorAll('a[href^="#"]').forEach(anchor => {
            anchor.addEventListener('click', function (e) {
                e.preventDefault();
                const target = document.querySelector(this.getAttribute('href'));
                if (target) {
                    target.scrollIntoView({ behavior: 'smooth' });
                }
            });
        });