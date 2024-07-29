'use client';

import { Pagination } from 'antd';

import CreateProduct from './product.create.admin';
import { useState } from 'react';
import { revalidateGetProducts } from '@/utils/actions/actions';
import { useRouter } from 'next/navigation';
import page from '@/app/(user)/@header/page';

interface IProps {
    meta: IMeta | undefined;
    products: IProduct[] | undefined;
}

function ProductPage({ meta, products }: IProps) {
    const [isOpen, setOpen] = useState<boolean>(false);

    const router = useRouter();

    const handleOnChangePage = (page: number, pageSize: number) => {
        router.replace(`/admin/product/?page=${page}&size=${pageSize}`, {
            scroll: false,
        });
    };

    return (
        <main>
            <CreateProduct isOpen={isOpen} setOpen={setOpen} />

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
                            <button
                                onClick={() => setOpen((prev) => true)}
                                className="btn btn-primary d-flex align-items-center"
                            >
                                Create a product
                            </button>
                        </div>
                        <hr />

                        <table className="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Factory</th>
                                    <th>Image</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                {products &&
                                    products.map((item) => (
                                        <tr>
                                            <th>{item.id}</th>
                                            <td>{item.name}</td>
                                            <td>{item.price}</td>
                                            <td>{item.factory}</td>
                                            <td>
                                                <img
                                                    src={
                                                        process.env
                                                            .NEXT_PUBLIC_BACKEND_URL +
                                                        '/storage/products/' +
                                                        item.image
                                                    }
                                                    width={100}
                                                />
                                            </td>
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
                                    ))}
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

export default ProductPage;
