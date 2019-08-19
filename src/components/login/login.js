import React, { useState, useEffect } from "react";
import { connect } from "react-redux";
import * as actions from "../../store/actions/";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faDragon } from "@fortawesome/free-solid-svg-icons";
import "./login.css";


const Login = props => {
  const credentials = { username: "", password: "" };

  const [formData, setFormData] = useState(credentials);
  const [isSignUp, setIsSignUp] = useState(false);

  useEffect(()=> {
    if(props.isLoggedIn) {
      props.history.push('/list');
   }
  }, [props.isLoggedIn]);

  
  const changeHandler = event => {
    const { id, value } = event.target;
    setFormData({ ...formData, [id]: value });
  };

  const submitHandler = event => {
    props.onLogin(formData);
    event.preventDefault();
  };

  const signUpHandler = () => {
    setIsSignUp(true);
  }


  const renderButton = () => {
    if(!isSignUp) {
      return (
        <div className="loginButtons">
          <button type="submit">Sign in</button>
          <button type="button" onClick={signUpHandler}>Sign up</button>
        </div>
      )
    }else {
      return (
        <div className="loginButtons">
          <button type="submit">Sign up</button>
        </div>
      )
    }
  }

  const button = renderButton();

  return (
    <div>
      <FontAwesomeIcon icon={faDragon} className="Logo" size="7x" />
      <form onSubmit={submitHandler} className="Login">
        <input
          placeholder="Usuário"
          id="username"
          type="text"
          onChange={changeHandler}
          value={formData.username}
        />
        <input
          placeholder="Senha"
          id="password"
          type="password"
          onChange={changeHandler}
          value={formData.password}
        />
        {button}
      </form>
      <div>
        {props.error && <p>{props.error}</p>}
      </div>
    </div>
  );
};


const mapStateToProps = state => {
  return {
    isLoggedIn: state.auth.isLoggedIn,
    error: state.auth.error
  }
}


const mapDispatchToProps = dispatch => {
  return {
    onLogin: (formData) => dispatch(actions.login(formData))
  }
}
export default connect(mapStateToProps,mapDispatchToProps)(Login);
