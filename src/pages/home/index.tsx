import React from 'react';
import { useMatch } from 'react-router-dom';
import routes from '@/config/routes';

export default function HomePage() {
    const match = useMatch(routes.home.path);

    console.log('match', match);

    return (
        <div>
            <h1>Home Page</h1>
        </div>
    );
}
