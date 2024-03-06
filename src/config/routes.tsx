interface routes {
    [key: string]: {
        [key: string]: string;
    };
}

const routes: routes = {
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
};

export default routes;
