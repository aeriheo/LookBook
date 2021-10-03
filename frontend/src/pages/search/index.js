import React from 'react';
import Header from '../../components/headers';
import Search from '../../components/search';
import {Grid} from '@mui/material';

const SearchPage = props => {
    return(
        <div style={{height: '100%', display:"flex", flexDirection:"column", justifyContent:"space-between"}}>
            <Header  />
            <Grid>
                <Search/>
            </Grid>
        </div>
    );
};


export default SearchPage;