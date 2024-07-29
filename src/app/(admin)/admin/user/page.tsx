import Box from '@mui/material/Box';
import UserPage from '@/components/Page/Admin/user.admin';
import { handleGetUsers } from '@/utils/actions/actions';

interface IProps {
    searchParams: {
        page: number;
        size: number;
    };
}

async function ProductAdminPage({ searchParams }: IProps) {
    const page = (searchParams && searchParams.page) ?? 1;
    const size = (searchParams && searchParams.size) ?? 3;

    const users = await handleGetUsers(page, size);

    return (
        <Box>
            <UserPage meta={users.data?.meta} users={users.data?.result} />
        </Box>
    );
}

export default ProductAdminPage;
