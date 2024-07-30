'use client';
import React from 'react';
import { useAppDispatch } from '@/utils/redux/hook';
import { useEffect } from 'react';
import { useSession } from 'next-auth/react';
import { useRouter } from 'next/navigation';
import routes from '@/config/routes/routes';
import { usePathname } from 'next/navigation';

import { setUser } from '../slice/userSlice';

export default function ReduxChangeState({
    children,
    user,
}: {
    children: React.ReactNode;
    user: IBackendRes<IUser> | null;
}) {
    const dispatch = useAppDispatch();
    const pathname = usePathname();

    const router = useRouter();

    useEffect(() => {
        if (user && user.data) {
            dispatch(setUser(user.data));

            // console.log('Redux fetch API', pathname, user.data);

            // if (
            //     user.data.role.id === 'ADMIN' &&
            //     !pathname.startsWith(routes.home.admin.path)
            // ) {
            //     router.push(routes.home.admin.path);
            // } else if (
            //     user.data.role.id === 'USER' &&
            //     !pathname.startsWith(routes.home.user.path)
            // ) {
            //     router.push(routes.home.user.path);
            // }
        }
    }, [user]);

    return (
        <>
            {/* <h1 style={{ marginTop: '60px' }}>{JSON.stringify(user)}</h1> */}
            {children}
        </>
    );
}
