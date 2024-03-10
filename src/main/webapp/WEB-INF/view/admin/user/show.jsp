<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@taglib uri="http://www.springframework.org/tags/form"
prefix="form"%>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link href="../css/styles.css" rel="stylesheet" />
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
                        <h1 class="mt-4">Manage Users</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active"><a href="/admin">Dashboard</a></li>
                            <li class="breadcrumb-item active"><a>Manage Users</a></li>
                        </ol>
                        <!-- Content -->
                        <div class="row">
                            <div class="col-12 mx-auto">
                                <div class="d-flex justify-content-between">
                                    <h2>Table-User</h2>
                                    <a
                                        href="/admin/user/create"
                                        class="btn btn-primary d-flex align-items-center"
                                        >Create a user</a
                                    >
                                </div>
                                <hr />

                                <table class="table table-bordered table-hover">
                                    <thead>
                                        <th>Id</th>
                                        <th>Email</th>
                                        <th>Full Name</th>
                                        <th>Action</th>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="user" items="${users}">
                                            <tr>
                                                <td>${user.id}</td>
                                                <th>${user.email}</th>
                                                <td>${user.fullName}</td>
                                                <td>
                                                    <a
                                                        href="/admin/user/${user.id}"
                                                        class="btn btn-success"
                                                        >View</a
                                                    >
                                                    <a
                                                        href="/admin/user/update/${user.id}"
                                                        class="btn btn-warning mx-2"
                                                        >Update</a
                                                    >
                                                    <a
                                                        href="/admin/user/delete/${user.id}"
                                                        class="btn btn-danger"
                                                        >Delete</a
                                                    >
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
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
        <script src="../js/scripts.js"></script>
    </body>
</html>
