import Box from '@mui/material/Box';
import Link from 'next/link';

function HeaderUserPage() {
    const isLogin = true;

    return (
        <div
            className="container-fluid fixed-top"
            style={{ backgroundColor: 'rgba(22,24,35,0.05)' }}
        >
            <div className="container px-0">
                <nav className="navbar navbar-light  navbar-expand-xl">
                    <a href="/" className="navbar-brand">
                        <h1 className="text-primary display-6">Lê Nam</h1>
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
                        className="collapse navbar-collapse  justify-content-between ms-5"
                        id="navbarCollapse"
                    >
                        <div className="navbar-nav">
                            <Link href="/" className="nav-item nav-link active">
                                Home
                            </Link>
                            {/* <Link href="/product" className="nav-item nav-link">
                                Product
                            </Link> */}
                        </div>

                        <div className="d-flex m-3 me-0">
                            {isLogin && (
                                <div className="d-flex m-3 me-0">
                                    <span className="my-auto mx-3">Lê Nam</span>

                                    <Link
                                        href="/cart"
                                        className="position-relative me-4 my-auto"
                                    >
                                        <i className="fa fa-shopping-bag fa-2x"></i>
                                        <span
                                            className="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
                                            // style="top: -5px; left: 15px; height: 20px; min-width: 20px"
                                            style={{
                                                top: '-5px',
                                                left: '15px',
                                                height: '20px',
                                                minWidth: '20px',
                                            }}
                                        >
                                            5
                                        </span>
                                    </Link>
                                    <div className="dropdown my-auto">
                                        <a
                                            href="#"
                                            className="dropdown"
                                            role="button"
                                            id="dropdownMenuLink"
                                            data-bs-toggle="dropdown"
                                            aria-expanded="false"
                                            // data-bs-toggle="dropdown"
                                            // aria-expanded="false"
                                        >
                                            <i className="fas fa-user fa-2x"></i>
                                        </a>
                                        <ul
                                            className="dropdown-menu dropdown-menu-end p-4"
                                            aria-labelledby="dropdownMenuLink"
                                        >
                                            <li
                                                className="d-flex align-items-center flex-column"
                                                style={{ minWidth: '300px' }}
                                            >
                                                <img
                                                    // style="width: 150px; height: 150px; border-radius: 50%; overflow: hidden"
                                                    style={{
                                                        width: '150px',
                                                        height: '150px',
                                                        borderRadius: '50%',
                                                        overflow: 'hidden',
                                                    }}
                                                    src="/images/avatar/${sessionScope.avatar}"
                                                />
                                                <div className="text-center my-3">
                                                    {/* <c:out value="${pageContext.request.userPrincipal.name}" /> */}
                                                    Lê Nam
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
                                </div>
                            )}

                            {/* <c:if test="${empty pageContext.request.userPrincipal}">
                                <a href="/login" className="position-relative my-auto">
                                    {' '}
                                    Login{' '}
                                </a>
                            </c:if> */}

                            {!isLogin && (
                                <a href="/login" className="position-relative my-auto">
                                    {' '}
                                    Login{' '}
                                </a>
                            )}
                        </div>
                    </div>
                </nav>
            </div>
        </div>
    );
}

export default HeaderUserPage;
