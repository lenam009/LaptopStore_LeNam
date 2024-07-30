import HomepageUser from '@/components/Page/User/homepage.user';
import { handleGetCartByUser, handleGetProducts } from '@/utils/actions/actions';

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
