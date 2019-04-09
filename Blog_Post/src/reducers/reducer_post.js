import { REQUEST, SUCCESS, FAILURE} from '../actions/index';
import {ACTION_TYPES} from '../actions/blog';

const initialState = {
    posts: {},
    loading: false,
    errorMessage: null, // Errors returned from server side
};

/**
 * allPosts
 * @param {*} state 
 * @param {*} action 
 */
export const allPosts = (state = initialState, action) => {
    switch (action.type) {
        case REQUEST(ACTION_TYPES.GET_ALL_POSTS):{
            return {
                ...state,
                loading: true
            };
        }
        case SUCCESS(ACTION_TYPES.GET_ALL_POSTS): {
            return {
                ...state,
                loading: false,
                errorMessage: null,
                posts: action.payload.data
            };
        }
        case FAILURE(ACTION_TYPES.GET_ALL_POSTS): {
            return {
                ...initialState,
                errorMessage: action.message
            };
        }
        default:
            return state;
    }
}


const postInfos = {
    info: {},
    loading: false,
    errorMessage: null, // Errors returned from server side
};

/**
 * postInformations
 * @param {*} state 
 * @param {*} action 
 */
export const postInformations = (state = postInfos, action) => {
    switch (action.type) {
        case REQUEST(ACTION_TYPES.GET_POST_INFOS):{
            return {
                ...state,
                loading: true
            };
        }
        case SUCCESS(ACTION_TYPES.GET_POST_INFOS): {
            return {
                ...state,
                loading: false,
                errorMessage: null,
                info: action.payload.data
            };
        }
        case FAILURE(ACTION_TYPES.GET_POST_INFOS): {
            return {
                ...postInfos,
                errorMessage: action.message
            };
        }
        default:
            return state;
    }
}


const userInfos = {
    info: {},
    loading: false,
    errorMessage: null, // Errors returned from server side
};

/**
 * userInformations
 * @param {*} state 
 * @param {*} action 
 */
export const userInformations = (state = userInfos, action) => {
    switch (action.type) {
        case REQUEST(ACTION_TYPES.GET_USER_INFOS):{
            return {
                ...state,
                loading: true
            };
        }
        case SUCCESS(ACTION_TYPES.GET_USER_INFOS): {
            return {
                ...state,
                loading: false,
                errorMessage: null,
                info: action.payload.data
            };
        }
        case FAILURE(ACTION_TYPES.GET_USER_INFOS): {
            return {
                ...userInfos,
                errorMessage: action.message
            };
        }
        default:
            return state;
    }
}




const commsInfos = {
    info: {},
    loading: false,
    errorMessage: null, // Errors returned from server side
};

/**
 * userInformations
 * @param {*} state 
 * @param {*} action 
 */
export const commsInformations = (state = commsInfos, action) => {
    switch (action.type) {
        case REQUEST(ACTION_TYPES.GET_ALL_COMMS):{
            return {
                ...state,
                loading: true
            };
        }
        case SUCCESS(ACTION_TYPES.GET_ALL_COMMS): {
            return {
                ...state,
                loading: false,
                errorMessage: null,
                info: action.payload.data
            };
        }
        case FAILURE(ACTION_TYPES.GET_ALL_COMMS): {
            return {
                ...userInfos,
                errorMessage: action.message
            };
        }
        default:
            return state;
    }
}