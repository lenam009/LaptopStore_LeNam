import Link from 'next/link';

function HeaderAdmin() {
    return (
        <nav className="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            {/* <!-- Navbar Brand--> */}
            <Link className="navbar-brand ps-3" href="/admin">
                Home
            </Link>
            {/* <!-- Sidebar Toggle--> */}
            <button
                className="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0"
                id="sidebarToggle"
            >
                <i className="fas fa-bars"></i>
            </button>
            {/* <!-- Navbar Search--> */}
            <div className="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                {/* <c:if test="${not empty sessionScope.fullName}">
                <h5 className="text-white m-0"><c:out value="${sessionScope.fullName}" /></h5>
            </c:if> */}
                <h5 className="text-white m-0">Le Nam</h5>
            </div>
            {/* <!-- Navbar--> */}
            <ul className="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li className="nav-item dropdown">
                    <a
                        className="nav-link dropdown-toggle"
                        id="navbarDropdown"
                        href="#"
                        role="button"
                        data-bs-toggle="dropdown"
                        aria-expanded="false"
                    >
                        <i className="fas fa-user fa-fw"></i>
                    </a>
                    <ul
                        className="dropdown-menu dropdown-menu-end"
                        aria-labelledby="navbarDropdown"
                    >
                        <li>
                            <a className="dropdown-item" href="#!">
                                Settings
                            </a>
                        </li>
                        <li>
                            <a className="dropdown-item" href="#!">
                                Activity Log
                            </a>
                        </li>
                        <li>
                            <hr className="dropdown-divider" />
                        </li>
                        <li>
                            <form method="post" action="/logout">
                                <button className="dropdown-item">Đăng xuất</button>
                                <input
                                    type="hidden"
                                    name="${_csrf.parameterName}"
                                    value="${_csrf.token}"
                                />
                            </form>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>
    );
}

export default HeaderAdmin;
