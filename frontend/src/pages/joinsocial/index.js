import React from 'react';
import JoinSocial from '../../components/joinSocial';

const JoinSocialPage = props => {
    return(
        <div style={{height: '100%', display:"flex", flexDirection:"column", justifyContent:"space-between"}}>
            <div>
               <JoinSocial/> 
            </div>
        </div>
    );
};


export default JoinSocialPage;