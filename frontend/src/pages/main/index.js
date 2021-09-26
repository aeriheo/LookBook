import React from 'react';
import Header from '../../components/headers';
import MainSession from '../../components/main';
import {Grid} from '@mui/material';

const Main = props => {
    return(
        <div style={{height: '100%', display:"flex", flexDirection:"column", justifyContent:"space-between"}}>
            <Header  />
            <Grid>
                <MainSession/>
            </Grid>
        </div>
    );
};


export default Main;