import { handleGetCartByUser, handleGetCurrentUser } from '@/utils/actions/actions';
import ReduxChangeState from './redux.changeState';
import { getServerSession } from 'next-auth/next';
import { authOptions } from '@/app/api/auth/[...nextauth]/route';

export default async function ReduxFetchApi({ children }: { children: React.ReactNode }) {
    const cart = await handleGetCartByUser();

    // console.log('cart', cart);

    return <ReduxChangeState cart={cart.data}>{children}</ReduxChangeState>;
}
