import React, {useState, useEffect} from 'react';
import {useMediaQuery} from 'react-responsive';
import { useHistory } from "react-router-dom";
import {reviewAPI} from '../../utils/axios';
import {Button, Rating} from '@mui/material';
import FavoriteBorderRoundedIcon from '@mui/icons-material/FavoriteBorderRounded';
import default_url from '../../images/default_imgurl.png';
import './style.css';

const Review = () =>{
    let history = useHistory();
    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });
    
    const [data, setData] = useState({});
    const loadReview = async()=>{
        const result = await reviewAPI.myreviews();
        setData(result.UserReviewList);
    }

    useEffect(()=>{
        loadReview();
    },[]);

    const deleteReview = async(reviewId)=>{
        let res = window.confirm('리뷰를 삭제하시겠습니까?');
        if (res) {
            try{
                await reviewAPI.deletemyreivew(reviewId);
                alert('리뷰가 삭제되었습니다.');
            }catch(e){
                alert('리뷰 삭제를 취소하셨습니다.');
            }
        }
    }

    const reviewList = (data)=>{
        let result = [];
        if (data.length > 0) {
            data.map(item=>{
                result = result.concat(
                    <div id='myLBDivWeb'>
                        <div id='myLBbookWeb' onClick={()=>history.push(`/book/${item.bookIsbn}`)}>
                            {item.bookImgUrl.length>0?(
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
                                <div>
                                    <Button id='myLBbookBtnWeb' onClick={()=>deleteReview(item.reviewId)}>삭제</Button>
                                </div>
                            </div>
                            <div is='myLBbookRatingDivWeb'>
                                <Rating value = {item.bookGrade} size = 'large' id='ratingColor' readOnly/>
                            </div>
                            <div id='myLBbookDateLikeWeb'>
                                {/* 날짜 좋아요 */}
                                <div id='myLBbookDateWeb'>{item.reviewDate.split("T")[0]}</div>
                                <FavoriteBorderRoundedIcon id='myLBLikeColor'/>
                                <div id='myLBLikeWeb'>{item.reviewLikeCount}</div>
                            </div>
                            <div>
                                {/* 리뷰내용 */}
                                <div id='myLBContentWeb'>
                                    {item.reviewContent}
                                </div>
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
                            <div id='myLBbookTitleMobile'>
                                {item.bookTitle}
                            </div>
                            <div>
                                <Button id='myLBbookBtnMobile' onClick={()=>deleteReview(item.reviewId)}>삭제</Button>
                            </div>
                        </div>
                        <div id='myLBbookDateLikeMobile'>
                            <Rating value = {item.bookGrade} size = 'large' id='ratingColor' readOnly/>
                            <div id='myLBbookDateMobile'>{item.reviewDate.split("T")[0]}</div>
                            <FavoriteBorderRoundedIcon id='myLBLikeMobile'/>
                            <div id='myLBbookLikeMobile'>{item.reviewLikeCount}</div>
                        </div>
                        <div id='myLBbookContentDivMobile'>
                            <div id='myLBbookContentMobile'>
                                {item.reviewContent}
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
                <div id='myLBMobile' style={{height: '100%'}}>
                    <div id='myLBList'>
                        {reviewListMobile(data)}
                    </div>
                </div>
            ):(
                <div id='myLBWeb'  style={{height: '100%'}}>
                    <div id='myLBNameWeb'>
                        REVIEW
                    </div>
                    <div id='myLBList'>
                        {reviewList(data)}
                    </div>
                </div>
            )}
        </div>
    )
}

export default Review;