import * as actionTypes from './actionTypes';
import { sortList } from '../../shared/utility';
import axios from 'axios'



export const getDragonList = () => {
    return {
        type: actionTypes.GET_DRAGONS_LIST,
    }
}

export const setDragonsList = (list) => {
    return {
        type: actionTypes.GET_DRAGONS_LIST_SUCCESS,
        dragonsList: list
    }
}

export const getDragonsListFail = (error) => {
    return {
        type: actionTypes.GET_DRAGONS_LIST_FAIL,
        error: error
    }
}

export const sortDragonsList = (list) => {
    return {
        type: actionTypes.SORT_DRAGON_LIST,
        sortedDragonsList: sortList(list)
    }
}

export const getDragonsList = () => {
    return dispatch => 
        axios.get('http://5c4b2a47aa8ee500142b4887.mockapi.io/api/v1/dragon')
            .then(res => {
                dispatch(getDragonList());
                dispatch(sortDragonsList(res.data))
                dispatch(setDragonsList(res.data));
                
            })
            .catch(error => {
                dispatch(getDragonsListFail("An error has occurred"))
            })
    
}