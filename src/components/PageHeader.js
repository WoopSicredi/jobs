import React from 'react';
import Logout from './Logout';

import './components.scss'

function PageHeader(params) {
    const { title } = params;
    return (
        <div className="pageHeader">
            <h2>{ title }</h2>
            <Logout />
        </div>
    )
}

export default PageHeader;