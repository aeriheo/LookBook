import React from 'react';
import Intro from '../../components/intro';
import Login from '../../components/login';

const LoginPage = props => {
    
    return(
        <div style={{height: '100%', display:"flex", flexDirection:"column", justifyContent:"space-between"}}>
            <div>
                <Intro  />
            </div>
            <div>
               <Login/> 
            </div>
            
        </div>
    );
};


export default LoginPage;