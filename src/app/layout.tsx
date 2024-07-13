import ThemeRegistry from '@/components/ThemeRegistry/ThemeRegistry';
import StyledComponentsRegistry from '@/lib/AntdRegistry';
import NextAuthSessionWrapper from '@/lib/next.auth.wrapper';
import NProgressWrapper from '@/lib/nprogress.wrapper';
import './global.scss';
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
            <head></head>

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

                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
                <script src="../../client/lib/easing/easing.min.js"></script>
                <script src="../../client/lib/waypoints/waypoints.min.js"></script>
                <script src="../../client/lib/lightbox/js/lightbox.min.js"></script>
                <script src="../../client/lib/owlcarousel/owl.carousel.min.js"></script>

                <script
                    src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossOrigin="anonymous"
                ></script>
                <script src="../../js/scripts.js"></script>
            </body>
        </html>
    );
}
