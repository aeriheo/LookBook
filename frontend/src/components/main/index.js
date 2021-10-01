import React, {useState, useEffect} from 'react';
import {useMediaQuery} from 'react-responsive';
import Slider from 'react-slick';
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import './style.css';
import {Box, Card, CardContent} from '@mui/material';
import Logo from '../logo';
import {userAPI} from '../../utils/axios';
import { useHistory } from "react-router-dom";

const MainSession = () =>{
    let history = useHistory();
    const [q, setQ] = useState('react');
    const [data, setData] = useState({});
    // book isbn 수정필요
    let bookisbn = '9788984371071';

    useEffect(()=>{
        let completed = false;

        async function loadUser(){
            const result = await userAPI.userinfo();
            setData(result.data);
        }

        loadUser();

        return()=>{
            completed=true;
        };
    },[q]);

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

    
    const booklist = [
        {url:'https://image.aladin.co.kr/product/11830/53/cover500/k722531529_1.jpg ', title:'어린 왕자'},
        {url:'https://image.aladin.co.kr/product/27877/5/cover500/8954682154_1.jpg', title:'작별하지 않는다'},
        {url:'https://image.aladin.co.kr/product/27587/47/cover500/k962733015_1.jpg', title:'달러구트 꿈 백화점 2'}, 
        {url:'https://image.aladin.co.kr/product/27691/91/cover500/8934985011_1.jpg', title:'햇빛은 찬란하고 인생은 귀하니까요'}, 
        {url:'https://image.aladin.co.kr/product/27692/63/cover500/k082733434_1.jpg', title:'지구 끝의 온실'}, 
        {url:'https://image.aladin.co.kr/product/27541/91/cover500/8954681174_1.jpg', title:'밝은 밤'}, 
        {url:'https://image.aladin.co.kr/product/27608/83/cover500/k722733028_2.jpg', title:'다정한 것이 살아남는다'}, 
        {url:'https://image.aladin.co.kr/product/26302/71/cover500/8954677150_1.jpg', title:'긴긴밤'}, 
        {url:'https://image.aladin.co.kr/product/27817/71/cover500/k292734577_1.jpg', title:'럭키'}
    ]

    const bookreclist=[
        {url:'https://image.aladin.co.kr/product/11830/53/cover500/k722531529_1.jpg ', title:'어린 왕자'},
        {url:'https://image.aladin.co.kr/product/27877/5/cover500/8954682154_1.jpg', title:'작별하지 않는다'},
        {url:'https://image.aladin.co.kr/product/27587/47/cover500/k962733015_1.jpg', title:'달러구트 꿈 백화점 2'}, 
        {url:'https://image.aladin.co.kr/product/27691/91/cover500/8934985011_1.jpg', title:'햇빛은 찬란하고 인생은 귀하니까요'}, 
        {url:'https://image.aladin.co.kr/product/27692/63/cover500/k082733434_1.jpg', title:'지구 끝의 온실'}
    ]

    return(
        <div>
            {/* lookbook logo */}
            <Logo/>
            {/* LB best 10 */}
            {isMobile?(
                <div className="mobileWidth">
                    <div id='bestMobile'>
                        <div id='bestLBMobile'>LB</div>
                        <div id='bestTenMobile'>Best 10</div>
                    </div>
                    <div id='bestSliderMobile'>
                        <Slider {...bookMobileSettings}>
                            {booklist.map(item=>{
                                return(
                                    <Card variant='outlined' sx={{maxWidth: 140,alignItems: 'center',justifyContent: 'center',cursor:'pointer', border:0}} onClick={() => {history.push(`/book/${bookisbn}`)}}>
                                        <CardContent sx={{alignItems: 'center', justifyContent: 'center', marginLeft:'auto'}}>
                                            <div id='cardSizeMobile'>
                                                <img src={item.url} width='100%' id='cardImgMobile'/>
                                                <div id='cardTitleMobile'>{item.title}</div>
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
                        <div id='bestLBWeb'>LB</div>
                        <div id='bestTenWeb'>Best 10</div>
                    </div>
                    <div id='cardSizeWeb'>
                        <Slider {...bookSettings}>
                            {booklist.map(item=>{
                                return(
                                    <Box sx={{alignItems: 'center', justifyContent: 'center', cursor:'pointer'}} onClick={() => {history.push(`/book/${bookisbn}`)}}>
                                        <img src={item.url} id='boxImgWeb'/>
                                        <div id='textcss'>{item.title}</div>
                                    </Box>
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
                            어린왕자
                        </div>
                        <div id='reviewGoMobile'>
                            베스트 리뷰 보러가기
                        </div>
                    </div>
                    <div id='reviewTextMobile'>
                        ‘네 장미를 그토록 소중하게 만든 건 그 꽃에게 네가 바친 그 시간들이야’ 친구가 된다는것, 관계를 맺는 다는 것은 성실과 함께한 시간들이다. 그것이 내게 어린 왕자가 특별해 진 이유이기도 하다. 이 책은 나를 길들였고 나를 책임져야 한다.^^
                    </div>
                    <div id='reviewTextMobile'>
                        <div id='reviewUserMobile'>
                            by Nickname
                        </div>
                    </div>
                </div>
            ):(
                <div id='reviewWeb'>
                    <div id='reviewWeb2'>
                        <div id='reviewContentWeb'>
                            <div id='reviewNameWeb'>Best Review</div>
                            <div id='reviewGoWeb'>베스트 리뷰 보러가기</div>
                        </div>
                        <div id='reviewTitleWeb'>어린왕자</div>
                        <div id='reviewTextFormWeb'>
                            <div id='reviewTextWeb'>
                                ‘네 장미를 그토록 소중하게 만든 건 그 꽃에게 네가 바친 그 시간들이야’ 친구가 된다는것, 관계를 맺는 다는 것은 성실과 함께한 시간들이다. 그것이 내게 어린 왕자가 특별해 진 이유이기도 하다. 이 책은 나를 길들였고 나를 책임져야 한다.^^
                            </div>
                            <div id='reviewUserWeb'>
                                by Nickname
                            </div>
                        </div>
                    </div>
                    <div id='reviewBookWeb' onClick={() => {history.push(`/book/${bookisbn}`)}}>
                        <img src='https://image.aladin.co.kr/product/11830/53/cover500/k722531529_1.jpg' id='reviewBookImg'/>
                    </div>
                </div>
            )}
            {/* 추천도서 */}
            {isMobile?(
                <div id='mobileWidth'>
                    <div id='recommDiv'>
                        <div id='recommTitleMobile'>'{data.userNickname}'님을 위한 추천 도서</div>
                        <div id='recommGoMobile'>추천도서 보러가기</div>
                    </div>
                    <div id='recommBookMobile'>
                        {bookreclist.map(item=>{
                            return(
                                <Card variant='outlined' sx={{maxWidth: 140,alignItems: 'center',justifyContent: 'center',cursor:'pointer', border:0}} onClick={() => {history.push(`/book/${bookisbn}`)}}>
                                    <CardContent sx={{alignItems: 'center', justifyContent: 'center', marginLeft:'auto'}}>
                                        <div id='cardSizeMobile'>
                                            <img src={item.url} width='100%' id='cardImgMobile'/>
                                            <div id='cardTitleMobile'>{item.title}</div>
                                        </div>
                                    </CardContent>
                                </Card>
                            )
                        })}
                    </div>
                </div>
            ):(
                <div>
                    <div id='recommDiv'>
                        <div id='recommTitleWeb'>'{data.userNickname}'님을 위한 추천 도서</div>
                        <div id='recommGoWeb'>추천도서 보러가기</div>
                    </div>
                    <div id='recommBookWeb'>
                        {bookreclist.map(item=>{
                            return(
                                <Box sx={{alignItems: 'center', justifyContent: 'center', cursor:'pointer'}} onClick={() => {history.push(`/book/${bookisbn}`)}}>
                                    <img src={item.url} id='boxImgWeb'/>
                                    <div id='recommBookTitleWeb'>{item.title}</div>
                                </Box>
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
                        <div id='recommGoMobile'>추천도서 보러가기</div>
                    </div>
                    <div id='recommBookMobile'>
                        {bookreclist.map(item=>{
                            return(
                                <Card variant='outlined' sx={{maxWidth: 140,alignItems: 'center',justifyContent: 'center',cursor:'pointer', border:0}} onClick={() => {history.push(`/book/${bookisbn}`)}}>
                                    <CardContent sx={{alignItems: 'center', justifyContent: 'center', marginLeft:'auto'}}>
                                        <div id='cardSizeMobile'>
                                            <img src={item.url} width='100%' id='cardImgMobile'/>
                                            <div id='cardTitleMobile'>{item.title}</div>
                                        </div>
                                    </CardContent>
                                </Card>
                            )
                        })}
                    </div>
                </div>
            ):(
                <div>
                    <div id='recommDiv'>
                        <div id='recommTitleWeb'>'{data.userNickname}'님과 비슷한 취향의 유저가 읽은 도서</div>
                        <div id='recommGoWeb'>추천도서 보러가기</div>
                    </div>
                    <div id='recommBookWeb'>
                        {bookreclist.map(item=>{
                            return(
                                <Box sx={{alignItems: 'center', justifyContent: 'center', cursor:'pointer'}} onClick={() => {history.push(`/book/${bookisbn}`)}}>
                                    <img src={item.url} id='boxImgWeb'/>
                                    <div id='recommBookTitleWeb'>{item.title}</div>
                                </Box>
                            )
                        })}
                    </div>
                </div>
            )}
            {/* 컬렉션 */}
            {isMobile?(
                <div id='mobileWidth'>
                    <div id='recommDiv'>
                        <div id='recommTitleMobile'>'자까이름' 컬렉션</div>
                        <div id='recommGoMobile'>컬렉션 보러가기</div>
                    </div>
                    <div id='recommBookMobile'>
                        {bookreclist.map(item=>{
                            return(
                                <Card variant='outlined' sx={{maxWidth: 140,alignItems: 'center',justifyContent: 'center',cursor:'pointer', border:0}} onClick={() => {history.push(`/book/${bookisbn}`)}}>
                                    <CardContent sx={{alignItems: 'center', justifyContent: 'center', marginLeft:'auto'}}>
                                        <div id='cardSizeMobile'>
                                            <img src={item.url} width='100%' id='cardImgMobile'/>
                                            <div id='cardTitleMobile'>{item.title}</div>
                                        </div>
                                    </CardContent>
                                </Card>
                            )
                        })}
                    </div>
                </div>
            ):(
                <div style={{marginBottom: '2vw'}}>
                    <div id='recommDiv'>
                        <div id='recommTitleWeb'>'자까이름' 컬렉션</div>
                        <div id='recommGoWeb'>컬렉션 보러가기</div>
                    </div>
                    <div id='recommBookWeb'>
                        {bookreclist.map(item=>{
                            return(
                                <Box sx={{alignItems: 'center', justifyContent: 'center' , cursor:'pointer'}} onClick={() => {history.push(`/book/${bookisbn}`)}}>
                                    <img src={item.url} id='boxImgWeb'/>
                                    <div id='recommBookTitleWeb'>{item.title}</div>
                                </Box>
                            )
                        })}
                    </div>
                </div>
            )}
        </div>
    )
}

export default MainSession;