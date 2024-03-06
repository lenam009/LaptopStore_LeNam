import { Outlet } from 'react-router-dom';
import Box from '@mui/material/Box';

export default function Layout() {
    return (
        <Box>
            <header style={{ backgroundColor: 'red' }}>
                <h1>Header</h1>
            </header>
            <Outlet />
        </Box>
    );
}
