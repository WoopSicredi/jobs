import React, { useState } from 'react';
import { BrowserRouter, Route, Switch, Redirect } from 'react-router-dom';
import axios from 'axios';

import { FirebaseAuthProvider } from '@react-firebase/auth';
import * as firebase from 'firebase/app';
import 'firebase/auth';
import { config } from './firebaseConfig';

import { Login, Home } from 'scenes';
import { Loader } from 'components';

import './App.scss';

const App = () => {
  const [_showLoader, _setShowLoader] = useState(false);

  function _configureRequestInterceptor() {
    axios.interceptors.request.use((config) => {
      _setShowLoader(true);
      return config;
    });

    axios.interceptors.response.use(
      (response) => {
        _setShowLoader(false);
        return response;
      },
      (error) => {
        _setShowLoader(false);
        return Promise.reject(error);
      }
    );
  }

  return (
    <div className='app'>
      {_configureRequestInterceptor()}
      {_showLoader ? <Loader /> : null}
      <FirebaseAuthProvider {...config} firebase={firebase}>
        <BrowserRouter>
          <Switch>
            <Route exact path='/login' component={Login} />
            <Route path='/' component={Home} />
            <Redirect to='/' />
          </Switch>
        </BrowserRouter>
      </FirebaseAuthProvider>
    </div>
  );
};

export default App;
