import React from 'react';
import AppContent from '../common/AppContent';
import { connect } from 'react-redux';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';


import { getUserInfos } from '../../actions/blog';
import Card from "../common/card/Card.jsx";
import CardHeader from "../common/card/CardHeader.jsx";
import CardBody from "../common/card/CardBody.jsx";


//styles
const styles = theme => ({
    root: {
        width: '100%',
        marginTop: theme.spacing.unit * 3,
        overflowX: 'auto',
    },
    table: {
        minWidth: 500,
    },
});

export class User extends React.Component {

    constructor(props) {
        super(props);
        this.props.getUserInfos(this.props.match.params.id);
    }

    render() {
        const { classes, info, userInfo } = this.props;
        const { id } = this.props.match.params;

        return (
            <React.Fragment>
                <AppContent style={{ marginTop: 20 }}>
                    <React.Fragment>
                        <Typography variant="h6" color="inherit" className={classes.brand}>User Informations</Typography>
                        <Card>
                            <CardHeader color="primary">
                                <h4 className={classes.cardTitleWhite}>
                                    {userInfo.name}
                                </h4>

                            </CardHeader>
                            <CardBody><br /><br />
                                Username: {userInfo.username},<br /><br />
                                Email: {userInfo.email},<br /><br />
                                Address: Street {userInfo.address.street}, {userInfo.address.suite}, {userInfo.address.zipcode} {userInfo.address.city}, Latitude: {userInfo.address.geo.lat}, Longitude: {userInfo.address.geo.lng}<br /><br />
                                Phone number: {userInfo.phone}<br /><br />
                                Website: {userInfo.website}<br /><br />
                                Company: {userInfo.company.name} "{userInfo.company.catchPhrase}", Sector: {userInfo.company.bs}
                            </CardBody>
                        </Card>
                    </React.Fragment>
                </AppContent>
            </React.Fragment>
        );
    }
}

function mapStateToProps(state) {
    return {
        userInfo: state.userInformations.info,
    }
};

export default connect(
    mapStateToProps,
    { getUserInfos }
)(withStyles(styles)(User));