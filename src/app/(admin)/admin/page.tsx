import DashboardPage from '@/components/Page/Admin/dashboard.admin';
import { handleGetDashboard } from '@/utils/actions/actions';

async function DashboardAdminPage() {
    const dashBoard = await handleGetDashboard();

    return <DashboardPage dashBoard={dashBoard.data} />;
}

export default DashboardAdminPage;
