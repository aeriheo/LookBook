import React, {useState, useEffect} from 'react';
import {useMediaQuery} from 'react-responsive';
import { useHistory } from "react-router-dom";
import {bookAPI} from '../../utils/axios';
import {Button, Rating} from '@mui/material';
import FavoriteBorderRoundedIcon from '@mui/icons-material/FavoriteBorderRounded';
import default_url from '../../images/default_imgurl.png';
import './style.css';

const MYLB = () =>{
    let history = useHistory();
    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });
    
    const [data, setData] = useState({});
    const loadGrade = async()=>{
        const result = await bookAPI.gradeList();
        setData(result.bookGradeList);
    }

    useEffect(()=>{
        loadGrade();
    },[]);

    const reviewList = (data)=>{
        let result = [];
        if (data.length > 0) {
            data.map(item=>{
                result = result.concat(
                    <div id='myLBDivWeb'>
                        <div id='myLBbookWeb' onClick={()=>history.push(`/book/${item.bookIsbn}`)}>
                            {item.bookImgUrl?(
                                <img src={item.bookImgUrl} id='myLBbookImgWeb'/>
                            ):(
                                <img src={default_url} id='myLBbookImgWeb'/>
                            )}
                            
                        </div>
                        <div>
                            <div id='myLBbookInfoDivWeb'>
                                <div id='myLBbookTitleWeb'>
                                    {item.bookTitle}
                                </div>
                            </div>
                            <div is='myLBbookRatingDivWeb'>
                                <Rating value = {item.bookGrade} size = 'large' id='ratingColor' readOnly/>
                            </div>
                        </div>
                    </div>
                )
            })
        }
        return result;
    }

    const reviewListMobile = (data)=>{
        let result = [];
        if (data.length > 0) {
            data.map(item=>{
                result = result.concat(
                    <div id='myLBDivMobile'>
                        <div id='myLBbookInfoDivMobile'>
                            <div id='myLBbookTitleMobile' onClick={()=>history.push(`/book/${item.bookIsbn}`)}>
                                {item.bookTitle}
                            </div>
                        </div>
                        <div id='myLBbookDateLikeMobile'>
                            <Rating value = {item.bookGrade} size = 'large' id='ratingColor' readOnly/>
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
                <div id='myLBMobile' style={{height: '100%'}}>
                    <div id='myLBList'>
                        {reviewListMobile(data)}
                    </div>
                </div>
            ):(
                <div id='myLBWeb'  style={{height: '100%'}}>
                    <div id='myLBNameWeb'>
                        MY LB
                    </div>
                    <div id='myLBList'>
                        {reviewList(data)}
                    </div>
                </div>
            )}
        </div>
    )
}

export default MYLB;