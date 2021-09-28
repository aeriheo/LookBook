import React from 'react';
import {useMediaQuery} from 'react-responsive';
import './style.css';

const Intro = () =>{
    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });

    const imgurls = ['https://image.aladin.co.kr/product/11830/53/cover500/k722531529_1.jpg ','https://image.aladin.co.kr/product/27877/5/cover500/8954682154_1.jpg', 
'https://image.aladin.co.kr/product/27587/47/cover500/k962733015_1.jpg', 'https://image.aladin.co.kr/product/27691/91/cover500/8934985011_1.jpg', 'https://image.aladin.co.kr/product/27692/63/cover500/k082733434_1.jpg', 
'https://image.aladin.co.kr/product/27541/91/cover500/8954681174_1.jpg', 'https://image.aladin.co.kr/product/27608/83/cover500/k722733028_2.jpg', 'https://image.aladin.co.kr/product/26302/71/cover500/8954677150_1.jpg',
'https://image.aladin.co.kr/product/27817/71/cover500/k292734577_1.jpg', 'https://image.aladin.co.kr/product/27407/79/cover500/k362732558_1.jpg'];

    const imgArrWeb = () =>{
        let result = [];
        for (let i = 0; i <imgurls.length; i++) {
            result = result.concat(
                <div className='bookImgDiv'>
                    <img src={imgurls[i]} id='bookImg'/>
                </div>
            );
        }
        return result;
    };

    const imgArrMobile = () =>{
        let result = [];
        for (let i = 0; i <imgurls.length; i++) {
            result = result.concat(
                <div className='bookImgDivMobile'>
                    <img src={imgurls[i]} id='bookImg'/>
                </div>
            );
        }
        return result;
    }

    return(
        <div>
            {isMobile?(
                <div className='introDivMobile'>
                    <div id='bookDivMobile'>
                        {imgArrMobile()}
                    </div>
                    <div id='textLine'>
                        Look Book
                    </div>    
                    <div id='textShadow'>
                        책을 보다, 룩북
                    </div> 
                    <div style={{top:'90%'}} id='text'>
                        scroll
                    </div>
                    <div style={{top:'95%'}} id='text'>
                        ↓
                    </div>  
                </div>
            ):(
                <div className='introDivWeb'>
                    <div id='bookDiv'>
                        {imgArrWeb()}
                    </div>
                    <div id='textLine'>
                        Look Book
                    </div>    
                    <div id='textShadow'>
                        책을 보다, 룩북
                    </div> 
                    <div style={{top:'90%'}} id='text'>
                        scroll
                    </div>
                    <div style={{top:'95%'}} id='text'>
                        ↓
                    </div>       
                </div>
            )}
        </div>
    )
}

export default Intro;