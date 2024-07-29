const admin = '/admin';
const user = '/user';

const routes = {
    home: { user: { path: '/user' }, admin: { path: admin } },

    admin: {
        product: { path: admin + '/product' },
        order: { path: admin + '/order' },
        user: { path: admin + '/user' },
    },

    login: { path: '/login' },
    register: { path: '/register' },
};

export default routes;
