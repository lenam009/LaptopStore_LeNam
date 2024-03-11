import ReactDOM from 'react-dom/client';
import { Provider } from 'react-redux';
import { PersistGate } from 'redux-persist/integration/react';

import 'components/GlobalStyle/GlobalStyle.module.scss';
import '@/assets/css/styles.css';
import store from './redux/store';
import { persistor } from './redux/store';

import RegisterPage from './pages/register';
import LoginPage from './pages/login';
import HomePage from './pages/home';
import Layout from './layout/layout';
import ProductPage from './pages/admin/product/admin.product';
import UserPage from './pages/admin/user/admin.user';
import OrderPage from './pages/admin/order/admin.order';
import CreateUserPage from './pages/admin/user/admin.user.create';

import {
    RouterProvider,
    createBrowserRouter,
    createRoutesFromElements,
    Route,
    redirect,
} from 'react-router-dom';
import routes from './config/routes';
import { defer } from 'react-router-dom';

const router = createBrowserRouter(
    createRoutesFromElements(
        <Route>
            <Route path={routes.login.path} element={<LoginPage />} />
            <Route path={routes.register.path} element={<RegisterPage />} />

            <Route element={<Layout />}>
                <Route
                    path={routes.home.path}
                    element={<HomePage />}
                    // path={'/:slug'}
                    // loader={async ({}) => {
                    //     // loaders can be async functions
                    //     const res = fetch('http://localhost:8088/api/user/getall').then(
                    //         (res) => res.json(),
                    //     );
                    //     try {
                    //         // const user = res.json();
                    //         return defer({ res });
                    //     } catch (error) {
                    //         throw redirect(routes.login);
                    //     }
                    // }}
                />

                <Route path={routes.user.path} element={<UserPage />} />
                <Route path={routes.createUser.path} element={<CreateUserPage />} />
                <Route path={routes.product.path} element={<ProductPage />} />
                <Route path={routes.order.path} element={<OrderPage />} />
            </Route>
        </Route>,
    ),
);

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);
root.render(
    <Provider store={store}>
        <PersistGate loading={null} persistor={persistor}>
            {/* <App /> */}
            <RouterProvider router={router} />
        </PersistGate>
    </Provider>,
);
