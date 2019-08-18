import React from "react";
import { Route, Switch, withRouter, Redirect } from "react-router-dom";
import { connect } from "react-redux";
import Login from "./components/login/login";
import DragonList from "./components/dragons-list/dragonsList";
import Dragon from "./components/dragon/dragon";
import "./App.css";

const App = props => {
  let routes = (
    <Switch>
      <Route path="/" exact component={Login} />
      <Redirect to="/" />
    </Switch>
  );

  if (props.isLoggedIn) {
    routes = (
      <Switch>
        <Route path="/" exact component={Login} />
        <Route path="/list" exact component={DragonList} />
        <Route path="/dragon/:id" render={props => <Dragon {...props} />} />
        <Redirect to="/" />
      </Switch>
    );
  }

  return (
    <div className="App">
      <header className="App-header">{routes}</header>
    </div>
  );
};

const mapStateToProps = state => {
  return {
    isLoggedIn: state.auth.isLoggedIn
  };
};



export default withRouter(
  connect(
    mapStateToProps,
    null
  )(App)
);
