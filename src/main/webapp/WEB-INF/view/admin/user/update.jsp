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
                        <h1 class="mt-4">Update User</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active"><a href="/admin">Dashboard</a></li>
                            <li class="breadcrumb-item active"><a>Update User</a></li>
                        </ol>
                        <!-- Content -->
                        <div class="row">
                            <div class="col-md-6 col-12 mx-auto">
                                <form:form
                                    method="post"
                                    action="/admin/user/update"
                                    modelAttribute="currentUser"
                                >
                                    <!-- ID -->
                                    <div class="mb-3" style="display: none">
                                        <label for="exampleInputId" class="form-label">ID:</label>
                                        <form:input type="text" class="form-control" path="id" />
                                    </div>
                                    <!-- Email -->
                                    <div class="mb-3">
                                        <label for="exampleInputEmail1" class="form-label"
                                            >Email:</label
                                        >
                                        <form:input
                                            disabled="true"
                                            type="email"
                                            class="form-control"
                                            id="exampleInputEmail1"
                                            aria-describedby="emailHelp"
                                            path="email"
                                        />
                                    </div>
                                    <!-- Password -->
                                    <div class="mb-3">
                                        <label for="exampleInputPassword1" class="form-label"
                                            >Password:</label
                                        >
                                        <form:input
                                            disabled="true"
                                            type="password"
                                            class="form-control"
                                            id="exampleInputPassword1"
                                            path="password"
                                        />
                                    </div>
                                    <!-- Phone -->
                                    <div class="mb-3">
                                        <label for="exampleInputTel" class="form-label"
                                            >Phone number:</label
                                        >
                                        <form:input
                                            type="tel"
                                            class="form-control"
                                            id="exampleInputTel"
                                            path="phone"
                                        />
                                    </div>
                                    <!-- Full name -->
                                    <div class="mb-3">
                                        <label for="exampleInputFullName" class="form-label"
                                            >Full name:</label
                                        >
                                        <form:input
                                            class="form-control"
                                            id="exampleInputFullName"
                                            path="fullName"
                                        />
                                    </div>
                                    <!-- Address -->
                                    <div class="mb-3">
                                        <label for="exampleInputAddress" class="form-label"
                                            >Address:</label
                                        >
                                        <form:input
                                            class="form-control"
                                            id="exampleInputAddress"
                                            path="address"
                                        />
                                    </div>

                                    <div class="mb-3 row">
                                        <!-- Role-->
                                        <div class="col-5">
                                            <label for="exampleInputEmail1" class="form-label"
                                                >Role:</label
                                            >
                                            <form:select
                                                class="form-select"
                                                aria-label="Default select example"
                                                path="role"
                                            >
                                                <c:forEach var="roleOb" items="${roles}">
                                                    <form:option value="${roleOb}"
                                                        >${roleOb.name}</form:option
                                                    >
                                                </c:forEach>
                                            </form:select>
                                        </div>
                                        <!-- Upload -->
                                        <div class="col-5">
                                            <label for="avatarFile" class="form-label"
                                                >Avatar:</label
                                            >
                                            <div class="input-group mb-3">
                                                <input
                                                    type="file"
                                                    class="form-control"
                                                    id="avatarFile"
                                                    name="lenamFile"
                                                    accept=".png, .jpg, .jpeg"
                                                />
                                            </div>
                                        </div>
                                        <div class="col-2">
                                            <img
                                                width="100%"
                                                style="height: 69px; display: none"
                                                id="avatarPreview"
                                            />
                                        </div>
                                    </div>

                                    <button type="submit" class="btn btn-primary">Update</button>
                                </form:form>
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
