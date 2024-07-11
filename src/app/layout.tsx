import ThemeRegistry from '@/components/ThemeRegistry/ThemeRegistry';
import StyledComponentsRegistry from '@/lib/AntdRegistry';
import NextAuthSessionWrapper from '@/lib/next.auth.wrapper';
import NProgressWrapper from '@/lib/nprogress.wrapper';
import './global.scss';
import '../../public/css/styles.css';
import ReduxWrapper from '@/utils/redux/redux.wrapper';
import ReduxFetchApi from '@/utils/redux/WrapperReducerRedux/redux.fetchApi';
import 'bootstrap/dist/css/bootstrap.css';

export const metadata = {
    title: 'Facebook Clone',
    description: 'Facebook Clone',
};

export default function RootLayout({ children }: { children: React.ReactNode }) {
    return (
        <html lang="en">
            <head>
                <script
                    src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
                    crossOrigin="anonymous"
                ></script>
                <script
                    src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
                    integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
                    crossOrigin="anonymous"
                    referrerPolicy="no-referrer"
                ></script>
            </head>

            <body>
                <StyledComponentsRegistry>
                    <ThemeRegistry>
                        <NProgressWrapper>
                            <NextAuthSessionWrapper>
                                <ReduxWrapper>
                                    <ReduxFetchApi>{children}</ReduxFetchApi>
                                </ReduxWrapper>
                            </NextAuthSessionWrapper>
                        </NProgressWrapper>
                    </ThemeRegistry>
                </StyledComponentsRegistry>

                <script
                    src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossOrigin="anonymous"
                ></script>
                <script src="../../public/js/scripts.js"></script>
            </body>
        </html>
    );
}
