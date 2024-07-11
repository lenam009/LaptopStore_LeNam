import Box from '@mui/material/Box';

function ProductAdminPage() {
    return (
        <Box>
            <main>
                <div className="container-fluid px-4">
                    <h1 className="mt-4">Manage Products</h1>
                    <ol className="breadcrumb mb-4">
                        <li className="breadcrumb-item active">
                            <a href="/admin">Dashboard</a>
                        </li>
                        <li className="breadcrumb-item active">
                            <a>Manage Products</a>
                        </li>
                    </ol>

                    <div className="row">
                        <div className="col-12 mx-auto">
                            <div className="d-flex justify-content-between">
                                <h2>Table Product</h2>
                                <a
                                    href="/admin/product/create"
                                    className="btn btn-primary d-flex align-items-center"
                                >
                                    Create a product
                                </a>
                            </div>
                            <hr />

                            <table className="table table-bordered table-hover">
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
                                    {/* <c:forEach var="product" items="${products}">
                                            <tr>
                                                <th>${product.id}</th>
                                                <td>${product.name}</td>
                                                <td><fmt:formatNumber type="number" value="${product.price}" /> đ</td>
                                                <td>${product.factory}</td>
                                                <td>
                                                    <a href="/admin/product/${product.id}" className="btn btn-success">View</a>
                                                    <a href="/admin/product/update/${product.id}" className="btn btn-warning mx-2">Update</a>
                                                    <a href="/admin/product/delete/${product.id}" className="btn btn-danger">Delete</a>
                                                </td>
                                            </tr>
                                        </c:forEach> */}

                                    <tr>
                                        <th>1</th>
                                        <td>name 1</td>
                                        <td>200.000 đ</td>
                                        <td>factory 1</td>
                                        <td>
                                            <a
                                                href="/admin/product/${product.id}"
                                                className="btn btn-success"
                                            >
                                                View
                                            </a>
                                            <a
                                                href="/admin/product/update/${product.id}"
                                                className="btn btn-warning mx-2"
                                            >
                                                Update
                                            </a>
                                            <a
                                                href="/admin/product/delete/${product.id}"
                                                className="btn btn-danger"
                                            >
                                                Delete
                                            </a>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th>1 2</th>
                                        <td>name 1 2</td>
                                        <td>200.000 đ 2</td>
                                        <td>factory 1 2</td>
                                        <td>
                                            <a
                                                href="/admin/product/${product.id}"
                                                className="btn btn-success"
                                            >
                                                View
                                            </a>
                                            <a
                                                href="/admin/product/update/${product.id}"
                                                className="btn btn-warning mx-2"
                                            >
                                                Update
                                            </a>
                                            <a
                                                href="/admin/product/delete/${product.id}"
                                                className="btn btn-danger"
                                            >
                                                Delete
                                            </a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>

                            <nav aria-label="Page navigation example">
                                <ul className="pagination justify-content-center">
                                    <li className="page-item">
                                        <a
                                            className="page-link ${(currentPage) eq 1 ? 'disabled ' : ''}"
                                            href="/admin/product?page=${currentPage-1}"
                                            aria-label="Previous"
                                        >
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>

                                    {/* <c:forEach begin="1" end="${totalPages}" varStatus="loop">
                                            <li className="page-item">
                                                <a
                                                    className="page-link ${(loop.index) eq currentPage ? 'active' : ''}"
                                                    href="/admin/product?page=${loop.index}"
                                                    >${loop.index}</a
                                                >
                                            </li>
                                        </c:forEach> */}

                                    <li className="page-item">
                                        <a
                                            className="page-link ${(loop.index) eq currentPage ? 'active' : ''}"
                                            href="/admin/product?page=${loop.index}"
                                        >
                                            1
                                        </a>
                                    </li>

                                    <li className="page-item">
                                        <a
                                            className="page-link ${(currentPage) eq totalPages ? 'disabled ' : ''}"
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
        </Box>
    );
}

export default ProductAdminPage;
