import React, {useState, useLayoutEffect} from 'react';
import {useMediaQuery} from 'react-responsive';
import { useHistory } from "react-router-dom";
import {recommendAPI, userAPI} from '../../utils/axios';
import default_url from '../../images/default_imgurl.png';
import Logo from '../logo';
import './style.css';

const OtherRecomm = () =>{
    let history = useHistory();
    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });
    
    const [data, setData] = useState([]);
    const [user, setUser] = useState('');
    const loadotherRecomm = async()=>{
        const result = await recommendAPI.otherPredict();
        setData(result.userBasedCFList);
    }

    useLayoutEffect(()=>{
        async function loadUser(){
            const result = await userAPI.userinfo();
            setUser(result.data.userNickname);
        }
        loadotherRecomm();
        loadUser();
    },[]);



    const recommList = (data)=>{
        let result = [];
        {data&&data.map(item=>{
            result = result.concat(
                <div id='bookDivWeb' onClick={()=>history.push(`/book/${item.bookIsbn}`)}>
                    {item.bookImgUrl?(
                            <img src={item.bookImgUrl} id='bookotherRecImg'/>
                        ):(
                            <img src={default_url} id='bookotherRecImg'/>
                        )}
                    <div id='bookTitleotherRec'>
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
                <div id='otherRecDivMobile'>
                    <div id='otherRecListNameMobile'>
                        {user}님과 비슷한 취향의 유저가 읽은 도서
                    </div>
                    <div id='otherRecList'>
                        {recommList(data)}
                    </div>
                </div>
            ):(
                <div id='otherRecDivWeb'>
                    <div id='otherRecListNameWeb'>
                        {user}님과 비슷한 취향의 유저가 읽은 도서
                    </div>
                    <div id='otherRecList'>
                        {recommList(data)}
                    </div>
                </div>
            )}
        </div>
    )
}

export default OtherRecomm;