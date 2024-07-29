'use client';

import { Pagination } from 'antd';
import Link from 'next/link';
import { useRouter } from 'next/navigation';

interface IProps {
    meta: IMeta | undefined;
    orders: IOrder[] | undefined;
}

function OrderPage({ meta, orders }: IProps) {
    const router = useRouter();

    const handleOnChangePage = (page: number, pageSize: number) => {
        router.replace(`/admin/order/?page=${page}&size=${pageSize}`, {
            scroll: false,
        });
    };

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
                                {orders &&
                                    orders.map((item) => (
                                        <tr>
                                            <th>{item.id}</th>
                                            <td>{item.totalPrice}</td>
                                            <td>{item.user?.fullName}</td>
                                            <td>{item.status}</td>
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
                                    ))}

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
                            </tbody>
                        </table>

                        <nav aria-label="Page navigation example">
                            <ul className="pagination justify-content-center">
                                <Pagination
                                    defaultCurrent={1}
                                    total={meta?.total}
                                    current={meta?.page}
                                    pageSize={meta?.pageSize}
                                    onChange={(page, number) =>
                                        handleOnChangePage(page, number)
                                    }
                                />
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </main>
    );
}

export default OrderPage;
