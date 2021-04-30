import React from 'react';
import { Link } from 'react-router-dom';

function Logout() {

    const logout = () => {
        localStorage.removeItem('loggedIn');
        window.location.reload();
    }

    return (
        <Link to='logout' onClick={() => logout()}>Sair</Link>
    )
}

export default Logout;