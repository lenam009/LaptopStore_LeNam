const admin = '/admin';
const user = '/';

const routes = {
    home: { user: { path: user }, admin: { path: admin } },

    user: {
        cart: { path: '/cart' },
    },

    admin: {
        product: { path: admin + '/product' },
        order: { path: admin + '/order' },
        user: { path: admin + '/user' },
    },

    login: { path: '/login' },
    register: { path: '/register' },
};

export default routes;
