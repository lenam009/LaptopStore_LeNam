'use client';
import React from 'react';

import { AppProgressBar as ProgressBar } from 'next-nprogress-bar';

const NProgressWrapper = ({ children }: { children: React.ReactNode }) => {
    return (
        <>
            {children}
            <ProgressBar
                height="3px"
                color="blue"
                options={{ showSpinner: false, parent: '#layoutSidenav_content' }}
                shallowRouting
            />
        </>
    );
};

export default NProgressWrapper;
