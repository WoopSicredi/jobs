import * as actionTypes from "../actions/actionTypes";
import { updateObject } from "../../shared/utility";

const initialState = {
  error: null,
  successMessage: null,
  wasCreated: false
};

const saveDragonSuccess = (state, action) => {
  return updateObject(state, { successMessage: action.successMessage, wasCreated: true });
};

const saveDragonFail = (state, action) => {
  return updateObject(state, { error: action.error, wasCreated: false });
};

const deleteDragonSuccess = (state, action) => {
  return updateObject(state, action);
};
const deleteDragonInit = (state, action) => {
  return updateObject(state, action);

}
const deleteDragonFail = (state, action) => {
  return updateObject(state, { error: action.error });
};

const createDragonFail = (state, action) => {
    return updateObject(state, { error: action.error, wasCreated: false });
};

const createDragonSuccess = (state,action) => {
    return updateObject(state, {wasCreated: action.wasCreated})
}
const reducer = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.SAVE_DRAGON_SUCCESS:
      return saveDragonSuccess(state, action);
    case actionTypes.SAVE_DRAGON_FAIL:
      return saveDragonFail(state, action);
    case actionTypes.DELETE_DRAGON_INIT:
      return deleteDragonInit(state,action);
    case actionTypes.DELETE_DRAGON_SUCCESS:
      return deleteDragonSuccess(state, action);
    case actionTypes.DELETE_DRAGON_FAIL:
      return deleteDragonFail(state, action);
    case actionTypes.CREATE_DRAGON_SUCCESS:
      return createDragonSuccess(state, action);
    case actionTypes.CREATE_DRAGON_FAIL:
      return createDragonFail(state, action);
    default:
      return state;
  }
};

export default reducer;
