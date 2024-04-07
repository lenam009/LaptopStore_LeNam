<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form"%> <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link href="../css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <jsp:include page="../layout/header.jsp" />

        <div id="layoutSidenav">
            <jsp:include page="../layout/sidebar.jsp" />

            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Product</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active"><a href="/admin">Dashboard</a></li>
                            <li class="breadcrumb-item active"><a>Manage Products</a></li>
                        </ol>

                        <div class="row">
                            <div class="col-12 mx-auto">
                                <div class="d-flex justify-content-between">
                                    <h2>Table Product</h2>
                                    <a href="/admin/product/create" class="btn btn-primary d-flex align-items-center">Create a product</a>
                                </div>
                                <hr />

                                <table class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Name</th>
                                            <th>Price</th>
                                            <th>Factory</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="product" items="${products}">
                                            <tr>
                                                <th>${product.id}</th>
                                                <td>${product.name}</td>
                                                <td><fmt:formatNumber type="number" value="${product.price}" /> đ</td>
                                                <td>${product.factory}</td>
                                                <td>
                                                    <a href="/admin/product/${product.id}" class="btn btn-success">View</a>
                                                    <a href="/admin/product/update/${product.id}" class="btn btn-warning mx-2">Update</a>
                                                    <a href="/admin/product/delete/${product.id}" class="btn btn-danger">Delete</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                                <nav aria-label="Page navigation example">
                                    <ul class="pagination justify-content-center">
                                        <li class="page-item">
                                            <a
                                                class="page-link ${(currentPage) eq 1 ? 'disabled ' : ''}"
                                                href="/admin/product?page=${currentPage-1}"
                                                aria-label="Previous"
                                            >
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>

                                        <c:forEach begin="1" end="${totalPages}" varStatus="loop">
                                            <li class="page-item">
                                                <a
                                                    class="page-link ${(loop.index) eq currentPage ? 'active' : ''}"
                                                    href="/admin/product?page=${loop.index}"
                                                    >${loop.index}</a
                                                >
                                            </li>
                                        </c:forEach>

                                        <li class="page-item">
                                            <a
                                                class="page-link ${(currentPage) eq totalPages ? 'disabled ' : ''}"
                                                href="/admin/product?page=${currentPage+1}"
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
                </main>

                <jsp:include page="../layout/footer.jsp" />
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="../js/scripts.js"></script>
    </body>
</html>
