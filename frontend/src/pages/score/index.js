import React from 'react';
import Score from '../../components/score';
import {Grid} from '@mui/material';

const ScorePage = props => {
    return(
        <div style={{height: '100%', display:"flex", flexDirection:"column", justifyContent:"space-between"}}>
            <Grid>
                <Score/>
            </Grid>
        </div>
    );
};


export default ScorePage;