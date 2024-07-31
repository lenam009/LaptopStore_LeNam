'use server';

import { sendRequest } from '../api';
import { getServerSession } from 'next-auth/next';
import { authOptions } from '@/app/api/auth/[...nextauth]/route';
import { revalidateTag } from 'next/cache';
import { number } from 'yup';

export const handleSignInAction = async (email: string, password: string) => {
    const action = (await sendRequest<IBackendRes<ILogin>>({
        url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/auth/login`,
        method: 'POST',
        headers: {
            // cache: 'no-store',
        },
        body: { email, password },
    })
        .then((res) => {
            return res;
        })
        .catch((error) => {
            console.log('error handleSignInAction', error);
            return error;
        })) as IBackendRes<ILogin>;

    return action;
};

export const handleGetCurrentUser = async () => {
    const session = await getServerSession(authOptions);

    const action = (await sendRequest<IBackendRes<IUser>>({
        url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/auth/account`,
        method: 'GET',
        headers: {
            Authorization: `Bearer ${session?.access_token}`,
            next: { tags: ['handleGetCurrentUser'] },
            // cache: 'no-store',
        },
    })
        .then((res) => {
            return res;
        })
        .catch((error) => {
            console.log('error handleGetCurrentUser', error);
            return error;
        })) as IBackendRes<IUser>;

    return action;
};

export const handleGetDashboard = async () => {
    const session = await getServerSession(authOptions);

    const action = (await sendRequest<IBackendRes<IDashBoard>>({
        url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/dashboards`,
        method: 'GET',
        headers: {
            Authorization: `Bearer ${session?.access_token}`,
            next: { tags: ['handleGetDashboard'] },
        },
    })
        .then((res) => {
            return res;
        })
        .catch((error) => {
            console.log('error handleGetDashboard', error);
            return error;
        })) as IBackendRes<IDashBoard>;

    return action;
};

export const handleGetProducts = async (page: number, size: number) => {
    const session = await getServerSession(authOptions);

    const action = (await sendRequest<IBackendRes<IModelPaginate<IProduct>>>({
        url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/products`,
        method: 'GET',
        headers: {
            Authorization: `Bearer ${session?.access_token}`,
            next: { tags: ['handleGetProducts'] },
        },
        queryParams: { page, size },
    })
        .then((res) => {
            return res;
        })
        .catch((error) => {
            console.log('error handleGetProducts', error);
            return error;
        })) as IBackendRes<IModelPaginate<IProduct>>;

    return action;
};

export const handleGetOrders = async (page: number, size: number) => {
    const session = await getServerSession(authOptions);

    const action = (await sendRequest<IBackendRes<IModelPaginate<IOrder>>>({
        url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/orders`,
        method: 'GET',
        headers: {
            Authorization: `Bearer ${session?.access_token}`,
            next: { tags: ['handleGetOrders'] },
        },
        queryParams: { page, size },
    })
        .then((res) => {
            return res;
        })
        .catch((error) => {
            console.log('error handleGetOrders', error);
            return error;
        })) as IBackendRes<IModelPaginate<IOrder>>;

    return action;
};

export const handleGetUsers = async (page: number, size: number) => {
    const session = await getServerSession(authOptions);

    const action = (await sendRequest<IBackendRes<IModelPaginate<IUser>>>({
        url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/users`,
        method: 'GET',
        headers: {
            Authorization: `Bearer ${session?.access_token}`,
            next: { tags: ['handleGetUsers'] },
        },
        queryParams: { page, size },
    })
        .then((res) => {
            return res;
        })
        .catch((error) => {
            console.log('error handleGetUsers', error);
            return error;
        })) as IBackendRes<IModelPaginate<IUser>>;

    return action;
};

export const handleUploadFile = async (
    fileFormData: FormData,
    folder: 'products' | 'users',
) => {
    const session = await getServerSession(authOptions);

    const action = (await sendRequest<IBackendRes<IUploadFile>>({
        url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/files`,
        method: 'POST',
        headers: {
            Authorization: `Bearer ${session?.access_token}`,
        },
        body: fileFormData,
        queryParams: { folder },
    })
        .then((res) => {
            return res;
        })
        .catch((error) => {
            console.log('error handleUploadFile', error);
            return error;
        })) as IBackendRes<IUploadFile>;

    return action;
};

export const handleCreateProduct = async (data: IProduct) => {
    const session = await getServerSession(authOptions);

    const action = (await sendRequest<IBackendRes<IProduct>>({
        url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/products`,
        method: 'POST',
        headers: {
            Authorization: `Bearer ${session?.access_token}`,
        },
        body: data,
    })
        .then((res) => {
            return res;
        })
        .catch((error) => {
            console.log('error handleCreateProduct', error);
            return error;
        })) as IBackendRes<IProduct>;

    return action;
};

export const handleGetCartByUser = async () => {
    const session = await getServerSession(authOptions);

    const action = (await sendRequest<IBackendRes<ICart>>({
        url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/carts`,
        method: 'GET',
        headers: {
            Authorization: `Bearer ${session?.access_token}`,
        },
    })
        .then((res) => {
            return res;
        })
        .catch((error) => {
            console.log('error handleGetCartByUser', error);
            return error;
        })) as IBackendRes<ICart>;

    return action;
};

export const handleAddToCart = async (idProduct: string) => {
    const session = await getServerSession(authOptions);

    const action = (await sendRequest<IBackendRes<IProduct>>({
        url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/carts/${idProduct}`,
        method: 'POST',
        headers: {
            Authorization: `Bearer ${session?.access_token}`,
        },
    })
        .then((res) => {
            return res;
        })
        .catch((error) => {
            console.log('error handleAddToCart', error);
            return error;
        })) as IBackendRes<IProduct>;

    return action;
};

export const handleCheckout = async ({
    receiverName,
    receiverAddress,
    receiverPhone,
}: {
    receiverName: string;
    receiverAddress: string;
    receiverPhone: string;
}) => {
    const session = await getServerSession(authOptions);

    const action = (await sendRequest<IBackendRes<IOrder>>({
        url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/items/place-order`,
        method: 'POST',
        headers: {
            Authorization: `Bearer ${session?.access_token}`,
        },
        body: { receiverName, receiverAddress, receiverPhone },
    })
        .then((res) => {
            return res;
        })
        .catch((error) => {
            console.log('error handleCheckout', error);
            return error;
        })) as IBackendRes<IProduct>;

    return action;
};

export const revalidateGetCurrentUser = () => {
    revalidateTag('handleGetCurrentUser');
};

export const revalidateGetDashboard = () => {
    revalidateTag('handleGetDashboard');
};

export const revalidateGetProducts = () => {
    revalidateTag('handleGetProducts');
};

export const revalidateGetOrders = () => {
    revalidateTag('handleGetOrders');
};

export const revalidateGetUsers = () => {
    revalidateTag('handleGetUsers');
};

export const revalidateGetCartByUser = () => {
    revalidateTag('handleGetCartByUser');
};
