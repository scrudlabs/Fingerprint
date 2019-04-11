import React from 'react';
import AppContent from '../common/AppContent';
import { connect } from 'react-redux';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';

import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';


import { Link } from "react-router-dom";

import { getAllPosts } from '../../actions/blog';


//styles
const styles = theme => ({
    root: {
        width: '100%',
        marginTop: theme.spacing.unit * 3,
        overflowX: 'auto',
    },
    table: {
        minWidth: 700,
    },
});


export class Home extends React.Component {

    constructor(props) {
        super(props);
        this.props.getAllPosts();
    }



    render() {
        const { classes, posts } = this.props;

        return (

            <React.Fragment>
            <div>
                Toto
            </div>
                <AppContent style={{ marginTop: 20 }}>

                    <React.Fragment>
                        <Typography variant="h6" color="inherit" className={classes.brand}>All the posts</Typography>
                        <Paper className={classes.root}>
                            <Table className={classes.table}>
                                <TableHead>
                                    <TableRow>
                                        <TableCell>Id</TableCell>
                                        <TableCell>Title</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {posts.map(post =>
                                        <TableRow key={post.id}>
                                            <TableCell component="th" scope="row">
                                                <Link to={`/app/post/${post.id}`} style={{ textDecoration: 'none', color: '#000' }}>{post.id}</Link>
                                            </TableCell>
                                            <TableCell>
                                                <Link to={`/app/post/${post.id}`} style={{ textDecoration: 'none', color: '#000' }}>{post.title}</Link>
                                            </TableCell>
                                        </TableRow>
                                    )}
                                </TableBody>
                            </Table>
                        </Paper>
                    </React.Fragment>

                </AppContent>
            </React.Fragment>
        );
    }
}

function mapStateToProps(state) {
    return {
        posts: state.allPosts.posts,
    }
};

export default connect(
    mapStateToProps,
    { getAllPosts }
)(withStyles(styles)(Home));