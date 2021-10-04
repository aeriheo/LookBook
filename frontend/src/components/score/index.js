import React, {useState, useLayoutEffect} from 'react';
import {useMediaQuery} from 'react-responsive';
import {recommendAPI} from '../../utils/axios';
import default_url from '../../images/default_imgurl.png';
import {Button, Rating} from '@mui/material';
import {bookAPI, userAPI} from '../../utils/axios';
import './style.css';

const Score = () =>{
    const [data, setData] = useState([]);
    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });
    const [cnt, setCnt] = useState(0);

    const [myGrade, setMyGrade] = useState([{grade:0}, {grade:0}, {grade:0}, {grade:0}, {grade:0}, {grade:0},{grade:0}, {grade:0}, {grade:0}, {grade:0}, {grade:0}, {grade:0},{grade:0}, {grade:0}, {grade:0}, {grade:0}, {grade:0}, {grade:0},{grade:0}, {grade:0}, {grade:0}, {grade:0}, {grade:0}, {grade:0},{grade:0}, {grade:0}, {grade:0}, {grade:0}, {grade:0}, {grade:0}]);
    const handleGrade = (e)=>{
        const {name, value} = e.target;
        setMyGrade({
            ...myGrade,
            [name]:{grade:value}});
    }

    useLayoutEffect(()=>{
        async function firstLogin(){
            const result = await recommendAPI.firstRecomm();
            setData(result.firstBookList);
        }
        firstLogin();

    }, []);

    const insertScore = async(bookIsbn, bookGrade)=>{
        await bookAPI.addBookGrade(bookIsbn, bookGrade);
        setCnt(cnt+1);
    }

    const serviceStart = ()=>{
        if(cnt<5) alert('평가를 5개 이상 진행해주세요 !');
        else{
            alert('사용자 취향 분석 중 입니다.. 잠시만 기다려주세요!');
            userAPI.updateList();
            alert('환영합니다!');
            window.location.replace('/lookbook');
        }
    }

    const bookList = (data)=>{
        let result = [];
        {data && data.map((item, key)=>{
            result = result.concat(
                <div id='firstbookListDiv'>
                    <div id='firstbookImgSize'>
                        {item.bookImgUrl?(
                            <img src={item.bookImgUrl} id='firstbookImg'/>
                        ):(
                            <img src={default_url} id='firstbookImg'/>
                        )}
                    </div>
                    <div style={{margin:'10px'}}>
                        <div id='firstbookTitle'>{item.bookTitle}</div>
                        <div id='firstbookScore'>
                            <Rating value={myGrade[key].grade} size='large' name={key} onChange={handleGrade} id='firstRating'/>
                            <Button id='btn' onClick={()=>insertScore(item.bookIsbn, myGrade[key].grade)}>확인</Button>
                        </div>
                    </div>
                </div>
            )
        })}
        return result;
    }
    return(
        <div>
            {isMobile?(
                <div id='firstDivMobile'>
                    <div id='firstIntroDiv'>
                        <div id='firstIntro'>평가하기</div>
                        <Button id='btn' onClick={()=>serviceStart()}>평가 완료</Button>
                    </div>
                    <div id='firstDesc'>읽으신 책 또는 관심 있는 책의 평점을 최소 5개 이상 입력해주세요.</div>
                    <div id='firstList'>
                        {bookList(data)}
                    </div>
                </div>
            ):(
                <div id='firstDivWeb'>
                    <div id='firstIntroDiv'>
                        <div id='firstIntro'>평가하기</div>
                        <Button id='btn' onClick={()=>serviceStart()}>평가 완료</Button>
                    </div>
                    <div id='firstDesc'>읽으신 책 또는 관심 있는 책의 평점을 최소 5개 이상 입력해주세요.</div>
                    <div id='firstList'>
                        {bookList(data)}
                    </div>
                </div>
            )}
        </div>
    )
}

export default Score;