import Link from 'next/link';

import { getServerSession } from 'next-auth/next';
import { authOptions } from '@/app/api/auth/[...nextauth]/route';
import routes from '@/config/routes/routes';

export default async function SidebarAdmin() {
    const session = await getServerSession(authOptions);

    return (
        <div id="layoutSidenav_nav">
            <nav className="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                <div className="sb-sidenav-menu">
                    <div className="nav">
                        <div className="sb-sidenav-menu-heading">Core</div>
                        <Link className="nav-link" href="/admin">
                            <div className="sb-nav-link-icon">
                                <i className="fas fa-tachometer-alt"></i>
                            </div>
                            Dashboard
                        </Link>

                        <Link className="nav-link" href={routes.admin.product.path}>
                            <div className="sb-nav-link-icon">
                                <i className="fas fa-tachometer-alt"></i>
                            </div>
                            Product
                        </Link>
                        <Link className="nav-link" href={routes.admin.order.path}>
                            <div className="sb-nav-link-icon">
                                <i className="fas fa-tachometer-alt"></i>
                            </div>
                            Order
                        </Link>

                        <Link className="nav-link" href={routes.admin.user.path}>
                            <div className="sb-nav-link-icon">
                                <i className="fas fa-tachometer-alt"></i>
                            </div>
                            User
                        </Link>
                    </div>
                </div>
                <div className="sb-sidenav-footer">
                    <div className="small">Logged in as:</div>
                    {session?.user.fullName}
                </div>
            </nav>
        </div>
    );
}
