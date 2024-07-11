import { Col, Row } from 'antd';
import LeftBar from '@/components/LeftBar/leftbar.home';
import Feed from '@/components/Feed/feed';
import RightBar from '@/components/Home/RightBar/rightbar.home';
import {
    handleGetUserRandomAction,
    handleGetUserByFollowing,
    handleGetPostsFollowing,
} from '@/utils/actions/actions';
import { getServerSession } from 'next-auth/next';
import { authOptions } from '@/app/api/auth/[...nextauth]/route';

export default async function HomePage() {
    return <Row>Profile</Row>;
}
