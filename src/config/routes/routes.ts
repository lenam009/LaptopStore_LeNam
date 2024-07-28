const routes = {
    home: { user: { path: '/' }, admin: { path: '/admin' } },
    profile: {
        prefix: '/profile',
        params: ':/username',
        path: '/profile/:username',
    },
    editUser: { path: '/profile/edit' },

    login: { path: '/login' },
    register: { path: '/register' },
    users: { path: '/users' },
};

export default routes;
