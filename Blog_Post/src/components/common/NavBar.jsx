import React from 'react';
import PropTypes from 'prop-types';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';
import { Link } from "react-router-dom";


const styles = theme => ({
    title: {
        fontSize: 24,
    }
});

/**
 * NavBar
 */
class NavBar extends React.Component {

    /**
     * render
     */
    render() {
        const { classes } = this.props;

        return (
            <React.Fragment>
                <AppBar position="static">
                    <Toolbar>
                        <Typography variant="h6" color="inherit" className={classes.title}>
                            <Link to="/app/home" style={{ textDecoration: 'none', color: 'inherit' }}>
                                Posts' Blog
                                    </Link>
                        </Typography>


                    </Toolbar>
                </AppBar>
            </React.Fragment>
        )
    }
}


NavBar.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(NavBar);