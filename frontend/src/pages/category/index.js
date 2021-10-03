import React from 'react';
import Header from '../../components/headers';
import Category from '../../components/category';
import {Grid} from '@mui/material';

const CategoryPage = props => {
    return(
        <div style={{height: '100%', display:"flex", flexDirection:"column", justifyContent:"space-between"}}>
            <Header  />
            <Grid>
                <Category/>
            </Grid>
        </div>
    );
};


export default CategoryPage;