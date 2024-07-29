import Box from '@mui/material/Box';
import ProductPage from '@/components/Page/Admin/product.admin';
import { handleGetProducts, revalidateGetProducts } from '@/utils/actions/actions';

interface IProps {
    searchParams: {
        page: number;
        size: number;
    };
}

async function ProductAdminPage({ searchParams }: IProps) {
    const page = (searchParams && searchParams.page) ?? 1;
    const size = (searchParams && searchParams.size) ?? 3;

    const products = await handleGetProducts(page, size);

    return (
        <Box>
            <ProductPage meta={products.data?.meta} products={products.data?.result} />
        </Box>
    );
}

export default ProductAdminPage;
