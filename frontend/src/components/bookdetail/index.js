import React , {useState, useEffect} from 'react';
import {useMediaQuery} from 'react-responsive';
import {bookAPI, reviewAPI, likeAPI} from '../../utils/axios';
import { useLocation } from "react-router-dom";
import {Button, Rating, TextField, Tab, IconButton, Stack} from '@mui/material';
import {TabContext,TabList,TabPanel} from '@mui/lab';
import ShoppingCartOutlinedIcon from '@mui/icons-material/ShoppingCartOutlined';
import RoomOutlinedIcon from '@mui/icons-material/RoomOutlined';
import FavoriteBorderRoundedIcon from '@mui/icons-material/FavoriteBorderRounded';
import FavoriteRoundedIcon from '@mui/icons-material/FavoriteRounded';
import './style.css';
import default_url from '../../images/default_imgurl.png';
import Pagination from 'react-js-pagination';
import './pagination.css'

const BookDetail= () =>{
    const location = useLocation();
    let bookisbn = location.pathname.split("/")[2];

    const [data, setData] = useState({});
    const [page, setPage] = useState(1);

    const [recentReivew, setrecentReivew] = useState({});
    const [recommReivew, setrecommReivew] = useState({});
    const [myReview, setmyReview] = useState('');
    const [moreMobile, setMoreMobile] = useState(false);
    const [isLike, setIsLike] = useState(0);
    const [tab, setTab] = useState('recently');
    const [imglink, setImglink] = useState(default_url);
    const [bookTitle, setbookTitle] = useState('');
    const [bookAuthor, setBookAuthor] = useState('');
    const [bookPub,setBookPub] = useState('');
    const [bookPubdate, setBookPubdate] = useState('알 수 없음');
    const [myGrade, setMyGrade] = useState(0);
    const [bookGrade, setbookGrade] = useState(0);
    const [bookDesc, setbookDesc] = useState('등록된 책 소개가 없습니다.');

    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });

    const handleTab = (event, newTab) =>{
        setTab(newTab);
    };

    const handlePage = (event) =>{
        setPage(event);
    }
    
    const handleReview = (e) =>{
        setmyReview(e.target.value);
    }

    const handleGrade = (e, newValue) =>{
        if(myGrade!==newValue) setMyGrade(newValue);
    }

    const addgrade = async()=>{
        await bookAPI.addBookGrade(bookisbn, myGrade);
    }

    const modifygrade = async()=>{
        await bookAPI.modifyBookGrade(bookisbn, myGrade);
    }

    const loadBook = async()=>{
        const result = await bookAPI.detail(bookisbn);
        setData(result);
        // console.log(result);
        if(result.bookImgUrl.length>0) setImglink(result.bookImgUrl);
        setbookTitle(result.bookTitle);
        setBookPub(result.bookPub);
        setBookAuthor(result.bookAuthor);
        if(result.bookPubDate.length>0) setBookPubdate(result.bookPubDate);
        setMyGrade(result.myGrade);
        setbookGrade(result.avgGrade);
        if(result.bookDesc.length>0) setbookDesc(result.bookDesc);
        setrecommReivew(result.recommReviewList);
        setrecentReivew(result.recentReviewList);
        setIsLike(result.isLiked);
    }
    useEffect(()=>{
        
        loadBook();
        const interval = setInterval(()=>{loadBook()}, 7000)

        return ()=> clearInterval(interval);
    },[]);

    const reviewLikes = async(reviewId, isLiked, click=false)=>{
        if (isLiked===0 && click===true) {
            await reviewAPI.like(reviewId);
        }else if(isLiked===1 && click===true){
            await reviewAPI.likeremove(reviewId);
        }
    }

    const reviewAppended = async()=>{
        try{
            await reviewAPI.write(bookisbn, myReview);
            alert('등록이 완료되었습니다.');
            setmyReview('');
        }catch(err){
            alert('등록에 실패했습니다.');
            console.log(err);
        }
    }

    const addBookLike = async(isLike)=>{
        if(isLike===0) await likeAPI.addlike(bookisbn);
        else if (isLike===1) await likeAPI.removelike(bookisbn);
    }

    // page마다 보여줄 개수정하기
    const pageList = (reviewlist)=>{
        let indexOfLast = page * 5;
        let indexOfFirst = indexOfLast - 5;
        if (indexOfLast>reviewlist.length) indexOfLast = reviewlist.length;
        let lst = Array.from(reviewlist).slice(indexOfFirst, indexOfLast);
        let result = [];
        lst.map(item=>{
            result = result.concat(
                <div id='reviewFormWeb'>
                    <div>
                        {item.review_content}
                    </div>
                    <div id='reviewWriterWeb'>
                        <div id='reviewMargin'>{item.user_nickname}</div>
                        <div id='reviewMargin'>{item.review_date}</div>
                        <IconButton onClick={()=> reviewLikes(item.review_id, item.isLiked, true)}>
                            <FavoriteBorderRoundedIcon id='likeColor'/>
                        </IconButton>
                        <div>{item.review_like_cnt}</div>
                    </div>
                </div>
            )
        })
        return result;
    }

    const pageListMobile = (reviewlist)=>{
        let indexOfLast = page * 5;
        let indexOfFirst = indexOfLast - 5;
        if (indexOfLast>reviewlist.length) indexOfLast = reviewlist.length;
        let lst = Array.from(reviewlist).slice(indexOfFirst, indexOfLast);
        let result = [];
        lst.map(item=>{
            result = result.concat(
                <div id='reviewFormMobile'>
                    <div id='reviewWriterMobile'>
                        <div id='reviewMargin'>{item.user_nickname}</div>
                        <div id='reviewMargin'>{item.review_date}</div>
                        <IconButton onClick={()=> reviewLikes(item.review_id, item.isLiked, true)}>
                            <FavoriteBorderRoundedIcon id='likeColor'/>
                        </IconButton>
                        <div>{item.review_like_cnt}</div>
                    </div>
                    <div>
                        {item.review_content}
                    </div>                    
                </div>
            )
        })
        return result;
    }

    return(
        <div>
            {isMobile?(
                <div className='divForm'>
                    <div id='bookDetailMobile'>
                        <div id='bookTitleMobile'>
                            {bookTitle} 
                            {isLike?(
                                <FavoriteRoundedIcon id='likeBookMobile' onClick={()=>addBookLike(isLike)}/>
                            ):(
                                <FavoriteRoundedIcon id='dislikeBookMobile' onClick={()=>addBookLike(isLike)}/>
                            )}
                        </div>
                        <div id='bookScoreMobile'>평균 평점 {bookGrade.toFixed(1)}</div>
                        <div id='bookImgDivMobile'>
                            <img src={imglink} id='bookDetailImg'/>
                        </div>
                        <div id='bookDataMobile'>{bookAuthor}</div>
                        <div id='bookDataMobile'>출판사 {bookPub}</div>
                        <div id='bookPubdateMobile'>발행일자 {bookPubdate}</div>
                        
                        <Button id='buyMobile'><ShoppingCartOutlinedIcon/>도서 구매하기</Button>
                        <Button id='rentMobile'><RoomOutlinedIcon/>내 주변에서 도서 대여하기</Button>
                        <div id='myGradeDivMobile'>
                            <div id='myGradeTitleMobile'>이미 읽은 도서인가요?</div>
                            <Rating value={myGrade} size='large' onChange={handleGrade} id='ratingStyle'/>
                            {data.myGrade===0?(
                                <div id='ratingfontMobile' onClick={()=>addgrade()}>등록</div>
                            ):(
                                <div id='ratingfontMobile' onClick={()=>modifygrade()}>변경</div>
                            )}
                        </div>
                    </div>
                    <div id='dividerMobile'></div>
                    <div id='bookDescMobile'>
                        <div id='bookIntroduceMobile'>책 소개</div>
                        <div id={moreMobile?"":"moreMobile"} style={{fontSize:'3vw'}}>
                            {bookDesc}
                        </div>
                        {moreMobile?null:(
                            <div id='morebtn' onClick={()=>setMoreMobile(true)}>더보기</div>
                        )}
                    </div>
                    <div id='dividerMobile'></div>
                    <div id='bookDescMobile'>
                        {/* 리뷰 작성 */}
                        <div id='reviewTitleMobile'>
                            리뷰
                        </div>
                        {/* 리뷰 form */}
                        <div id='reviewWriteFormMobile'>
                            <TextField multiline rows={2} value={myReview} onChange={handleReview} style={{width:'64vw', backgroundColor:'#F3F3F3'}}/>
                            <Button id='submitBtnMobile' onClick={()=>reviewAppended()}>등록</Button>
                        </div>
                        {/* 리뷰 list */}
                        <div>
                            <TabContext value={tab} >
                                <div id='reviewTabMobile'>
                                    <TabList onChange={handleTab} textColor='black' indicatorColor='secondary'>
                                        <Tab label = "추천순" value="recommend"/>
                                        <Tab label = "최신순" value="recently"/>
                                    </TabList>
                                </div>
                                {/* 추천순 */}
                                <TabPanel value = "recommend">
                                    {/* 리뷰 */}
                                    <Stack spacing={2}>
                                        <div>
                                        {pageListMobile(recommReivew)}
                                        </div>
                                        <div id='paginationMobile'>
                                            <Pagination itemsCountPerPage={Math.ceil(recentReivew.length/5)} activePage={page} onChange={handlePage} prevPageText={"<"} nextPageText={">"} itemsCountPerPage={5} totalItemsCount={recentReivew.length}/>
                                        </div>
                                    </Stack>
                                </TabPanel>
                                {/* 최신순 */}
                                <TabPanel value = "recently">
                                    <Stack spacing={2}>
                                        <div>
                                        {pageListMobile(recentReivew)}
                                        </div>
                                        <div id='paginationMobile'>
                                            <Pagination itemsCountPerPage={Math.ceil(recentReivew.length/5)} activePage={page} onChange={handlePage} prevPageText={"<"} nextPageText={">"} itemsCountPerPage={5} totalItemsCount={recentReivew.length}/>
                                        </div>
                                    </Stack>
                                </TabPanel>
                            </TabContext>
                        </div>
                    </div>
                </div>
            ):(
                <div className='divForm'>
                    <div id='bookDetailWeb'>
                        <div id='bookInfoWeb'>
                            <div>
                                <div id='bookTitleWeb'>
                                    {bookTitle}
                                    {isLike?(
                                        <FavoriteRoundedIcon id='likeBookWeb' onClick={()=>addBookLike(isLike)}/>
                                    ):(
                                        <FavoriteRoundedIcon id='dislikeBookWeb' onClick={()=>addBookLike(isLike)}/>
                                    )}
                                </div>
                                <div id='bookScoreWeb'>평균 평점 {bookGrade.toFixed(1)}</div>
                            </div>
                            <div>
                                <div id='bookContentWeb'>{bookAuthor}</div>
                                <div id='bookContentWeb'>출판사 {bookPub}</div>
                                <div id='bookContentWeb'>발행일자 {bookPubdate}</div>
                            </div>
                            <div id='columnDiv'>
                                <Button id='buyWeb'><ShoppingCartOutlinedIcon/>도서 구매하기</Button>
                                <Button id='rentWeb'><RoomOutlinedIcon/>내 주변에서 도서 대여하기</Button>
                                <div id='myGradeDivWeb'>
                                    <div id='myGradeTitleWeb'>이미 읽은 도서인가요?</div>
                                    <Rating value={myGrade} size='large' onChange={handleGrade} id='ratingStyle'/>
                                    {data.myGrade===0?(
                                        <div id='ratingfontWeb' onClick={()=>addgrade()}>등록</div>
                                    ):(
                                        <div id='ratingfontWeb' onClick={()=>modifygrade()}>변경</div>
                                    )}
                                </div>
                            </div>
                        </div>
                        <div id='bookDetailImgDivWeb'>
                            <img src={imglink} id='bookDetailImg'/>
                        </div>
                    </div>
                    <div id='dividerWeb'></div>
                    {/* book description */}
                    <div id='bookDescMobile'>
                        <div id='bookIntroduceWeb'>책 소개</div>
                        <div id='bookDescWeb'>
                            {bookDesc}
                        </div>
                    </div>
                    <div id='dividerWeb'></div>
                    {/* review */}
                    <div id='bookDescMobile'>
                        {/* 리뷰 작성 */}
                        <div id='bookIntroduceWeb'>
                            리뷰
                        </div>
                        {/* 리뷰 form */}
                        <div id='reviewWriteFormWeb'>
                            <TextField multiline rows={3} value={myReview} onChange={handleReview} style={{width:'64vw', backgroundColor:'#F3F3F3'}}/>
                            <Button id='submitBtnWeb' onClick={()=>reviewAppended()}>등록</Button>
                        </div>
                        {/* 리뷰 list */}
                        <div>
                            <TabContext value={tab} >
                                <div id='reviewTabWeb'>
                                    <TabList onChange={handleTab} textColor='black' indicatorColor='secondary'>
                                        <Tab label = "추천순" value="recommend"/>
                                        <Tab label = "최신순" value="recently"/>
                                    </TabList>
                                </div>
                                {/* 추천순 */}
                                <TabPanel value = "recommend">
                                    {/* 리뷰 */}
                                    <Stack spacing={2}>
                                        <div>
                                        {pageList(recommReivew)}
                                        </div>
                                        <div id='paginationWeb'>
                                            <Pagination itemsCountPerPage={Math.ceil(recentReivew.length/5)} activePage={page} onChange={handlePage} prevPageText={"<"} nextPageText={">"} itemsCountPerPage={5} totalItemsCount={recentReivew.length}/>
                                        </div>
                                    </Stack>
                                </TabPanel>
                                {/* 최신순 */}
                                <TabPanel value = "recently">
                                    <Stack spacing={2}>
                                        <div>
                                        {pageList(recentReivew)}
                                        </div>
                                        <div id='paginationWeb'>
                                            <Pagination itemsCountPerPage={Math.ceil(recentReivew.length/5)} activePage={page} onChange={handlePage} prevPageText={"<"} nextPageText={">"} itemsCountPerPage={5} totalItemsCount={recentReivew.length}/>
                                        </div>
                                    </Stack>
                                </TabPanel>
                            </TabContext>
                        </div>
                    </div>
                </div>
            )}
            
        </div>
    );
}

export default BookDetail;