import HomepageUser from '@/components/Page/User/homepage.user';
import routes from '@/config/routes/routes';
import { handleGetCartByUser, handleGetProducts } from '@/utils/actions/actions';
import { redirect } from 'next/navigation';

interface IProps {
    searchParams: {
        page: number;
        size: number;
    };
}

export default async function HomePageUser({ searchParams }: IProps) {
    const page = (searchParams && searchParams.page) ?? 1;
    const size = (searchParams && searchParams.size) ?? 8;

    const products = await handleGetProducts(page, size);

    return <HomepageUser meta={products.data?.meta} products={products.data?.result} />;
}
