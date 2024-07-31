'use client';

import Box from '@mui/material/Box';
import Link from 'next/link';
import { signOut } from 'next-auth/react';

import { useAppSelector } from '@/utils/redux/hook';
import { getCartSelector } from '@/utils/redux/slice/cartSlice';
import { useSession } from 'next-auth/react';
import routes from '@/config/routes/routes';

function HeaderUserPage() {
    const { data: session } = useSession();

    const isLogin = !!session?.user;

    const cartCurrent = useAppSelector(getCartSelector);

    return (
        <div
            className="container-fluid fixed-top"
            style={{ backgroundColor: 'rgba(22,24,35,0.05)' }}
        >
            <div className="container px-0">
                <nav className="navbar navbar-light  navbar-expand-xl">
                    <a href="/">
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
                            <span></span>
                        </div>

                        <div className="d-flex m-3 me-0">
                            {isLogin && (
                                <div className="d-flex m-3 me-0">
                                    <span className="my-auto mx-3">
                                        {session.user.fullName}
                                    </span>

                                    <a
                                        href={routes.user.cart.path}
                                        className="position-relative me-4 my-auto"
                                    >
                                        Cart ({cartCurrent.sum})
                                    </a>

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

                            {!isLogin && (
                                <a href="/login" className="position-relative my-auto">
                                    Login
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
