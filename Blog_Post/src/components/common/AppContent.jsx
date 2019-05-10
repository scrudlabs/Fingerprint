import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import LayoutBody from './LayoutBody';


const styles = theme => ({
  root: {
    display: 'flex',
  }
});

function AppContent(props) {
  const { children, classes } = props;

  return (
    <div className={classes.root}>
      <LayoutBody margin width='large'>
        {children}
      </LayoutBody>
    </div>
  );
}

AppContent.propTypes = {
  children: PropTypes.node.isRequired,
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(AppContent);