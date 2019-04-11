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

import { getAllPosts, getAllComms } from '../../actions/blog';


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
        this.state = {
            comms: [],
        };
        
    }



    render() {
        const { classes, posts, commsInfo } = this.props;
        const { comms } = this.state;

        // for (var post in posts) { 
        //     this.props.getAllComms(post.id);
        //     comms.push(commsInfo.length);
        // };

        return (
            <React.Fragment>
                <AppContent style={{ marginTop: 20 }}>

                    <React.Fragment>
                        <Typography variant="h6" color="inherit" className={classes.brand}>All the posts</Typography>
                        <Paper className={classes.root}>
                            <Table className={classes.table}>
                                <TableHead>
                                    <TableRow>
                                        <TableCell>Id</TableCell>
                                        <TableCell>Title</TableCell>
                                        <TableCell>Comments' Number</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {posts.map((post, index) =>
                                        
                                        <TableRow key={post.id}>
                                            <TableCell component="th" scope="row">
                                                <Link to={`/post/${post.id}`} style={{ textDecoration: 'none', color: '#000' }}>{post.id}</Link>
                                            </TableCell>
                                            <TableCell>
                                                <Link to={`/post/${post.id}`} style={{ textDecoration: 'none', color: '#000' }}>{post.title}</Link>
                                            </TableCell>
                                            {/* <TableCell>
                                                <Link to={`/post/${post.id}`} style={{ textDecoration: 'none', color: '#000' }}>{comms[index]}</Link>
                                            </TableCell> */}
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
        commsInfo: state.commsInformations.info,
    }
};

export default connect(
    mapStateToProps,
    { getAllPosts, getAllComms }
)(withStyles(styles)(Home));