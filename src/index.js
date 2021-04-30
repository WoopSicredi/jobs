import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Switch, Route, Redirect } from "react-router-dom";
import './global.scss';

import Login from './views/Login/';
import Dragons from './views/Dragons/';
import EditDragon from './views/EditDragon/';

const loggedIn = localStorage.getItem('loggedIn');

ReactDOM.render(
  <React.StrictMode>
    <Router>
      <Switch>
        <Route exact path="/">
          { loggedIn ? <Redirect to="dragons" /> : <Login /> }
        </Route>
        <Route exact path="/dragons">
        { loggedIn ? <Dragons /> : <Redirect to="/" />}
        </Route>
        <Route exact path="/dragons/edit/:dragonId">
        { loggedIn ? <EditDragon /> : <Redirect to="/" />}
        </Route>
      </Switch>
    </Router>
  </React.StrictMode>,
  document.getElementById('root')
);
