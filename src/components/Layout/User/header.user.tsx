'use client';

import Box from '@mui/material/Box';
import Link from 'next/link';
import { signOut } from 'next-auth/react';

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

                                    <div className="position-relative me-4 my-auto">
                                        <button
                                            className="btn btn-danger"
                                            onClick={() => signOut()}
                                        >
                                            Logout
                                        </button>
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
