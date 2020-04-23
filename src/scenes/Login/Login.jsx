import React, { useState } from 'react';
import { Redirect } from 'react-router-dom';
import { FirebaseAuthConsumer } from '@react-firebase/auth';

import { Button, Loader } from 'components';

import logo from 'assets/icons/logo-big.svg';

import './Login.scss';

export const Login = () => {
  const [_showLoader, _setShowLoader] = useState(false);

  const _signInAnonymously = async (firebase) => {
    _setShowLoader(true);
    await firebase.app().auth().signInAnonymously();
    _setShowLoader(false);
  };

  return (
    <React.Fragment>
      <FirebaseAuthConsumer>
        {({ isSignedIn, firebase }) => {
          if (isSignedIn) {
            return <Redirect to='/' />;
          } else {
            return (
              <div className='login'>
                {_showLoader ? <Loader /> : null}
                <img className='logo' src={logo} alt='Dracarys' />
                <div className='btn-sign-in'>
                  <Button
                    primary={true}
                    type='button'
                    size='huge'
                    text='Entrar anonimamente'
                    onClick={() => {
                      _signInAnonymously(firebase);
                    }}
                  />
                </div>
              </div>
            );
          }
        }}
      </FirebaseAuthConsumer>
    </React.Fragment>
  );
};
