import React from 'react';
import AppContent from '../common/AppContent';
import { connect } from 'react-redux';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';


import { getPostInfos, getUserInfos, getAllComms } from '../../actions/blog';
import Card from "../common/card/Card.jsx";
import CardHeader from "../common/card/CardHeader.jsx";
import CardBody from "../common/card/CardBody.jsx";
import CardFooter from "../common/card/CardFooter.jsx";
import { Link } from "react-router-dom";
import Divider from '@material-ui/core/Divider';

import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';


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
    comm: {
        marginLeft: '2%'
    }
});

export class Post extends React.Component {

    constructor(props) {
        super(props);
        this.props.getPostInfos(this.props.match.params.id);
        this.props.getUserInfos(this.props.info.userId);
        this.props.getAllComms(this.props.match.params.id)
    }

    render() {
        const { classes, info, userInfo, commsInfo } = this.props;
        const { id } = this.props.match.params;

        return (
            <React.Fragment>
                <AppContent style={{ marginTop: 20 }}>
                    <React.Fragment>
                        <Typography variant="h6" color="inherit" className={classes.brand}>Post Informations</Typography>
                        <Card>
                            <CardHeader color="primary">
                                <h4 className={classes.cardTitleWhite}>
                                    {info.title}<br /><br /><br />
                                    <Divider /><br />
                                    Written by <Link to={`/app/users/${userInfo.id}`} style={{ textDecoration: 'none', color: '#FFF' }}>{userInfo.name}</Link>
                                </h4>

                            </CardHeader>
                            <CardBody><br /><br />
                                Content : <br /><br />
                                {info.body}
                            </CardBody>
                            <br /><br />
                            <Divider />
                            <div className={classes.comm}>
                            <br /><br />{commsInfo.length} Commentaires :<br /><br />
                            </div>
                            <CardFooter>
                                <Table className={classes.table}>
                                    <TableHead>
                                        <TableRow>
                                            <TableCell>Written by</TableCell>
                                            <TableCell>Name</TableCell>
                                            <TableCell>Content</TableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {commsInfo.map(comm =>
                                            <TableRow key={comm.id}>
                                                <TableCell component="th" scope="row">
                                                    {comm.email}
                                                </TableCell>
                                                <TableCell>
                                                    {comm.name}
                                                </TableCell>
                                                <TableCell>
                                                    {comm.body}
                                                </TableCell>
                                            </TableRow>
                                        )}
                                    </TableBody>
                                </Table>
                            </CardFooter>
                        </Card>
                    </React.Fragment>
                </AppContent>
            </React.Fragment>
        );
    }
}

function mapStateToProps(state) {
    return {
        info: state.postInformations.info,
        userInfo: state.userInformations.info,
        commsInfo: state.commsInformations.info,
    }
};

export default connect(
    mapStateToProps,
    { getPostInfos, getUserInfos, getAllComms }
)(withStyles(styles)(Post));