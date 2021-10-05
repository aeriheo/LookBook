import React, {useState, useLayoutEffect} from 'react';
import {useMediaQuery} from 'react-responsive';
import { useHistory } from "react-router-dom";
import {Select, MenuItem, IconButton} from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import {bookAPI} from '../../utils/axios';
import default_url from '../../images/default_imgurl.png';
import './style.css';

const Category = () =>{
    let history = useHistory();
    const [category, setCategory] = useState('1');
    const [data, setData] = useState({});
    const [query, setQuery] = useState(false);

    const handleChange = (e)=>{
        setCategory(e.target.value);
    }

    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });

    const categoryBook = async()=>{
        try{
            const result = await bookAPI.category(category);
            setData(result.categoryList);
            setQuery(true);
        }catch(e){
            
        }
    }

    useLayoutEffect (()=>{
        categoryBook();
    },[]);

    const bookListMobile = (books)=>{
        let result = [];
        if(books.length > 0){
            books.map(item=>{
                result = result.concat(
                    <div onClick={()=>history.push(`/book/${item.bookIsbn}`)} id='categorybookListDivMobile'>
                        <div style={{height:'20vh'}}>
                            {item.bookImgUrl?(
                                <img src={item.bookImgUrl} id='categorybookImg'/>
                            ):(
                                <img src={default_url} id='categorybookImg'/>
                            )}
                        </div>
                        <div id='categorybookTitle'>{item.bookTitle}</div>
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
                    <div onClick={()=>history.push(`/book/${item.bookIsbn}`)} id='categorybookListDivWeb'>
                        {item.bookImgUrl?(
                            <img src={item.bookImgUrl} id='categorybookImg'/>
                        ):(
                            <img src={default_url} id='categorybookImg'/>
                        )}
                        <div id='categorybookTitleWeb'>
                            <div id='categorybookTitleFont'>
                                {item.bookTitle}
                            </div>
                        </div>
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
                    <div id='categoryFormDivMobile'>
                        <div id='categoryNameMobile'>카테고리별로 보고싶은 작품을 찾아보세요</div>
                        <div>
                            <Select value={category} onChange={handleChange} variant="standard" style={{marginRight:'1vw'}}>
                                <MenuItem value={'1'}>소설</MenuItem>
                                <MenuItem value={'2'}>시/에세이</MenuItem>
                                <MenuItem value={'3'}>인문</MenuItem>
                                <MenuItem value={'4'}>역사/문학</MenuItem>
                                <MenuItem value={'5'}>경제/경영</MenuItem>
                                <MenuItem value={'6'}>유아/아동</MenuItem>
                                <MenuItem value={'7'}>청소년</MenuItem>
                                <MenuItem value={'8'}>국어/외국어/사전</MenuItem>
                                <MenuItem value={'9'}>사회과학</MenuItem>
                                <MenuItem value={'10'}>자연/과학</MenuItem>
                                <MenuItem value={'11'}>컴퓨터/IT</MenuItem>
                                <MenuItem value={'12'}>기술/공학</MenuItem>
                                <MenuItem value={'13'}>예술/대중문화</MenuItem>
                                <MenuItem value={'14'}>종교</MenuItem>
                                <MenuItem value={'15'}>여행</MenuItem>
                                <MenuItem value={'16'}>요리</MenuItem>
                                <MenuItem value={'17'}>건강/뷰티</MenuItem>
                                <MenuItem value={'18'}>취미/실용/스포츠</MenuItem>
                            </Select>
                            <IconButton onClick={()=>categoryBook()}>
                                <SearchIcon/>
                            </IconButton>
                        </div>
                    </div>
                    {query?(
                        <div>
                            <div id='categoryResultList'>
                                {bookListMobile(data)}
                            </div>
                        </div>
                    ):null}
                </div>
            ):(
                <div>
                    <div id='categoryFormDivWeb'>
                        <div id='categoryNameWeb'>카테고리별로 보고싶은 작품을 찾아보세요</div>
                        <div>
                            <Select value={category} onChange={handleChange} variant="standard" style={{marginRight:'1vw', width:'15vw', fontSize:'1.5vw'}}>
                                <MenuItem value={'1'}>소설</MenuItem>
                                <MenuItem value={'2'}>시/에세이</MenuItem>
                                <MenuItem value={'3'}>인문</MenuItem>
                                <MenuItem value={'4'}>역사/문학</MenuItem>
                                <MenuItem value={'5'}>경제/경영</MenuItem>
                                <MenuItem value={'6'}>유아/아동</MenuItem>
                                <MenuItem value={'7'}>청소년</MenuItem>
                                <MenuItem value={'8'}>국어/외국어/사전</MenuItem>
                                <MenuItem value={'9'}>사회과학</MenuItem>
                                <MenuItem value={'10'}>자연/과학</MenuItem>
                                <MenuItem value={'11'}>컴퓨터/IT</MenuItem>
                                <MenuItem value={'12'}>기술/공학</MenuItem>
                                <MenuItem value={'13'}>예술/대중문화</MenuItem>
                                <MenuItem value={'14'}>종교</MenuItem>
                                <MenuItem value={'15'}>여행</MenuItem>
                                <MenuItem value={'16'}>요리</MenuItem>
                                <MenuItem value={'17'}>건강/뷰티</MenuItem>
                                <MenuItem value={'18'}>취미/실용/스포츠</MenuItem>
                            </Select>
                            <IconButton onClick={()=>categoryBook()} >
                                <SearchIcon style={{width:'3vw', height:'3vw'}}/>
                            </IconButton>
                        </div>
                    </div>
                    {query?(
                        <div>
                            <div id='categoryResultList'>
                                {bookList(data)}
                            </div>
                        </div>
                    ):null}
                </div>
            )}
        </div>
    )
}

export default Category;