import React from 'react';
import Header from '../../components/headers';
import KakaoMaps from '../../components/kakaomaps';
import {Grid} from '@mui/material';

const Library = props => {
    return(
        <div style={{height: '100%', display:"flex", flexDirection:"column", justifyContent:"space-between"}}>
            <Header  />
            <Grid>
                <KakaoMaps/>
            </Grid>
        </div>
    );
};


export default Library;