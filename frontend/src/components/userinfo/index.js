import React, {useState, useLayoutEffect} from 'react';
import {useMediaQuery} from 'react-responsive';
import { useHistory } from "react-router-dom";
import {userAPI, reviewAPI, likeAPI} from '../../utils/axios';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import ImportContactsIcon from '@mui/icons-material/ImportContacts';
import {Button, Modal} from '@mui/material';
import S3Upload from '../profile';
import './style.css';

const UserInfo = () =>{
    let history = useHistory();
    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });
    
    const [data, setData] = useState({});
    const [mylbNum, setmylbNum] = useState(0);
    const [myLikeNum, setmyLikeNum] = useState(0);
    const [open, setOpen] = useState(false);
    const [myProfile, setmyProfile] = useState('');

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const loadUser = async()=>{
        const result = await userAPI.userinfo();
        console.log(result);
        setData(result.data);
        setmyProfile(result.data.userProfileUrl);
    }

    const likenum = async()=>{
        let result = await likeAPI.mylikelist();
        setmyLikeNum(result.likeBookList.length);
    }

    const lbnum = async()=>{
        let result = await reviewAPI.myreviews();
        setmylbNum(result.UserReviewList.length);
    }

    useLayoutEffect(()=>{
        loadUser();
        likenum();
        lbnum();
    },[]);

    return(
        <div>
            {isMobile ? (
                <div id='userinfoDiv'>
                    <div id='userinfoMobile'>
                        <div id='userinfoProfileDiv'>
                            <div id='userinfoProfileImgDivMobile'>
                            {myProfile?(
                                <img src={`https://pjt2-lookbook.s3.ap-northeast-2.amazonaws.com/profile/${data.userProfileUrl}`} id='userinfoProfileImg'/>
                            ):null}
                            </div>
                        </div>
                        <div id='userinfodataDiv'>
                            <div id='userinfoNickMobile'>
                                {data.userNickname}
                            </div>
                            <div id='userinfoLIKELBMobile'>
                                <div id='userinfoDefaultDiv'>
                                    <FavoriteBorderIcon id='userinfoLikeMobile'/>
                                    <div id='userinfoDefaultNumMobile'>{myLikeNum}</div>
                                </div>
                                <div id='userinfoDefaultDiv'>
                                    <ImportContactsIcon id='userinfoLBMobile'/>
                                    <div id='userinfoDefaultNumMobile'>{mylbNum}</div>
                                </div>
                            </div>
                            <Button id='userinfoBtnMobile' onClick={()=>handleOpen()}>내 프로필 사진 수정하기</Button>
                            <Modal open={open} onClose={()=>handleClose()}>
                                <div id='modalDiv'>
                                    <div id='modalDetailDiv'>
                                        <div id='modalDetail'>이미지 파일(jpg, png)만 업로드 가능합니다.</div>
                                        <div id='modalLeft'>
                                            <S3Upload/>
                                        </div>
                                    </div>
                                </div>
                            </Modal>
                        </div>
                    </div>
                </div>
            ):(
                <div id='userinfoDiv'>
                    <div id='userinfoNameWeb'>
                        MY PAGE
                    </div>
                    <div id='userinfoWeb'>
                        <div id='userinfoProfileDiv'>
                            <div id='userinfoProfileImgDivWeb'>
                                {myProfile?(
                                    <img src={`https://pjt2-lookbook.s3.ap-northeast-2.amazonaws.com/profile/${data.userProfileUrl}`} id='userinfoProfileImg'/>
                                ):null}
                            </div>
                        </div>
                        <div id='userinfodataDiv'>
                            <div id='userinfoNickWeb'>
                                {data.userNickname}
                            </div>
                            <div id='userinfoLIKELBWeb'>
                                <div id='userinfoDefaultDiv'>
                                    <FavoriteBorderIcon id='userinfoLikeWeb'/>
                                    <div id='userinfoDefaultNumWeb'>{myLikeNum}</div>
                                </div>
                                <div id='userinfoDefaultDiv'>
                                    <ImportContactsIcon id='userinfoLBWeb'/>
                                    <div id='userinfoDefaultNumWeb'>{mylbNum}</div>
                                </div>
                            </div>
                            <Button id='userinfoBtnWeb' onClick={()=>handleOpen()}>내 프로필 사진 수정하기</Button>
                            <Modal open={open} onClose={()=>handleClose()}>
                                <div id='modalDiv'>
                                    <div id='modalDetailDiv'>
                                        <div id='modalDetail'>이미지 파일(jpg, png)만 업로드 가능합니다.</div>
                                        <div id='modalLeft'>
                                            <S3Upload/>
                                        </div>
                                    </div>
                                </div>
                            </Modal>
                        </div>
                    </div>
                </div>
            )}
        </div>
    )
}

export default UserInfo;