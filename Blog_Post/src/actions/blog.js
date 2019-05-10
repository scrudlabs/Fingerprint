import axios from 'axios';
import { REQUEST, SUCCESS, FAILURE } from './index';


//Action types
export const ACTION_TYPES = {
    GET_ALL_POSTS: 'home/GET_ALL_POSTS',
    GET_POST_INFOS: 'home/GET_POST_INFOS',
    GET_USER_INFOS: 'home/GET_USER_INFOS',
    GET_ALL_COMMS: 'home/GET_ALL_COMMS',
};

/**
 * requestGetAllPosts
 */
const requestGetAllPosts = () => ({ type: REQUEST(ACTION_TYPES.GET_ALL_POSTS) });


/**
 * getAllPostsFailure
 * 
 * @param {*} message 
 */
const getAllPostsFailure = (message) => ({ type: FAILURE(ACTION_TYPES.GET_ALL_POSTS), message });

/**
 * getAllPostsSuccess
 */
const getAllPostsSuccess = (payload) => ({ type: SUCCESS(ACTION_TYPES.GET_ALL_POSTS), payload });


/**
 * get all posts
 * 
 */
export const getAllPosts = () => async dispatch => {
    //request
    dispatch(requestGetAllPosts());

    try {

        const response = await axios.get(`${process.env.REACT_APP_API_URL}/posts`, {});

        if (response.status === 200) {
            console.log('SUCCESS getAllPosts');
            dispatch(getAllPostsSuccess(response));
        }
    }
    catch (error) {
        console.log('FAILURE getAllPosts');
        dispatch(getAllPostsFailure(`Erreur lors de la récupération de tous les posts`));
    }
}

/**
 * requestGetPostInfos
 */
const requestGetPostInfos = () => ({ type: REQUEST(ACTION_TYPES.GET_POST_INFOS) });


/**
 * getPostInfosFailure
 * 
 * @param {*} message 
 */
const getPostInfosFailure = (message) => ({ type: FAILURE(ACTION_TYPES.GET_POST_INFOS), message });

/**
 * getPostInfosSuccess
 */
const getPostInfosSuccess = (payload) => ({ type: SUCCESS(ACTION_TYPES.GET_POST_INFOS), payload });

/**
* get post info by id
* 
*/
export const getPostInfos = (id) => async dispatch => {
    //request
    dispatch(requestGetPostInfos());

    try {
        const response = await axios.get(`${process.env.REACT_APP_API_URL}/posts/${id}`, {});

        if (response.status === 200) {
            console.log('SUCCESS getPostInfos', response.data);
            dispatch(getPostInfosSuccess(response));
        }
    }
    catch (error) {
        console.log('FAILURE getPostInfos');
        dispatch(getPostInfosFailure(`Erreur lors de la récupération de informations du post`));
    }
}

/**
 * requestGetUserInfos
 */
const requestGetUserInfos = () => ({ type: REQUEST(ACTION_TYPES.GET_USER_INFOS) });


/**
 * getUserInfosFailure
 * 
 * @param {*} message 
 */
const getUserInfosFailure = (message) => ({ type: FAILURE(ACTION_TYPES.GET_USER_INFOS), message });

/**
 * getUserInfosSuccess
 */
const getUserInfosSuccess = (payload) => ({ type: SUCCESS(ACTION_TYPES.GET_USER_INFOS), payload });

/**
* get user info by id
* 
*/
export const getUserInfos = (id) => async dispatch => {
    //request
    dispatch(requestGetUserInfos());

    try {
        const response = await axios.get(`${process.env.REACT_APP_API_URL}/users/${id}`, {});

        if (response.status === 200) {
            console.log('SUCCESS getUserInfos', response.data);
            dispatch(getUserInfosSuccess(response));
        }
    }
    catch (error) {
        console.log('FAILURE getUserInfos');
        dispatch(getUserInfosFailure(`Erreur lors de la récupération de informations de l'utilisateur`));
    }
}



/**
 * requestGetAllComms
 */
const requestGetAllComms = () => ({ type: REQUEST(ACTION_TYPES.GET_ALL_COMMS) });


/**
 * getAllCommsFailure
 * 
 * @param {*} message 
 */
const getAllCommsFailure = (message) => ({ type: FAILURE(ACTION_TYPES.GET_ALL_COMMS), message });

/**
 * getAllCommsSuccess
 */
const getAllCommsSuccess = (payload) => ({ type: SUCCESS(ACTION_TYPES.GET_ALL_COMMS), payload });


/**
 * get all Comms
 * 
 */
export const getAllComms = (postId) => async dispatch => {
    //request
    dispatch(requestGetAllComms());

    try {

        const response = await axios.get(`${process.env.REACT_APP_API_URL}/comments/?postId=${postId}`, {});

        if (response.status === 200) {
            console.log('SUCCESS getAllComms');
            dispatch(getAllCommsSuccess(response));
        }
    }
    catch (error) {
        console.log('FAILURE getAllComms');
        dispatch(getAllCommsFailure(`Erreur lors de la récupération de tous les commentaires`));
    }
}