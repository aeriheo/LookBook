import React from 'react';
import Header from '../../components/headers';
import ItemBased from '../../components/itembased';
import {Grid} from '@mui/material';

const itembasedPage = props => {
    return(
        <div style={{height: '100%', display:"flex", flexDirection:"column", justifyContent:"space-between"}}>
            <Header  />
            <Grid>
                <ItemBased/>
            </Grid>
        </div>
    );
};


export default itembasedPage;