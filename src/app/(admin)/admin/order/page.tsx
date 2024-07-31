import Link from 'next/link';
import OrderPage from '@/components/Page/Admin/order.admin';

import { handleGetOrders } from '@/utils/actions/actions';

interface IProps {
    searchParams: {
        page: number;
        size: number;
    };
}

async function OrderAdminPage({ searchParams }: IProps) {
    const page = (searchParams && searchParams.page) ?? 1;
    const size = (searchParams && searchParams.size) ?? 3;

    const orders = await handleGetOrders(page, size);

    return <OrderPage meta={orders.data?.meta} orders={orders.data?.result} />;
}

export default OrderAdminPage;
