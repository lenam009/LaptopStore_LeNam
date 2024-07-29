import React from 'react';

import { getServerSession } from 'next-auth/next';
import { authOptions } from '@/app/api/auth/[...nextauth]/route';
import { Button, Result } from 'antd';

interface IProps {
    authorization: string;
    children: React.ReactNode;
}

export default async function Authorization({ authorization, children }: IProps) {
    const ACT_ENABLE = true;
    if (!ACT_ENABLE) return <>{children}</>;

    const session = await getServerSession(authOptions);

    const isAdminSession = session && session?.user.role.id;

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
