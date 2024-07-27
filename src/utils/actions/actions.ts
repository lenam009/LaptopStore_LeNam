'use server';

import { sendRequest } from '../api';
import { getServerSession } from 'next-auth/next';
import { authOptions } from '@/app/api/auth/[...nextauth]/route';
import { revalidateTag } from 'next/cache';

export const handleSignInAction = async (email: string, password: string) => {
    const signInAction = (await sendRequest<IBackendRes<IUser>>({
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
        })) as IBackendRes<IUser>;

    return signInAction;
};

export const revalidateGetOneUserById = () => {
    revalidateTag('handleGetOneUserById');
};
export const revalidateGetPostsFollowing = () => {
    revalidateTag('handleGetPostsFollowing');
};
export const revalidateGetPostsByUserId = () => {
    revalidateTag('handleGetPostsByUserId');
};
