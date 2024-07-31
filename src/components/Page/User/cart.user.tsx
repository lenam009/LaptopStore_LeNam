'use client';

import { Input, Form, Button, Popconfirm, PopconfirmProps, message } from 'antd';
import Link from 'next/link';
import React from 'react';

import { handleCheckout, revalidateGetCartByUser } from '@/utils/actions/actions';
import { useAppDispatch, useAppSelector } from '@/utils/redux/hook';
import { setCart, getCartSelector } from '@/utils/redux/slice/cartSlice';

type FieldType = {
    receiverName: '';
    receiverAddress: '';
    receiverPhone: '';
};

interface IProps {
    cart: ICart | undefined;
}

export default function CartUser() {
    const [form] = Form.useForm<FieldType>();

    const cartCurrent = useAppSelector(getCartSelector);

    console.log('cartCurrent', cartCurrent);

    const dispatch = useAppDispatch();

    const confirm: PopconfirmProps['onConfirm'] = (e: any) => {
        e.preventDefault();
        form.submit();
    };

    const onFinish = async (values: FieldType) => {
        const data: FieldType = { ...values };

        const result = await handleCheckout(data);

        if (result.data) {
            await revalidateGetCartByUser();
            message.success('Checkout success');
        } else {
            message.error(result.message);
        }

        // console.log('data', values);
    };

    const onFinishFailed = (errorInfo: any) => {
        console.log('Failed:', errorInfo);
    };

    const isEmpty = !!!cartCurrent;

    return (
        <>
            <div className="container-fluid py-3 ">
                <div className="container ">
                    <h1 className="text-danger">{isEmpty && 'Gio hàng rỗng'}</h1>
                </div>

                {!isEmpty && (
                    <div className="container ">
                        <div className="table-responsive">
                            <table className="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Products</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Quantity</th>
                                        <th scope="col">Total</th>
                                        <th scope="col">Handle</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {cartCurrent &&
                                        cartCurrent.cartDetails.map((item) => (
                                            <tr>
                                                <th scope="row">
                                                    <div className="d-flex align-items-center">
                                                        <img
                                                            src={
                                                                process.env
                                                                    .NEXT_PUBLIC_BACKEND_URL +
                                                                '/storage/products/' +
                                                                item.product.image
                                                            }
                                                            width={100}
                                                        />
                                                    </div>
                                                </th>
                                                <td>
                                                    <p className="mb-0 mt-4">
                                                        {item.product.name}
                                                    </p>
                                                </td>
                                                <td>
                                                    <p className="mb-0 mt-4">
                                                        {item.price} đ
                                                    </p>
                                                </td>
                                                <td>
                                                    <p className="mb-0 mt-4">
                                                        {item.quantity}
                                                    </p>
                                                </td>
                                                <td>
                                                    <p
                                                        className="mb-0 mt-4"
                                                        data-cart-detail-id="${cartDetail.id}"
                                                    >
                                                        {item.price * item.quantity} đ
                                                    </p>
                                                </td>
                                                <td>
                                                    <button className="btn btn-md rounded-circle bg-light border mt-4">
                                                        <i className="fa fa-times text-danger"></i>
                                                    </button>
                                                </td>
                                            </tr>
                                        ))}
                                </tbody>
                            </table>
                        </div>

                        <Form
                            form={form}
                            onFinish={onFinish}
                            onFinishFailed={onFinishFailed}
                        >
                            <div className="row g-4">
                                <div className="col-4"></div>
                                <div className="col-sm-8 col-md-7 col-lg-6 col-xl-8">
                                    <div className="bg-light rounded">
                                        <div className="p-4">
                                            <h1 className="display-6 mb-4">
                                                Cart{' '}
                                                <span className="fw-normal">Total</span>
                                            </h1>

                                            <div className="d-flex justify-content-between mb-4">
                                                <h5 className="mb-0 me-4">
                                                    Receiver Name:
                                                </h5>
                                                <span
                                                    className="mb-0"
                                                    data-cart-total-price="${total}"
                                                >
                                                    <Form.Item<FieldType> name="receiverName">
                                                        <Input />
                                                    </Form.Item>
                                                </span>
                                            </div>

                                            <div className="d-flex justify-content-between mb-4">
                                                <h5 className="mb-0 me-4">
                                                    Receiver Address:
                                                </h5>
                                                <span
                                                    className="mb-0"
                                                    data-cart-total-price="${total}"
                                                >
                                                    <Form.Item<FieldType> name="receiverAddress">
                                                        <Input />
                                                    </Form.Item>
                                                </span>
                                            </div>

                                            <div className="d-flex justify-content-between mb-4">
                                                <h5 className="mb-0 me-4">
                                                    Receiver Phone:
                                                </h5>
                                                <span
                                                    className="mb-0"
                                                    data-cart-total-price="${total}"
                                                >
                                                    <Form.Item<FieldType> name="receiverPhone">
                                                        <Input />
                                                    </Form.Item>
                                                </span>
                                            </div>
                                        </div>
                                        <div className="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
                                            <h5 className="mb-0 ps-4 me-4">Total</h5>
                                            <p
                                                className="mb-0 pe-4"
                                                data-cart-total-price="${total}"
                                            >
                                                {cartCurrent.totalPrice} đ
                                            </p>
                                        </div>

                                        <div className="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
                                            <h5 className="mb-0 ps-4 me-4"></h5>
                                            <p className="mb-0 pe-4">
                                                <Popconfirm
                                                    title="Checkout cart"
                                                    description="Are you sure checkout this cart?"
                                                    onConfirm={confirm}
                                                    okText="Yes"
                                                    cancelText="No"
                                                >
                                                    <Button type="primary">
                                                        Checkout
                                                    </Button>
                                                </Popconfirm>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </Form>
                    </div>
                )}
            </div>
        </>
    );
}
