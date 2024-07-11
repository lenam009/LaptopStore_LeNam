import Box from '@mui/material/Box';

function HeaderUserPage() {
    const isLogin = true;

    return (
        <div className="container-fluid fixed-top">
            <div className="container px-0">
                <nav className="navbar navbar-light bg-white navbar-expand-xl">
                    <a href="/" className="navbar-brand">
                        <h1 className="text-primary display-6">Le Nam</h1>
                    </a>
                    <button
                        className="navbar-toggler py-2 px-3"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarCollapse"
                    >
                        <span className="fa fa-bars text-primary"></span>
                    </button>
                    <div
                        className="collapse navbar-collapse bg-white justify-content-between mx-5"
                        id="navbarCollapse"
                    >
                        <div className="navbar-nav">
                            <a href="/" className="nav-item nav-link active">
                                Home
                            </a>
                            <a href="/product" className="nav-item nav-link">
                                Product
                            </a>
                        </div>

                        <div className="d-flex m-3 me-0">
                            {/* <c:if test="${not empty sessionScope.fullName}">
                                <span className="my-auto mx-3">
                                    <c:out value="${sessionScope.fullName}" />
                                </span>

                                <a
                                    href="/cart"
                                    className="position-relative me-4 my-auto"
                                >
                                    <i className="fa fa-shopping-bag fa-2x"></i>
                                    <span
                                        className="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
                                        style="top: -5px; left: 15px; height: 20px; min-width: 20px"
                                    >
                                        ${sessionScope.sum}
                                    </span>
                                </a>
                                <div className="dropdown my-auto">
                                    <a
                                        href="#"
                                        className="dropdown"
                                        role="button"
                                        id="dropdownMenuLink"
                                        data-bs-toggle="dropdown"
                                        aria-expanded="false"
                                        // data-bs-toggle="dropdown"
                                        aria-expanded="false"
                                    >
                                        <i className="fas fa-user fa-2x"></i>
                                    </a>
                                    <ul
                                        className="dropdown-menu dropdown-menu-end p-4"
                                        aria-labelledby="dropdownMenuLink"
                                    >
                                        <li
                                            className="d-flex align-items-center flex-column"
                                            style="min-width: 300px"
                                        >
                                            <img
                                                style="width: 150px; height: 150px; border-radius: 50%; overflow: hidden"
                                                src="/images/avatar/${sessionScope.avatar}"
                                            />
                                            <div className="text-center my-3">
                                                <c:out value="${pageContext.request.userPrincipal.name}" />
                                            </div>
                                        </li>
                                        <li>
                                            <a className="dropdown-item" href="#">
                                                Quản lý tài khoản
                                            </a>
                                        </li>
                                        <li>
                                            <a
                                                className="dropdown-item"
                                                href="/order-history"
                                            >
                                                Lịch sử mua hàng
                                            </a>
                                        </li>
                                        <li>
                                            <hr className="dropdown-divider" />
                                        </li>
                                        <li>
                                            <form method="post" action="/logout">
                                                <button className="dropdown-item">
                                                    Đăng xuất
                                                </button>
                                                <input
                                                    type="hidden"
                                                    name="${_csrf.parameterName}"
                                                    value="${_csrf.token}"
                                                />
                                            </form>
                                        </li>
                                    </ul>
                                </div>
                            </c:if> */}

                            {isLogin && (
                                <Box>
                                    <span className="my-auto mx-3">Lê Nam</span>

                                    <a
                                        href="/cart"
                                        className="position-relative me-4 my-auto"
                                    >
                                        <i className="fa fa-shopping-bag fa-2x"></i>
                                        <span
                                            className="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
                                            style="top: -5px; left: 15px; height: 20px; min-width: 20px"
                                        >
                                            5
                                        </span>
                                    </a>
                                    <div className="dropdown my-auto">
                                        <a
                                            href="#"
                                            className="dropdown"
                                            role="button"
                                            id="dropdownMenuLink"
                                            data-bs-toggle="dropdown"
                                            aria-expanded="false"
                                            // data-bs-toggle="dropdown"
                                            aria-expanded="false"
                                        >
                                            <i className="fas fa-user fa-2x"></i>
                                        </a>
                                        <ul
                                            className="dropdown-menu dropdown-menu-end p-4"
                                            aria-labelledby="dropdownMenuLink"
                                        >
                                            <li
                                                className="d-flex align-items-center flex-column"
                                                style="min-width: 300px"
                                            >
                                                <img
                                                    style="width: 150px; height: 150px; border-radius: 50%; overflow: hidden"
                                                    src="/images/avatar/${sessionScope.avatar}"
                                                />
                                                <div className="text-center my-3">
                                                    <c:out value="${pageContext.request.userPrincipal.name}" />
                                                </div>
                                            </li>
                                            <li>
                                                <a className="dropdown-item" href="#">
                                                    Quản lý tài khoản
                                                </a>
                                            </li>
                                            <li>
                                                <a
                                                    className="dropdown-item"
                                                    href="/order-history"
                                                >
                                                    Lịch sử mua hàng
                                                </a>
                                            </li>
                                            <li>
                                                <hr className="dropdown-divider" />
                                            </li>
                                            <li>
                                                <form method="post" action="/logout">
                                                    <button className="dropdown-item">
                                                        Đăng xuất
                                                    </button>
                                                    <input
                                                        type="hidden"
                                                        name="${_csrf.parameterName}"
                                                        value="${_csrf.token}"
                                                    />
                                                </form>
                                            </li>
                                        </ul>
                                    </div>
                                </Box>
                            )}

                            <c:if test="${empty pageContext.request.userPrincipal}">
                                <a href="/login" className="position-relative my-auto">
                                    {' '}
                                    Login{' '}
                                </a>
                            </c:if>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
    );
}

export default HeaderUserPage;
