import React from 'react';
import { Switch, Route, Redirect, useLocation } from 'react-router-dom';
import { FirebaseAuthConsumer } from '@react-firebase/auth';

import {
  DragonList,
  DragonDetail,
  CreateOrEditDragon,
  ModalEditDragon,
} from 'scenes';

import { Navbar } from 'components';

import './Home.scss';

export const Home = () => {
  const _location = useLocation();
  let _background = _location.state && _location.state.background;

  const _onSignOutCliked = async (firebase) => {
    await firebase.app().auth().signOut();
  };

  const _isFirebaseObjectLoaded = (firebase) => {
    return Object.keys(firebase).length !== 0;
  };

  return (
    <React.Fragment>
      <FirebaseAuthConsumer>
        {({ isSignedIn, firebase }) => {
          if (!isSignedIn) {
            return _isFirebaseObjectLoaded(firebase) ? (
              <Redirect to='/login' />
            ) : null;
          } else {
            return (
              <div className='home'>
                <Navbar
                  onSignOutClicked={() => {
                    _onSignOutCliked(firebase);
                  }}
                />
                <div className='home__content'>
                  <Switch location={_background || _location}>
                    <Route path='/' exact component={DragonList} />
                    <Route
                      path='/detalhes/:id'
                      exact
                      component={DragonDetail}
                    />
                    <Route
                      path='/editar/:id'
                      exact
                      component={CreateOrEditDragon}
                    />
                    <Route path='/criar' exact component={CreateOrEditDragon} />
                    {/* <Redirect to='/' /> */}
                  </Switch>
                  {_background && (
                    <Route
                      path='/editar/:id'
                      exact
                      component={ModalEditDragon}
                    />
                  )}
                </div>
              </div>
            );
          }
        }}
      </FirebaseAuthConsumer>
    </React.Fragment>
  );
};
