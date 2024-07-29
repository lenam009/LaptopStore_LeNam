import NextAuth, { DefaultSession } from 'next-auth';

declare module 'next-auth' {
    /**
     * Returned by `useSession`, `getSession` and received as a prop on the `SessionProvider` React Context
     */

    interface IUserCurrent {
        id: string;
        email: string;
        fullName: string;
        role: IRole;
    }

    interface User {
        access_token: string;
        user: IUser;
    }

    interface Session {
        error?: string;
        access_token: string;
        user: IUserCurrent;

        access_expire: number;
        expires: string;
    }
}

declare module 'next-auth/jwt' {
    /** Returned by the `jwt` callback and `getToken`, when using JWT sessions */

    interface IUserCurrent {
        id: string;
        email: string;
        fullName: string;
        role: IRole;
    }

    interface JWT {
        access_token: string;
        user: IUserCurrent;

        access_expire: number;
    }
}
