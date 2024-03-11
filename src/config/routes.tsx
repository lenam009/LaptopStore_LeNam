interface routes {
    [key: string]: {
        [key: string]: string;
    };
}

const routes = {
    home: {
        path: '/',
    },
    login: {
        path: '/login',
    },
    register: {
        path: '/register',
    },
    profile: {
        prefix: '/profile',
        params: ':/username',
        path: '/profile/:username',
    },
    user: {
        path: '/admin/user',
    },
    createUser: {
        path: '/admin/user/create',
    },
    product: {
        path: '/admin/product',
    },
    order: {
        path: '/admin/order',
    },
};

export default routes;
