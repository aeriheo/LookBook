import React, {useState} from 'react';
import {useMediaQuery} from 'react-responsive';
import { useHistory } from "react-router-dom";
import {Select, MenuItem, TextField, IconButton} from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import {bookAPI} from '../../utils/axios';
import default_url from '../../images/default_imgurl.png';
import './style.css';

const Search = () =>{
    let history = useHistory();
    const [category, setCategory] = useState('001');
    const [word, setWord] = useState('');
    const [data, setData] = useState({});
    const [query, setQuery] = useState(false);
    const [searchWord, setSearchWord] = useState('');

    const handleChange = (e)=>{
        setCategory(e.target.value);
    }

    const handleWord = (e)=>{
        setWord(e.target.value);
    }    

    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });

    const searchBook = async()=>{
        try{
            const result = await bookAPI.search(category, word);
            setData(result.searchBookList);
            setQuery(true);
            setSearchWord(word);
        }catch(e){
        }
    }

    const bookListMobile = (books)=>{
        let result = [];
        if(books.length > 0){
            books.map(item=>{
                result = result.concat(
                    <div onClick={()=>history.push(`/book/${item.bookIsbn}`)} id='searchbookListDivMobile'>
                        {item.bookImgUrl?(
                            <img src={item.bookImgUrl} id='searchbookImg'/>
                        ):(
                            <img src={default_url} id='searchbookImg'/>
                        )}
                        <div id='searchbookTitle'>{item.bookTitle}</div>
                    </div>
                )
            })
        }else{
            result = result.concat(
                <div>
                    해당되는 도서가 없습니다.
                </div>
            );
        }
        return result;
    }

    const bookList = (books)=>{
        let result = [];
        if(books.length > 0){
            books.map(item=>{
                result = result.concat(
                    <div onClick={()=>history.push(`/book/${item.bookIsbn}`)} id='searchbookListDivWeb'>
                        {item.bookImgUrl?(
                            <img src={item.bookImgUrl} id='searchbookImg'/>
                        ):(
                            <img src={default_url} id='searchbookImg'/>
                        )}
                        <div id='searchbookTitle'>{item.bookTitle}</div>
                    </div>
                )
            })
        }else{
            result = result.concat(
                <div>
                    해당되는 도서가 없습니다.
                </div>
            );
        }
        return result;
    }

    return(
        <div>
            {isMobile?(
                <div>
                    <div id='searchFormDivMobile'>
                        <Select value={category} onChange={handleChange} variant="standard" style={{marginRight:'1vw'}}>
                            <MenuItem value={'001'}>전체</MenuItem>
                            <MenuItem value={'002'}>도서명</MenuItem>
                            <MenuItem value={'003'}>저자명</MenuItem>
                        </Select>
                        <TextField value={word} onChange={handleWord} variant="standard" style={{width:'30vw'}}/>
                        <IconButton onClick={()=>searchBook()}>
                            <SearchIcon/>
                        </IconButton>
                    </div>
                    {query?(
                        <div>
                            <div id='searchResultMobile'>{searchWord}을(를) 검색한 결과입니다.</div>
                            <div id='searchResultList'>
                                {bookListMobile(data)}
                            </div>
                        </div>
                    ):null}
                </div>
            ):(
                <div>
                    <div id='searchFormDivWeb'>
                        <Select value={category} onChange={handleChange} variant="standard" style={{marginRight:'1vw'}}>
                            <MenuItem value={'001'}>전체</MenuItem>
                            <MenuItem value={'002'}>도서명</MenuItem>
                            <MenuItem value={'003'}>저자명</MenuItem>
                        </Select>
                        <TextField value={word} onChange={handleWord} variant="standard" style={{width:'15vw'}}/>
                        <IconButton onClick={()=>searchBook()}>
                            <SearchIcon style={{width:'2vw', height:'2vw'}}/>
                        </IconButton>
                    </div>
                    {query?(
                        <div>
                            <div id='searchResultWeb'>{searchWord}을(를) 검색한 결과입니다.</div>
                            <div id='searchResultList'>
                                {bookList(data)}
                            </div>
                        </div>
                    ):null}
                </div>
            )}
        </div>
    )
}

export default Search;