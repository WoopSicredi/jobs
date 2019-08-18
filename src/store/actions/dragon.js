import * as actionTypes from './actionTypes';
import axios from 'axios'


export const saveDragon = (dragonDetails) => {
    return dispatch => {
        const url = `http://5c4b2a47aa8ee500142b4887.mockapi.io/api/v1/dragon/${dragonDetails.id}`;
        axios.put(url, dragonDetails)
            .then(response =>{         
                dispatch(saveDragonSuccess(response));
            })
            .catch(error => {    
                dispatch(saveDragonFail("An error has occurred"));
            })
    }
}

export const saveDragonSuccess = success => {
    return {
        type: actionTypes.SAVE_DRAGON_SUCCESS,
        successMessage: success
    }
}

export const saveDragonFail = error => {
    return {
        type: actionTypes.SAVE_DRAGON_FAIL,
        error: error
    }
}

export const deleteDragon = dragonId => {
    return dispatch => {
        const url = `http://5c4b2a47aa8ee500142b4887.mockapi.io/api/v1/dragon/${dragonId}`;
        axios.delete(url)
            .then(response => {
                dispatch(deleteDragonSuccess(true));
            })
            .catch( error => {
                dispatch(deleteDragonFail("An error has occurred"))
            })
    }
}

export const deleteDragonSuccess = success => {
    return {
        type: actionTypes.DELETE_DRAGON_SUCCESS,
        wasDeleted: success
    }
}

export const deleteDragonFail = error => {
    return {
        type: actionTypes.DELETE_DRAGON_FAIL,
        error: error
    }
}


export const createDragon = (newDragon) => {
    return dispatch => {
        const url = `http://5c4b2a47aa8ee500142b4887.mockapi.io/api/v1/dragon/`;
        axios.post(url,newDragon)
            .then(response => {
                dispatch(createDragonSuccess(true));
            })
            .catch(error => {
                dispatch(createDragonFail("An error has occurred"));
            })

    }
}


export const createDragonSuccess = success => {
    return {
        type: actionTypes.CREATE_DRAGON_SUCCESS,
        wasCreated: success
    }
}

export const createDragonFail = error => {
    return {
        type: actionTypes.CREATE_DRAGON_FAIL,
        error: error
    }
}