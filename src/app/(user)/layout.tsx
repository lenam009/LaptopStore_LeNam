import Box from '@mui/material/Box';
import Header from '@/components/Header/header';
import HeaderUserPage from '@/components/Layout/User/header.user';

import '../../../public/client/lib/lightbox/css/lightbox.min.css';
import '../../../public/client/lib/owlcarousel/assets/owl.carousel.min.css';
import '../../../public/client/css/bootstrap.min.css';
import '../../../public/client/css/style.css';

export default function UserLayout({ children }: { children: React.ReactNode }) {
    return (
        <Box>
            <link
                rel="stylesheet"
                href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
            />
            <link
                href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                rel="stylesheet"
            />

            <HeaderUserPage />
            <div className="container-fluid  py-5">
                <div className="container py-5 px-0"> {children}</div>
            </div>
        </Box>
    );
}
