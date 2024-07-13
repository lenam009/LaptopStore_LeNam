import Link from 'next/link';

function OrderPage() {
    return (
        <main>
            <div className="container-fluid px-4">
                <h1 className="mt-4">Manage Orders</h1>
                <ol className="breadcrumb mb-4">
                    <li className="breadcrumb-item active">
                        <Link href="/admin">Dashboard</Link>
                    </li>
                    <li className="breadcrumb-item active">
                        <a>Manage Order</a>
                    </li>
                </ol>
                {/* <!-- Content --> */}
                <div className="row">
                    <div className="col-12 mx-auto">
                        <div className="d-flex justify-content-between">
                            <h2>Table-Order</h2>
                        </div>
                        <hr />

                        <table className="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Total Price</th>
                                    <th>User</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                {/* <c:forEach var="order" items="${orders}">
                            <tr>
                                <th>${order.id}</th>
                                <td><fmt:formatNumber type="number" value="${order.totalPrice}" /> đ</td>
                                <td>${order.user.fullName}</td>
                                <td>${order.status}</td>
                                <td>
                                    <a href="/admin/order/${order.id}" className="btn btn-success">View</a>
                                    <a href="/admin/order/update/${order.id}" className="btn btn-warning mx-2">Update</a>
                                    <a href="/admin/user/delete/${user.id}" className="btn btn-danger">Delete</a>
                                </td>
                            </tr>
                        </c:forEach> */}

                                <tr>
                                    <th>1</th>
                                    <td>200 đ</td>
                                    <td>fullname 1</td>
                                    <td>status 1</td>
                                    <td>
                                        <a
                                            href="/admin/order/${order.id}"
                                            className="btn btn-success"
                                        >
                                            View
                                        </a>
                                        <a
                                            href="/admin/order/update/${order.id}"
                                            className="btn btn-warning mx-2"
                                        >
                                            Update
                                        </a>
                                        <a
                                            href="/admin/user/delete/${user.id}"
                                            className="btn btn-danger"
                                        >
                                            Delete
                                        </a>
                                    </td>
                                </tr>

                                <tr>
                                    <th>1 2</th>
                                    <td>200 đ 2</td>
                                    <td>fullname 1 2</td>
                                    <td>status 1 2</td>
                                    <td>
                                        <a
                                            href="/admin/order/${order.id}"
                                            className="btn btn-success"
                                        >
                                            View
                                        </a>
                                        <a
                                            href="/admin/order/update/${order.id}"
                                            className="btn btn-warning mx-2"
                                        >
                                            Update
                                        </a>
                                        <a
                                            href="/admin/user/delete/${user.id}"
                                            className="btn btn-danger"
                                        >
                                            Delete
                                        </a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
    );
}

export default OrderPage;
