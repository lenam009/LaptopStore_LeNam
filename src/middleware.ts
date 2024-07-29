// import { NextResponse } from 'next/server';
// import { NextRequest } from 'next/server';

// import { getServerSession } from 'next-auth/next';
// import { authOptions } from '@/app/api/auth/[...nextauth]/route';
// // This function can be marked `async` if using `await` inside
// Note: cannot use session auth.......
// export async function middleware(request: NextRequest) {
//     // if (request.nextUrl.pathname.startsWith('/home')) {
//     //     return NextResponse.redirect(new URL('/', request.url));
//     // }

//     // const token = await getToken({ req });
//     // const isAuthenticated = !!token;

//     const session = await getServerSession(authOptions);

//     const isAdmin = true;
//     const isAuthentication = true;

//     if (!isAuthentication) {
//         if (request.nextUrl.pathname === '/' || request.nextUrl.pathname === '/admin') {
//             return NextResponse.redirect(new URL('/login', request.url));
//         }
//     } else {
//         if (isAdmin && ['/', '/login'].includes(request.nextUrl.pathname)) {
//             return NextResponse.redirect(new URL('/admin', request.url));
//         } else if (!isAdmin && ['/admin', '/login'].includes(request.nextUrl.pathname)) {
//             return NextResponse.redirect(new URL('/', request.url));
//         }
//     }

//     // //  @ts-expect-error
//     return NextResponse.next();
// }

//..........................................................................

import { withAuth } from 'next-auth/middleware';
import routes from './config/routes/routes';

export default withAuth({
    // Matches the pages config in `[...nextauth]`
    pages: {
        signIn: '/login',
    },
    // callbacks: {
    //     authorized({ req, token }) {
    //         // `/admin` requires admin role
    //         if (req.nextUrl.pathname.startsWith(routes.home.admin.path)) {
    //             return token?.user.role.id === 'ADMIN';
    //         }

    //         if (req.nextUrl.pathname.startsWith(routes.home.user.path)) {
    //             return token?.user.role.id === 'USER';
    //         }
    //         // `/me` only requires the user to be logged in
    //         return !!token;
    //     },
    // },
});

export const config = {
    matcher: [
        routes.home.user.path,
        routes.user.cart.path,
        routes.home.admin.path + '/:path*',
    ],
};
