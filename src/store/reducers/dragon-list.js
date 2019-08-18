import * as actionTypes from "../actions/actionTypes";
import {updateObject} from '../../shared/utility';

const initialState = {
  dragons: [],
  error: null,
  loading: false
};

const getDragonsList = (state, action) => {
  return updateObject(state,{
    dragons: action.dragonsList,
    loading: true
  })
};

const getDragonsListSuccess = (state, action) => {
  return updateObject(state,{
    dragons: action.dragonsList,
    loading: false
  })
};
const getDragonsListFail = (state, action) => {
  return updateObject(state,{
    error: action.error,
    loading: false
  })
};

const sortDragonList = (state, action) => {
  return updateObject(state, {
    dragons: action.sortedDragonsList  
  })
}

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.GET_DRAGONS_LIST:
      return getDragonsList(state, action);
    case actionTypes.GET_DRAGONS_LIST_FAIL:
      return getDragonsListFail(state, action);
    case actionTypes.GET_DRAGONS_LIST_SUCCESS:
      return getDragonsListSuccess(state, action);
    case actionTypes.SORT_DRAGON_LIST:
      return sortDragonList(state, action);
    default:
      return state;
  }
};

export default reducer;
