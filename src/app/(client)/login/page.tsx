import React from 'react';
import AuthSignin from '@/components/auth/auth.signin';
import Box from '@mui/material/Box';
import { redirect } from 'next/navigation';
import { getServerSession } from 'next-auth/next';
import { authOptions } from '@/app/api/auth/[...nextauth]/route';
import routes from '@/config/routes/routes';

export default async function LoginPage() {
    const session = await getServerSession(authOptions);

    if (session && session.user.role.id === 'ADMIN') {
        redirect(routes.home.admin.path);
    } else if (session && session.user.role.id === 'USER') {
        redirect(routes.home.user.path);
    }

    return <AuthSignin />;
}
