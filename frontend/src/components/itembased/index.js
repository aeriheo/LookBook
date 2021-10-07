import React, {useState, useLayoutEffect} from 'react';
import {useMediaQuery} from 'react-responsive';
import { useHistory } from "react-router-dom";
import {recommendAPI, bookAPI} from '../../utils/axios';
import default_url from '../../images/default_imgurl.png';
import Logo from '../logo';
import './style.css';

const ItemBased = () =>{
    let history = useHistory();
    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });
    
    const [data, setData] = useState([]);
    const [book, setBook] = useState('');
    const loaditemRecomm = async()=>{
        const result = await recommendAPI.itemBased();
        setData(result.itemBasedCFList);
    }

    useLayoutEffect(()=>{
        async function loadBEST(){
            const result = await bookAPI.mainBooks();
            setBook(result.bestBookList[0].bookTitle);
        }
        loaditemRecomm();
        loadBEST();
    },[]);

    const recommList = (data)=>{
        let result = [];
        {data&&data.map(item=>{
            result = result.concat(
                <div id='bookDivWeb' onClick={()=>history.push(`/book/${item.bookIsbn}`)}>
                    {item.bookImgUrl?(
                            <img src={item.bookImgUrl} id='bookitemRecImg'/>
                        ):(
                            <img src={default_url} id='bookitemRecImg'/>
                        )}
                    <div id='bookTitleitemRec'>
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
                <div id='itemRecDivMobile'>
                    <div id='itemRecListNameMobile'>
                        '{book}'와 비슷한 도서
                    </div>
                    <div id='itemRecList'>
                        {recommList(data)}
                    </div>
                </div>
            ):(
                <div id='itemRecDivWeb'>
                    <div id='itemRecListNameWeb'>
                        '{book}'와 비슷한 도서
                    </div>
                    <div id='itemRecList'>
                        {recommList(data)}
                    </div>
                </div>
            )}
        </div>
    )
}

export default ItemBased;