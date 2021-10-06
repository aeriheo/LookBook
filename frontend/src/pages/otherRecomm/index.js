import React from 'react';
import Header from '../../components/headers';
import OtherRecomm from '../../components/otherRecomm';
import {Grid} from '@mui/material';

const otherbasedPage = props => {
    return(
        <div style={{height: '100%', display:"flex", flexDirection:"column", justifyContent:"space-between"}}>
            <Header  />
            <Grid>
                <OtherRecomm/>
            </Grid>
        </div>
    );
};


export default otherbasedPage;