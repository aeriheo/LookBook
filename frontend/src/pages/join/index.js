import React from 'react';
import Join from '../../components/join';

const JoinPage = props => {
    return(
        <div style={{height: '100%', display:"flex", flexDirection:"column", justifyContent:"space-between"}}>
            <div>
               <Join/> 
            </div>
        </div>
    );
};


export default JoinPage;