import React from 'react';
import NavBar from '../common/NavBar';
import { Switch, Route, Redirect } from 'react-router-dom';
import Home from './Home';
import Post from './Post';
import User from './User';

class AuthorizedLayout extends React.Component {


    render() {

        return (
            <React.Fragment>
                <NavBar />
                <Switch>
                    <Route path={`${this.props.match.path}/home`} component={Home} />
                    <Route path={`${this.props.match.path}/post/:id`} component={Post} />
                    <Route path={`${this.props.match.path}/users/:id`} component={User} />
                    <Redirect to={`${this.props.match.path}/home`} />
                </Switch>
            </React.Fragment>
        )
    }

}

export default AuthorizedLayout;