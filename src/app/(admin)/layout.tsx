import Box from '@mui/material/Box';
import HeaderAdmin from '@/components/Layout/Admin/header.admin';
import SidebarAdmin from '@/components/Layout/Admin/sidebar.admin';
import '../../../public/css/styles.css';

export default function UserLayout({ children }: { children: React.ReactNode }) {
    return (
        <Box>
            <script
                src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
                crossOrigin="anonymous"
            ></script>
            <script
                src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
                integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
                crossOrigin="anonymous"
                referrerPolicy="no-referrer"
            ></script>

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
