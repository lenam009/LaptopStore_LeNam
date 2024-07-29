'use server';

import { sendRequest } from '../api';
import { getServerSession } from 'next-auth/next';
import { authOptions } from '@/app/api/auth/[...nextauth]/route';
import { revalidateTag } from 'next/cache';
import { number } from 'yup';

export const handleSignInAction = async (email: string, password: string) => {
    const signInAction = (await sendRequest<IBackendRes<ILogin>>({
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

    return signInAction;
};

export const handleGetCurrentUser = async () => {
    const session = await getServerSession(authOptions);

    const signInAction = (await sendRequest<IBackendRes<IUser>>({
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

    return signInAction;
};

export const handleGetDashboard = async () => {
    const session = await getServerSession(authOptions);

    const signInAction = (await sendRequest<IBackendRes<IDashBoard>>({
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

    return signInAction;
};

export const handleGetProducts = async (page: number, size: number) => {
    const session = await getServerSession(authOptions);

    const signInAction = (await sendRequest<IBackendRes<IModelPaginate<IProduct>>>({
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

    return signInAction;
};

export const handleGetOrders = async (page: number, size: number) => {
    const session = await getServerSession(authOptions);

    const signInAction = (await sendRequest<IBackendRes<IModelPaginate<IOrder>>>({
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

    return signInAction;
};

export const handleGetUsers = async (page: number, size: number) => {
    const session = await getServerSession(authOptions);

    const signInAction = (await sendRequest<IBackendRes<IModelPaginate<IUser>>>({
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

    return signInAction;
};

export const handleUploadFile = async (
    fileFormData: FormData,
    folder: 'products' | 'users',
) => {
    const session = await getServerSession(authOptions);

    const signInAction = (await sendRequest<IBackendRes<IUploadFile>>({
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

    return signInAction;
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
