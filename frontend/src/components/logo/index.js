import React from 'react';
import './style.css';
import {useMediaQuery} from 'react-responsive';

const Logo = ()=>{
    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });

    return(
        <div>
            {isMobile?(
                <div id='containerMobile'>
                    <div id='logo'>Look Book</div>
                </div>
            ):(
                <div id='containerWeb'>
                    <div id='logo'>Look Book</div>
                </div>
            )}
        </div>
    )
}

export default Logo;