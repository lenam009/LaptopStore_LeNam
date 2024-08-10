import React from 'react';

import { getServerSession } from 'next-auth/next';
import { authOptions } from '@/app/api/auth/[...nextauth]/route';
import { Button, Result } from 'antd';
import { headers } from 'next/headers';
import routes from '@/config/routes/routes';
import { redirect } from 'next/navigation';

interface IProps {
    authorization: string;
    children: React.ReactNode;
}

export default async function Authorization({ authorization, children }: IProps) {
    const ACT_ENABLE = true;
    if (!ACT_ENABLE) return <>{children}</>;

    const session = await getServerSession(authOptions);

    const isAdminSession = session && session?.user.role.id;

    //currentURL active not reliable
    const heads = headers();
    const currentURl = heads.get('next-url');

    console.log('currentURl ', currentURl);

    if (isAdminSession === 'ADMIN' && currentURl === routes.home.user.path) {
        redirect(routes.home.admin.path);
    } else if (isAdminSession === 'USER' && currentURl === routes.home.admin.path) {
        redirect(routes.home.user.path);
    }

    if (authorization !== isAdminSession) {
        return (
            <div>
                <Result
                    status="403"
                    title="403"
                    subTitle="Sorry, you are not authorized to access this page."
                />
            </div>
        );
    }

    return <>{children}</>;
}
