import React, {useState, useLayoutEffect} from 'react';
import {useMediaQuery} from 'react-responsive';
import { useHistory } from "react-router-dom";
import {userAPI, reviewAPI, likeAPI} from '../../utils/axios';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import ImportContactsIcon from '@mui/icons-material/ImportContacts';
import {Button, Modal} from '@mui/material';
import S3Upload from '../profile';
import PwCheck from '../pwCheck';
import ChangeSocial from '../changeSocial';
import ChangeInfo from '../change';
import './style.css';

const UserInfo = () =>{
    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });
    
    const [data, setData] = useState({});
    const [mylbNum, setmylbNum] = useState(0);
    const [myLikeNum, setmyLikeNum] = useState(0);
    const [open, setOpen] = useState(false);
    const [open2, setOpen2] = useState(false);
    const [open3, setOpen3] = useState(false);
    const [open4, setOpen4] = useState(window.sessionStorage.getItem('pwcheck'));
    const [myProfile, setmyProfile] = useState('');
    const [usertype, setusertype] = useState(0);

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const handleOpen2 = () => setOpen2(true);
    const handleClose2 = () => setOpen2(false);

    const handleOpen3 = () => setOpen3(true);
    const handleClose3 = () => setOpen3(false);

    const handleClose4 = () => setOpen4(false);

    const loadUser = async()=>{
        const result = await userAPI.userinfo();
        setData(result.data);
        setmyProfile(result.data.userProfileUrl);
        setusertype(result.data.userJoinType);
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
                            <Button id='userinfoBtnMobile' onClick={()=>{usertype!==0?handleOpen2():handleOpen3()}}>내 정보 수정하기</Button>
                            <Modal open={open2} onClose={()=>handleClose2()}>
                                <div id='modalDiv'>
                                    <div id='exitModal' onClick={()=>handleClose2()}>X</div>
                                    <div>
                                        <PwCheck/>
                                    </div>
                                </div>
                            </Modal>
                            {/* social */}
                            <Modal open={open3} onClose={()=>handleClose3()}>
                                <div id='modalDiv2'>
                                    <div id='exitModal' onClick={()=>handleClose3()}>X</div>
                                    <div>
                                        <ChangeSocial/>
                                    </div>
                                </div>
                            </Modal>
                            <Modal open={open4} onClose={()=>{handleClose4(); window.sessionStorage.removeItem('pwcheck');}}>
                                <div id='modalDiv3'>
                                    <div id='exitModal' onClick={()=>{handleClose4(); window.sessionStorage.removeItem('pwcheck');}}>X</div>
                                    <div>
                                        <ChangeInfo/>
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
                            <Button id='userinfoBtnWeb' onClick={()=>{usertype!==0?handleOpen2():handleOpen3()}}>내 정보 수정하기</Button>
                            <Modal open={open2} onClose={()=>handleClose2()}>
                                <div id='modalDiv'>
                                    <div id='exitModal' onClick={()=>handleClose2()}>X</div>
                                    <div>
                                        <PwCheck/>
                                    </div>
                                </div>
                            </Modal>
                            {/* social */}
                            <Modal open={open3} onClose={()=>handleClose3()}>
                                <div id='modalDiv2'>
                                    <div id='exitModal' onClick={()=>handleClose3()}>X</div>
                                    <div>
                                        <ChangeSocial/>
                                    </div>
                                </div>
                            </Modal>
                            {/* 일반유저 */}
                            <Modal open={open4} onClose={()=>{handleClose4(); window.sessionStorage.removeItem('pwcheck');}}>
                                <div id='modalDiv3'>
                                    <div id='exitModal' onClick={()=>{handleClose4(); window.sessionStorage.removeItem('pwcheck');}}>X</div>
                                    <div>
                                        <ChangeInfo/>
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