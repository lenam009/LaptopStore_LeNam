<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form"%> <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet" />

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet" />

        <!-- Libraries Stylesheet -->
        <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet" />
        <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="/client/css/bootstrap.min.css" rel="stylesheet" />

        <!-- Template Stylesheet -->
        <link href="/client/css/style.css" rel="stylesheet" />
    </head>

    <body>
        <!-- Spinner Start -->
        <div
            id="spinner"
            class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50 d-flex align-items-center justify-content-center"
        >
            <div class="spinner-grow text-primary" role="status"></div>
        </div>
        <!-- Spinner End -->

        <!-- Navbar start -->
        <jsp:include page="../layout/header.jsp" />
        <!-- Navbar End -->

        <!-- Fruits Shop Start-->
        <div class="container-fluid fruite py-5">
            <div class="container py-5">
                <div class="row">
                    <div class="col-4">
                        <form method="post">
                            <!-- Hãng sản xuất -->
                            <div class="row g-1 mb-3">
                                <p class="fw-bolder">Hãng sản xuất</p>

                                <div class="col-3 form-check">
                                    <input class="form-check-input" type="checkbox" value="APPLE" id="flexCheckDefault" />
                                    <label class="form-check-label" for="flexCheckDefault"> Apple </label>
                                </div>
                                <div class="col-3 form-check">
                                    <input class="form-check-input" type="checkbox" value="ASUS" id="flexCheckDefault2" />
                                    <label class="form-check-label" for="flexCheckDefault2"> Asus </label>
                                </div>
                                <div class="col-3 form-check">
                                    <input class="form-check-input" type="checkbox" value="DELL" id="flexCheckDefault3" />
                                    <label class="form-check-label" for="flexCheckDefault3"> Dell </label>
                                </div>
                                <div class="col-3 form-check">
                                    <input class="form-check-input" type="checkbox" value="LENOVO" id="flexCheckDefault4" />
                                    <label class="form-check-label" for="flexCheckDefault4"> Lenovo </label>
                                </div>
                                <div class="col-3 form-check">
                                    <input class="form-check-input" type="checkbox" value="ACER" id="flexCheckDefault5" />
                                    <label class="form-check-label" for="flexCheckDefault5"> Acer </label>
                                </div>
                                <div class="col-3 form-check">
                                    <input class="form-check-input" type="checkbox" value="DELL" id="flexCheckDefault6" />
                                    <label class="form-check-label" for="flexCheckDefault6"> Dell </label>
                                </div>
                            </div>
                            <!-- Mục đích sử dụng -->
                            <div class="row g-1 mb-3">
                                <p class="fw-bolder">Mục đích sử dụng</p>
                                <div class="col-6 form-check">
                                    <input class="form-check-input" type="checkbox" value="GAMING" id="GAMING" />
                                    <label class="form-check-label" for="GAMING"> Gaming </label>
                                </div>
                                <div class="col-6 form-check">
                                    <input class="form-check-input" type="checkbox" value="SINHVIEN-VANPHONG" id="SINHVIEN-VANPHONG" />
                                    <label class="form-check-label" for="SINHVIEN-VANPHONG"> Sinh viên văn phòng </label>
                                </div>
                                <div class="col-6 form-check">
                                    <input class="form-check-input" type="checkbox" value="MONG-NHE" id="MONG-NHE" />
                                    <label class="form-check-label" for="MONG-NHE"> Mỏng nhẹ </label>
                                </div>
                            </div>
                            <!-- Mức giá -->
                            <div class="row g-1 mb-3">
                                <p class="fw-bolder">Mức giá</p>
                                <div class="col-4 form-check">
                                    <input class="form-check-input" type="checkbox" value="duoi10" id="duoi10" />
                                    <label class="form-check-label" for="duoi10"> Dưới 10 triệu </label>
                                </div>
                                <div class="col-4 form-check">
                                    <input class="form-check-input" type="checkbox" value="tu10den15" id="tu10den15" />
                                    <label class="form-check-label" for="tu10den15"> Từ 10 đến 15 triệu </label>
                                </div>
                                <div class="col-4 form-check">
                                    <input class="form-check-input" type="checkbox" value="tu15den20" id="tu15den20" />
                                    <label class="form-check-label" for="tu15den20"> Từ 15 đến 20 triệu </label>
                                </div>
                                <div class="col-4 form-check">
                                    <input class="form-check-input" type="checkbox" value="tren20" id="tren20" />
                                    <label class="form-check-label" for="tren20"> Trên 20 triệu </label>
                                </div>
                            </div>
                            <!-- Sắp xếp -->
                            <div class="row g-1 mb-3">
                                <p class="fw-bolder">Sắp xếp</p>
                                <div class="col-4 form-check">
                                    <input class="form-check-input" name="flexRadioDefault" type="radio" value="giatangdan" id="giatangdan" />
                                    <label class="form-check-label" for="giatangdan">Gía tăng dần </label>
                                </div>
                                <div class="col-4 form-check">
                                    <input class="form-check-input" name="flexRadioDefault" type="radio" value="giagiamdan" id="giagiamdan" />
                                    <label class="form-check-label" for="giagiamdan"> Gía giảm dần </label>
                                </div>
                                <div class="col-4 form-check">
                                    <input class="form-check-input" name="flexRadioDefault" type="radio" value="kosap" id="kosap" />
                                    <label class="form-check-label" for="kosap"> Không sắp xếp </label>
                                </div>
                            </div>

                            <div class="text-end">
                                <button type="submit" class="btn btn-success mt-4">Lọc sản phẩm</button>
                            </div>
                        </form>
                    </div>
                    <div class="col-8">
                        <div class="tab-class text-center">
                            <div class="row g-4">
                                <div class="col-lg-4 text-start">
                                    <h1>Products</h1>
                                </div>
                                <div class="col-lg-8 text-end">
                                    <ul class="nav nav-pills d-inline-flex text-center mb-5">
                                        <li class="nav-item">
                                            <a class="d-flex m-2 py-2 bg-light rounded-pill active" data-bs-toggle="pill" href="#tab-1">
                                                <span class="text-dark" style="width: 130px">All Products</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="tab-content">
                                <div id="tab-1" class="tab-pane fade show p-0 active">
                                    <div class="row g-4">
                                        <div class="col-lg-12">
                                            <div class="row g-4">
                                                <!-- Loop -->
                                                <c:forEach var="product" items="${products}">
                                                    <div class="col-md-6 col-lg-4">
                                                        <div class="rounded position-relative fruite-item">
                                                            <div class="fruite-img">
                                                                <img
                                                                    src="/images/product/${product.image}"
                                                                    class="img-fluid w-100 rounded-top"
                                                                    alt=""
                                                                />
                                                            </div>
                                                            <div
                                                                class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                                                                style="top: 10px; left: 10px"
                                                            >
                                                                ${product.name}
                                                            </div>
                                                            <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                                <h5 style="font-size: small">
                                                                    <a href="/product/${product.id}">${product.name}</a>
                                                                </h5>

                                                                <p style="font-size: x-small">${product.shortDesc}</p>

                                                                <div class="d-flex justify-content-center flex-lg-wrap">
                                                                    <p class="text-dark fs-5 fw-bold mb-4">
                                                                        <fmt:formatNumber type="number" value=" ${product.price}" />
                                                                        đ
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <!-- Pagination -->
                                            <div class="row mt-5">
                                                <nav aria-label="Page navigation example">
                                                    <ul class="pagination justify-content-center d-flex">
                                                        <li class="page-item">
                                                            <a
                                                                class="page-link ${(currentPage) eq 1 ? 'disabled ' : ''}"
                                                                href="/product?page=${currentPage-1}"
                                                                aria-label="Previous"
                                                            >
                                                                <span aria-hidden="true">&laquo;</span>
                                                            </a>
                                                        </li>

                                                        <c:forEach begin="1" end="${totalPages}" varStatus="loop">
                                                            <li class="page-item">
                                                                <a
                                                                    class="page-link ${(loop.index) eq currentPage ? 'active' : ''}"
                                                                    href="/product?page=${loop.index}"
                                                                    >${loop.index}</a
                                                                >
                                                            </li>
                                                        </c:forEach>

                                                        <li class="page-item">
                                                            <a
                                                                class="page-link ${(currentPage) eq totalPages ? 'disabled ' : ''}"
                                                                href="/product?page=${currentPage+1}"
                                                                aria-label="Next"
                                                            >
                                                                <span aria-hidden="true">&raquo;</span>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </nav>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Fruits Shop End-->

        <!-- Back to Top -->
        <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>

        <!-- JavaScript Libraries -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="/client/lib/easing/easing.min.js"></script>
        <script src="/client/lib/waypoints/waypoints.min.js"></script>
        <script src="/client/lib/lightbox/js/lightbox.min.js"></script>
        <script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Template Javascript -->
        <script src="/client/js/main.js"></script>
    </body>
</html>
