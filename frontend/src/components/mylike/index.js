import React, {useState, useEffect} from 'react';
import {useMediaQuery} from 'react-responsive';
import { useHistory } from "react-router-dom";
import {likeAPI} from '../../utils/axios';
import './style.css';

const Mylike = () =>{
    let history = useHistory();
    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });
    
    const [data, setData] = useState({});
    const loadLike = async()=>{
        const result = await likeAPI.mylikelist();
        setData(result.likeBookList);
    }

    useEffect(()=>{
        loadLike();
    },[]);



    const likelist = (data)=>{
        let result = [];
        if(data.length>0){
            data.map(item=>{
                result = result.concat(
                    <div id='bookDivWeb' onClick={()=>history.push(`/book/${item.bookIsbn}`)}>
                        <img src={item.bookImgUrl} id='bookLikeImgWeb' />
                        <div id='bookTitlelikeWeb'>
                            <div id='bookTitleFontWeb'>
                                {item.bookTitle}
                            </div>
                        </div>
                    </div>
                )                    
            })
        }
        return result;
    }

    return(
        <div>
            {isMobile ? (
                <div id='myLikeDivMobile'>
                    <div id='myLikeList'>
                        {likelist(data)}
                    </div>
                </div>
            ):(
                <div id='myLikeDivWeb'>
                    <div id='myListNameWeb'>
                        LIKE
                    </div>
                    <div id='myLikeList'>
                        {likelist(data)}
                    </div>
                </div>
            )}
        </div>
    )
}

export default Mylike;