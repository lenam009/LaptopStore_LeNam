'use client';
import React from 'react';
import { useAppDispatch } from '@/utils/redux/hook';
import { useEffect } from 'react';
import { useSession } from 'next-auth/react';
import { useRouter } from 'next/navigation';
import routes from '@/config/routes/routes';
import { usePathname } from 'next/navigation';

import { setCart, setCartDetail } from '../../slice/cartSlice';
export default function ReduxChangeState({
    children,
    cart,
}: {
    children: React.ReactNode;
    cart: ICart | undefined | null;
}) {
    const dispatch = useAppDispatch();
    const pathname = usePathname();

    const router = useRouter();

    useEffect(() => {
        if (cart) {
            dispatch(setCart(cart));
        }
    }, [cart]);

    return (
        <>
            {/* <h1 style={{ marginTop: '60px' }}>{JSON.stringify(user)}</h1> */}
            {children}
        </>
    );
}
