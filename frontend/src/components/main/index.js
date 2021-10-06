import React, {useState, useLayoutEffect} from 'react';
import {useMediaQuery} from 'react-responsive';
import {userAPI, bookAPI} from '../../utils/axios';
import { useHistory } from "react-router-dom";
import default_url from '../../images/default_imgurl.png';
import { Card, CardContent} from '@mui/material';
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from 'react-slick';
import Logo from '../logo';
import './style.css';

const MainSession = () =>{
    let history = useHistory();
    const [data, setData] = useState({});
    const [bestTen, setbestTen] = useState([]); //BEST 10
    const [bestReview, setbestReview] = useState([]);
    const [userRecommList, setuserRecommList] = useState([]); //CF user
    const [otherRecommList, setotherRecommList] = useState([]); // CF UserBased
    const [itemRecommList, setitemRecommList] = useState([]); //CF itemBased
    const [numberOne, setnumberOne] = useState(''); 
    const [numList, setNumList] = useState(1);
    
    useLayoutEffect(()=>{

        async function loadUser(){
            const result = await userAPI.userinfo();
            setData(result.data);
            setNumList(result.data.bookGradeListSize);
            if(numList===0) {
                alert('추천 시스템 이용을 위해 평점 페이지로 이동합니다.');
                window.location.href='/score';
            }
        }

        async function loadBooks(){
            const result = await bookAPI.mainBooks();
            setbestTen(result.bestBookList);
            setbestReview(result.bestReview);
            setuserRecommList(result.userPredictedGradeList);
            setotherRecommList(result.userBasedCFList);
            setitemRecommList(result.itemBasedCFList);
            setnumberOne(result.bestBookList[0].bookTitle);
        }

        loadUser();
        loadBooks();
    },[]);


    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });

    const bookSettings = {
        slidesToShow : 5,
        slidesToScroll: 1,
        centerMode: true,
        className: 'center',
        infinite:true,
    };

    const bookMobileSettings = {
        slidesToShow : 3,
        slidesToScroll: 1,
        arrows:false,
        infinite:true,
        speed:500,
    }

    return(
        <div>
            <Logo/>
            {isMobile?(
                <div className="mobileWidth">
                    <div id='bestMobile'>
                        <div id='bestTenMobile'>Best 10</div>
                    </div>
                    <div id='bestSliderMobile'>
                        <Slider {...bookMobileSettings}>
                            {bestTen && bestTen.map(item=>{
                                return(
                                    <Card variant='outlined' sx={{maxWidth: 140,alignItems: 'center',justifyContent: 'center',cursor:'pointer', border:0}} onClick={() => {history.push(`/book/${item.bookIsbn}`)}}>
                                        <CardContent sx={{alignItems: 'center', justifyContent: 'center', marginLeft:'auto'}}>
                                            <div id='cardSizeMobile'>
                                                {item.bookImgUrl?(
                                                    <img src={item.bookImgUrl} width='100%' id='cardImgMobile'/>
                                                ):(
                                                    <img src={default_url} width='100%' id='cardImgMobile'/>
                                                )}
                                                <div id='cardTitleMobile'>{item.bookTitle}</div>
                                            </div>
                                        </CardContent>
                                    </Card>
                                )
                            })}
                        </Slider>
                    </div>
                </div>
            ):(
                <div>
                    <div id='bestWeb'>
                        <div id='bestTenWeb'>Best 10</div>
                    </div>
                    <div id='cardSizeWeb'>
                        <Slider {...bookSettings}>
                            {bestTen && bestTen.map(item=>{
                                return(
                                    <div id='bestTenListDiv' onClick={() => {history.push(`/book/${item.bookIsbn}`)}}>
                                        <div id='bestTenImgDiv'>
                                            {item.bookImgUrl?(
                                                <img src={item.bookImgUrl} id='carouselImgWeb'/>
                                            ):(
                                                <img src={default_url} id='carouselImgWeb'/>
                                            )}
                                        </div>
                                        <div id='textcss'>{item.bookTitle}</div>
                                    </div>
                                )
                            })}
                        </Slider>
                    </div>
                </div>
            )}
            {/* best review */}
            {isMobile?(
                <div id='reviewMobile'>
                    <div id='reviewDivMobile'>
                        <div id='reviewNameMobile'>
                            Best Review
                        </div>
                    </div>
                    <div id='reviewContentMobile'>
                        <div id='reviewTitleMobile'>
                        {bestReview.bookTitle}
                        </div>
                        <div id='reviewGoMobile' onClick={() => {history.push(`/book/${bestReview.bookIsbn}`)}}>
                            {bestReview.bookTitle} 보러가기
                        </div>
                    </div>
                    <div id='reviewTextMobile'>
                        {bestReview.reviewContent}
                    </div>
                    <div id='reviewTextMobile'>
                        <div id='reviewUserMobile'>
                            by {bestReview.userNickname}
                        </div>
                    </div>
                </div>
            ):(
                <div id='reviewWeb'>
                    <div id='reviewWeb2'>
                        <div id='reviewContentWeb'>
                            <div id='reviewNameWeb'>Best Review</div>
                            <div id='reviewGoWeb' onClick={() => {history.push(`/book/${bestReview.bookIsbn}`)}}>{bestReview.bookTitle} 보러가기</div>
                        </div>
                        <div id='reviewTitleWeb'>{bestReview.bookTitle}</div>
                        <div id='reviewTextFormWeb'>
                            <div id='reviewTextWeb'>
                                {bestReview.reviewContent}
                            </div>
                            <div id='reviewUserWeb'>
                                by {bestReview.userNickname}
                            </div>
                        </div>
                    </div>
                    <div id='reviewBookWeb' onClick={() => {history.push(`/book/${bestReview.bookIsbn}`)}}>
                        {bestReview.bookImgUrl?(
                            <img src={bestReview.bookImgUrl} id='reviewBookImg'/>
                        ):(
                            <img src={default_url} id='reviewBookImg'/>
                        )}
                    </div>
                </div>
            )}
            {/* 추천도서 */}
            {isMobile?(
                <div id='mobileWidth'>
                    <div id='recommDiv'>
                        <div id='recommTitleMobile'>'{data.userNickname}'님을 위한 추천 도서</div>
                        <div id='recommGoMobile' onClick={()=>window.location.href='/myrecommend'}>추천도서 보러가기</div>
                    </div>
                    <div id='recommBookMobile'>
                        {userRecommList && userRecommList.map(item=>{
                            return(
                                <div id='recommBookListDivMobile' onClick={() => {history.push(`/book/${item.bookIsbn}`)}}>
                                    <CardContent sx={{alignItems: 'center', justifyContent: 'center', marginLeft:'auto'}}>
                                        {item.bookImgUrl?(
                                                <img src={item.bookImgUrl} width='100%' height='100%' id='cardImgMobile'/>
                                            ):(
                                                <img src={default_url} width='100%' height='100%' id='cardImgMobile'/>
                                            )}
                                            <div id='cardTitleMobile'>{item.bookTitle}</div>
                                    </CardContent>
                                </div>
                            )
                        })}
                    </div>
                </div>
            ):(
                <div style={{marginBottom: '15vh'}}>
                    <div id='recommDiv'>
                        <div id='recommTitleWeb'>'{data.userNickname}'님을 위한 추천 도서</div>
                        <div id='recommGoWeb' onClick={()=>window.location.href='/myrecommend'}>추천도서 보러가기</div>
                    </div>
                    <div id='recommBookWeb'>
                        {userRecommList && userRecommList.map(item=>{
                            return(
                                <div id='recommBookListDivWeb' onClick={() => {history.push(`/book/${item.bookIsbn}`)}}>
                                    {item.bookImgUrl?(
                                        <img src={item.bookImgUrl} id='boxImgWeb'/>
                                    ):(
                                        <img src={default_url} id='boxImgWeb'/>
                                    )}
                                    <div id='recommBookTitleWeb'>{item.bookTitle}</div>
                                </div>
                            )
                        })}
                    </div>
                </div>
            )}
            {/* 나와 비슷한 성향의 유저 */}
            {isMobile?(
                <div id='mobileWidth'>
                    <div id='recommDiv'>
                        <div id='recommTitleMobile'>'{data.userNickname}'님과 비슷한 취향의 유저가 읽은 도서</div>
                        <div id='recommGoMobile'onClick={()=>window.location.href='/otherrecommend'}>추천도서 보러가기</div>
                    </div>
                    <div id='recommBookMobile'>
                        {otherRecommList && otherRecommList.map(item=>{
                            return(
                                <div id='recommBookListDivMobile' onClick={() => {history.push(`/book/${item.bookIsbn}`)}}>
                                    <CardContent sx={{alignItems: 'center', justifyContent: 'center', marginLeft:'auto'}}>
                                        {item.bookImgUrl?(
                                                <img src={item.bookImgUrl} width='100%' height='100%' id='cardImgMobile'/>
                                            ):(
                                                <img src={default_url} width='100%' height='100%' id='cardImgMobile'/>
                                            )}
                                            <div id='cardTitleMobile'>{item.bookTitle}</div>
                                    </CardContent>
                                </div>
                            )
                        })}
                    </div>
                </div>
            ):(
                <div style={{marginBottom: '10vh'}}>
                    <div id='recommDiv'>
                        <div id='recommTitleWeb'>'{data.userNickname}'님과 비슷한 취향의 유저가 읽은 도서</div>
                        <div id='recommGoWeb'onClick={()=>window.location.href='/otherrecommend'}>추천도서 보러가기</div>
                    </div>
                    <div id='recommBookWeb'>
                        {otherRecommList && otherRecommList.map(item=>{
                            return(
                                <div id='recommBookListDivWeb' onClick={() => {history.push(`/book/${item.bookIsbn}`)}}>
                                    {item.bookImgUrl?(
                                        <img src={item.bookImgUrl} id='boxImgWeb'/>
                                    ):(
                                        <img src={default_url} id='boxImgWeb'/>
                                    )}
                                    <div id='recommBookTitleWeb'>{item.bookTitle}</div>
                                </div>
                            )
                        })}
                    </div>
                </div>
            )}
            {/* 컬렉션 */}
            {isMobile?(
                <div id='mobileWidth'>
                    <div id='recommDiv'>
                        <div id='recommTitleMobile'>'{numberOne}'와 비슷한 책</div>
                        <div id='recommGoMobile' onClick={()=>window.location.href='/itemrecommend'}>비슷한 책 보러가기</div>
                    </div>
                    <div id='recommBookMobile'>
                        {itemRecommList && itemRecommList.map(item=>{
                            return(
                                <div id='recommBookListDivMobile' onClick={() => {history.push(`/book/${item.bookIsbn}`)}}>
                                    <CardContent sx={{alignItems: 'center', justifyContent: 'center', marginLeft:'auto'}}>
                                        {item.bookImgUrl?(
                                                <img src={item.bookImgUrl} width='100%' height='100%' id='cardImgMobile'/>
                                            ):(
                                                <img src={default_url} width='100%' height='100%' id='cardImgMobile'/>
                                            )}
                                            <div id='cardTitleMobile'>{item.bookTitle}</div>
                                    </CardContent>
                                </div>
                            )
                        })}
                    </div>
                </div>
            ):(
                <div style={{marginBottom: '20vh'}}>
                    <div id='recommDiv'>
                        <div id='recommTitleWeb'>'{numberOne}'와 비슷한 책</div>
                        <div id='recommGoWeb' onClick={()=>window.location.href='/itemrecommend'}>비슷한 책 보러가기</div>
                    </div>
                    <div id='recommBookWeb'>
                        {itemRecommList && itemRecommList.map(item=>{
                            return(
                                <div id='recommBookListDivWeb' onClick={() => {history.push(`/book/${item.bookIsbn}`)}}>
                                    {item.bookImgUrl?(
                                        <img src={item.bookImgUrl} id='boxImgWeb'/>
                                    ):(
                                        <img src={default_url} id='boxImgWeb'/>
                                    )}
                                    <div id='recommBookTitleWeb'>{item.bookTitle}</div>
                                </div>
                            )
                        })}
                    </div>
                </div>
            )}
        </div>
    )
}

export default MainSession;