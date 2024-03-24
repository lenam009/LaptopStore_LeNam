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
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
            integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        ></script>

        <script>
            $(document).ready(() => {
                const photoInp = $('#avatarFile');
                const fileName = '${newProduct.image}';

                if (fileName) {
                    const url = '/images/product/' + fileName;
                    $('#avatarPreview').attr('src', url);
                    $('#avatarPreview').css('display', 'block');
                }

                photoInp.change(function (e) {
                    file = this.files[0];
                    if (file) {
                        let reader = new FileReader();
                        reader.onload = function (event) {
                            $('#avatarPreview').attr('src', event.target.result);
                            $('#avatarPreview').css('display', 'block');
                        };
                        reader.readAsDataURL(file);
                    }
                });
            });
        </script>
    </head>
    <body class="sb-nav-fixed">
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
                                <h1 class="mt-4">Update Product</h1>
                                <form:form
                                    method="post"
                                    enctype="multipart/form-data"
                                    action="/admin/product/update"
                                    modelAttribute="newProduct"
                                >
                                    <form:input type="text" path="id" style="display: none" />
                                    <form:input type="text" path="image" style="display: none" />
                                    <!-- Email -->
                                    <div class="mb-3 row">
                                        <div class="col">
                                            <label for="exampleInputEmail1" class="form-label"
                                                >Name:</label
                                            >

                                            <c:set var="nameError">
                                                <form:errors
                                                    path="name"
                                                    cssClass="invalid-feedback"
                                                />
                                            </c:set>

                                            <form:input
                                                type="text"
                                                class="form-control ${not empty nameError ? 'is-invalid' : ''}"
                                                id="exampleInputEmail1"
                                                aria-describedby="emailHelp"
                                                path="name"
                                            />
                                            ${nameError}
                                        </div>
                                        <!-- Password -->
                                        <div class="col">
                                            <label for="exampleInputPassword1" class="form-label"
                                                >Price:</label
                                            >

                                            <c:set var="priceError">
                                                <form:errors
                                                    path="price"
                                                    cssClass="invalid-feedback"
                                                />
                                            </c:set>

                                            <form:input
                                                type="number"
                                                class="form-control ${not empty priceError ? 'is-invalid' : ''}"
                                                id="exampleInputPassword1"
                                                path="price"
                                            />

                                            ${priceError}
                                        </div>
                                    </div>
                                    <!-- Phone -->
                                    <div class="mb-3">
                                        <label for="exampleInputTel" class="form-label"
                                            >Detail description:</label
                                        >
                                        <form:textarea
                                            rows="3"
                                            type="text"
                                            class="form-control"
                                            id="exampleInputTel"
                                            path="detailDesc"
                                        />
                                    </div>

                                    <!-- Full name -->
                                    <div class="row mb-3">
                                        <div class="col">
                                            <label for="exampleInputFullName" class="form-label"
                                                >Short description:</label
                                            >
                                            <form:input
                                                type="text"
                                                class="form-control"
                                                id="exampleInputFullName"
                                                path="shortDesc"
                                            />
                                        </div>
                                        <div class="col">
                                            <label for="exampleInputAddress" class="form-label"
                                                >Quantity:</label
                                            >
                                            <c:set var="quantityError">
                                                <form:errors
                                                    path="quantity"
                                                    cssClass="invalid-feedback"
                                                />
                                            </c:set>

                                            <form:input
                                                class="form-control ${not empty quantityError ? 'is-invalid' : ''}"
                                                id="exampleInputAddress"
                                                path="quantity"
                                            />

                                            ${quantityError}
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col">
                                            <label for="exampleInputFullName" class="form-label"
                                                >Factory:</label
                                            >
                                            <form:select
                                                class="form-select"
                                                aria-label="Default select example"
                                                path="factory"
                                            >
                                                <form:option value="Apple">Apple</form:option>
                                                <form:option value="Asus">Asus</form:option>
                                                <form:option value="Lenovo">Lenovo</form:option>
                                                <form:option value="Dell">Dell</form:option>
                                                <form:option value="Gigabyte">Gigabyte</form:option>
                                            </form:select>
                                        </div>
                                        <div class="col">
                                            <label for="exampleInputFullName" class="form-label"
                                                >Target:</label
                                            >
                                            <form:select
                                                class="form-select"
                                                aria-label="Default select example"
                                                path="target"
                                            >
                                                <form:option value="Student - office"
                                                    >Student - office</form:option
                                                >
                                                <form:option value="Gaming">Gaming</form:option>
                                            </form:select>
                                        </div>
                                    </div>

                                    <div class="mb-3 row">
                                        <!-- Upload -->
                                        <div class="col-5">
                                            <label for="avatarFile" class="form-label"
                                                >Image:</label
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
