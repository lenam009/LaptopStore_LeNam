<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@taglib uri="http://www.springframework.org/tags/form"
prefix="form"%>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link href="../../../css/styles.css" rel="stylesheet" />
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
                        <h1 class="mt-4">Delete User</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active"><a href="/admin">Dashboard</a></li>
                            <li class="breadcrumb-item active"><a>Delete User</a></li>
                        </ol>
                        <!-- Content -->
                        <div class="row">
                            <div class="col-md-12 col-12 mx-auto">
                                <div><h1>Delete user with id : ${id}</h1></div>

                                <hr />

                                <div class="alert alert-danger" role="alert">
                                    Are you sure delete this user ?
                                </div>

                                <div>
                                    <a href="/admin/user" class="btn btn-primary">Back</a>
                                    <div class="d-inline-block">
                                        <form:form
                                            method="post"
                                            action="/admin/user/delete"
                                            modelAttribute="user"
                                        >
                                            <div class="mb-3" style="display: none">
                                                <label for="exampleInputId" class="form-label"
                                                    >ID:</label
                                                >
                                                <form:input
                                                    type="text"
                                                    class="form-control"
                                                    path="id"
                                                    value="${id}"
                                                />
                                            </div>
                                            <button class="btn btn-danger ms-3">Confirm</button>
                                        </form:form>
                                    </div>
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
        <script src="../../../js/scripts.js"></script>
    </body>
</html>
