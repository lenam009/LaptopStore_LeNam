<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@taglib uri="http://www.springframework.org/tags/form"
prefix="form"%>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link href="../../css/styles.css" rel="stylesheet" />
        <script
            src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
            crossorigin="anonymous"
        ></script>
    </head>
    <body class="sb-nav-fixed">
        <jsp:include page="../layout/header.jsp" />

        <div id="layoutSidenav">
            <jsp:include page="../layout/sidebar.jsp" />

            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Manage Product</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active"><a href="/admin">Dashboard</a></li>
                            <li class="breadcrumb-item active"><a>Product Detail</a></li>
                        </ol>
                        <!-- Content -->
                        <div class="row">
                            <div class="col-12 mx-auto">
                                <div class="d-flex justify-content-between">
                                    <h2>User Detail with id: ${product.id}</h2>
                                </div>

                                <img
                                    style="height: 69px"
                                    class="${empty product.image ? 'd-none ' : ''}"
                                    id="avatarPreview"
                                    src="/images/product/${product.image}"
                                />

                                <hr />

                                <div class="card" style="width: 60%">
                                    <div class="card-header">Product information</div>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">ID: ${product.id}</li>
                                        <li class="list-group-item">Name: ${product.name}</li>
                                        <li class="list-group-item">Price: ${product.price}</li>
                                        <li class="list-group-item">
                                            Detail desc: ${product.detailDesc}
                                        </li>
                                        <li class="list-group-item">
                                            Short desc: ${product.shortDesc}
                                        </li>
                                        <li class="list-group-item">Factory: ${product.factory}</li>
                                        <li class="list-group-item">Target: ${product.target}</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>

                <jsp:include page="../layout/footer.jsp" />
            </div>
        </div>

        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            crossorigin="anonymous"
        ></script>
        <script src="../../js/scripts.js"></script>
    </body>
</html>
