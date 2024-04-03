<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link href="../../../css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
            integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        ></script>
    </head>
    <body>
        <jsp:include page="../layout/header.jsp" />

        <div id="layoutSidenav">
            <jsp:include page="../layout/sidebar.jsp" />

            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4"></h1>

                        <!-- Content -->
                        <div class="row">
                            <div class="col-md-6 col-12 mx-auto">
                                <h1 class="mt-4">Update Order</h1>
                                <form:form method="post" action="/admin/order/update" modelAttribute="order">
                                    <div class="row mb-3">
                                        <label for="exampleInputEmail1" class="form-label">Name:</label>

                                        <form:input
                                            type="text"
                                            path="user.fullName"
                                            class="form-control"
                                            id="exampleInputEmail1"
                                            aria-describedby="emailHelp"
                                        />
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col">
                                            <label for="exampleInputFullName" class="form-label">Status:</label>
                                            <form:select class="form-select" aria-label="Default select example" path="status">
                                                <form:option value="COMPLETED">COMPLETED</form:option>
                                                <form:option value="PENDING">PENDING</form:option>
                                                <form:option value="SHIPPING">SHIPPING</form:option>
                                            </form:select>
                                            <form:input type="text" path="id" style="display: none" />
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
    </body>
</html>
