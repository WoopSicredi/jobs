import * as actionTypes from "./actionTypes";
import * as actions from "./index";

export const initLogin = () => {
  return {
    type: actionTypes.LOGIN_INIT
  }
};

export const loginFail = () => {
  return {
    type: actionTypes.LOGIN_FAIL,
    error: "Invalid username/password"
  }
}

export const login = (credentials) => {
  return dispatch => {
    if(credentials.username.length > 4 && credentials.password.length > 4) {
      dispatch(initLogin());
      dispatch(actions.getDragonsList());
    } else {
      dispatch(loginFail());
    }
  }
}




