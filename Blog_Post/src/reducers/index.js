import { combineReducers } from 'redux';
import { allPosts, postInformations, userInformations, commsInformations } from './reducer_post';

const rootReducer = combineReducers({
  allPosts,
  postInformations,
  userInformations,
  commsInformations
});

export default rootReducer;