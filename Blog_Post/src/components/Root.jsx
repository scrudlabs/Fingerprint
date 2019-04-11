import React from 'react';
import { MuiThemeProvider } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import { Switch, Route, Redirect } from 'react-router-dom';
import theme from '../_helpers/theme';
import AuthorizedLayout from './authorized/AuthorizedLayout';


class Root extends React.Component {

    render() {
        return (
            <MuiThemeProvider theme={theme}>
                <CssBaseline />
                <Switch>
                    <Route path="/" component={AuthorizedLayout} />
                    <Redirect to="/home" />
                </Switch>
            </MuiThemeProvider>
        )
    }
}

export default Root;