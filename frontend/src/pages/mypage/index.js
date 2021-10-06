import React from 'react';
import Header from '../../components/headers';
import MyInfo from '../../components/mypage';
import {Grid} from '@mui/material';

const Mypage = props => {
    return(
        <div style={{height: '100%', display:"flex", flexDirection:"column", justifyContent:"space-between"}}>
            <Header  />
            <Grid>
                <MyInfo/>
            </Grid>
            
        </div>
    );
};


export default Mypage;