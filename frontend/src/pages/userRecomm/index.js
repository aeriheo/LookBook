import React from 'react';
import Header from '../../components/headers';
import UserRecomm from '../../components/userRecomm';
import {Grid} from '@mui/material';

const userBasedPage = props => {
    return(
        <div style={{height: '100%', display:"flex", flexDirection:"column", justifyContent:"space-between"}}>
            <Header  />
            <Grid>
                <UserRecomm/>
            </Grid>
        </div>
    );
};


export default userBasedPage;