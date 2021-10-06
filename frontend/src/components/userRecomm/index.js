import React, {useState, useLayoutEffect} from 'react';
import {useMediaQuery} from 'react-responsive';
import { useHistory } from "react-router-dom";
import {recommendAPI, userAPI} from '../../utils/axios';
import Logo from '../logo';
import './style.css';

const UserRecomm = () =>{
    let history = useHistory();
    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });
    
    const [data, setData] = useState([]);
    const [user, setUser] = useState('');
    const loadmyRecomm = async()=>{
        const result = await recommendAPI.userPredict();
        setData(result.userPredictedGradeList);
    }

    useLayoutEffect(()=>{
        async function loadUser(){
            const result = await userAPI.userinfo();
            setUser(result.data.userNickname);
        }
        loadmyRecomm();
        loadUser();
    },[]);



    const recommList = (data)=>{
        let result = [];
        {data&&data.map(item=>{
            result = result.concat(
                <div id='bookDivWeb' onClick={()=>history.push(`/book/${item.bookIsbn}`)}>
                    <img src={item.bookImgUrl} id='bookmyRecImg' />
                    <div id='bookTitlemyRec'>
                        <div id='bookTitleFontWeb'>
                            {item.bookTitle}
                        </div>
                    </div>
                </div>
            )}
        )}
        return result;
    }

    return(
        <div>
            <Logo/>
            {isMobile ? (
                <div id='myRecDivMobile'>
                    <div id='myRecListNameMobile'>
                        {user}님을 위한 추천 도서
                    </div>
                    <div id='myRecList'>
                        {recommList(data)}
                    </div>
                </div>
            ):(
                <div id='myRecDivWeb'>
                    <div id='myRecListNameWeb'>
                        {user}님을 위한 추천 도서
                    </div>
                    <div id='myRecList'>
                        {recommList(data)}
                    </div>
                </div>
            )}
        </div>
    )
}

export default UserRecomm;