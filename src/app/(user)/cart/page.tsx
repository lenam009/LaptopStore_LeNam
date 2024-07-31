import React from 'react';

import CartUser from '@/components/Page/User/cart.user';
import { handleGetCartByUser } from '@/utils/actions/actions';

export default async function CartPage() {
    const cart = await handleGetCartByUser();

    console.log('cart123', cart);

    // console.log('cart123', cart);

    return (
        <>
            <CartUser />
        </>
    );
}
