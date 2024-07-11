import Box from '@mui/material/Box';
import HeaderAdmin from '@/components/Layout/Admin/header.admin';
import SidebarAdmin from '@/components/Layout/Admin/sidebar.admin';

export default function UserLayout({
    children,
    header,
}: {
    children: React.ReactNode;
    header: React.ReactNode;
}) {
    return (
        <Box>
            <HeaderAdmin />

            <div id="layoutSidenav">
                <SidebarAdmin />

                <div id="layoutSidenav_content">
                    <main>{children}</main>
                </div>
            </div>
        </Box>
    );
}
