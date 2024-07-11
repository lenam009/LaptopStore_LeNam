import React from 'react';
import ProfileUser from '@/components/Profile/profile.user';
import Box from '@mui/material/Box';
import { getServerSession } from 'next-auth/next';
import { authOptions } from '@/app/api/auth/[...nextauth]/route';
import {
    handleGetUserRandomAction,
    handleGetOneUserById,
    handleGetPostsByUserId,
    handleGetUserByFollowing,
} from '@/utils/actions/actions';

export default async function ProfilePage({ params }: { params: { slug: string } }) {
    const { slug } = params;
    const session = await getServerSession(authOptions);

    const words = slug.split('.html')[0];
    const wordId = words.split('-');
    const userId = wordId[wordId.length - 1];

    return <Box>slug : {slug}</Box>;
}
