import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

import logo from 'assets/icons/logo-medium.svg';

import './Navbar.scss';

export const Navbar = ({ onSignOutClicked }) => {
  return (
    <nav className='navbar'>
      <Link className='navbar__logo' to='/'>
        <img className='navbar__logo__img' src={logo} alt='Dracarys' />
      </Link>
      <div className='navbar__actions'>
        <ul>
          <li>
            <Link to='/criar'>Criar dragão</Link>
          </li>
          <li>
            <button
              onClick={() => {
                onSignOutClicked();
              }}>
              Sair
            </button>
          </li>
        </ul>
      </div>
    </nav>
  );
};

Navbar.propTypes = {
  onSignOutClicked: PropTypes.func.isRequired,
};
