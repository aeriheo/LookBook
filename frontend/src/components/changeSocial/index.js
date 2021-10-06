import React, {useState, useLayoutEffect} from 'react';
import {userAPI} from '../../utils/axios';
import {TextField, Button} from '@mui/material';
import './style.css';

const ChangeSocial = () =>{
    const [email, setEmail] = useState('');
    const [name, setName] = useState('');
    const [nickname, setNickname] = useState('');
    const [profile, setProfile] = useState('');

    const [change, setChange] = useState(false);

    const handleName = (e) =>{
        setName(e.target.value);
    }

    const handleNickname = (e) =>{
        setNickname(e.target.value);
        setChange(true);
    }

    const loadUser = async()=>{
        const result = await userAPI.userinfo();
        setEmail(result.data.userEmail);
        setName(result.data.userName);
        setNickname(result.data.userNickname);
        setProfile(result.data.userProfileUrl);
    }

    const checkNickname = async()=>{
        if (change){
            try{
                await userAPI.checkNickName(nickname);
                alert('사용 가능한 닉네임입니다.');
    
            }catch(e){
                alert('이미 존재하는 닉네임입니다.');
            }
        }else{
            alert('현재 닉네임입니다.');
        }
    }

    const changeUser = async()=>{
        const result = await userAPI.changeUser(email, '', name, nickname, profile);
        if(result===200) {
            alert('수정에 성공했습니다.');
            window.location.href='/mypage/mypage';
        }
    }

    useLayoutEffect(()=>{
        loadUser();
    },[]);

    return(
        <div id='SocialInfoDiv'>
            <div id='SocialText'>회원정보 수정</div>
            <div id='SocialMsg'>아이디</div>
            <TextField disabled value={email} style={{marginTop: '15px', marginLeft:'20px', marginBottom:'15px'}} id='SocialTF'/>
            <div id='SocialMsg'>이름</div>
            <TextField value={name} onChange={handleName} style={{marginTop: '15px', marginLeft:'20px', marginBottom:'15px'}} id='SocialTF'/>
            <div id='SocialNickLabel'>
                <div style={{fontWeight:'bold'}}>
                    닉네임
                </div>
                <div style={{fontSize:'15px', cursor: 'pointer'}} onClick={()=>checkNickname()}>중복 확인</div>
            </div>
            <TextField value={nickname} onChange={handleNickname} style={{marginTop: '15px', marginLeft:'20px'}} id='SocialTF'/>
            <Button id='SocialBtn' onClick={()=>changeUser()}>수정</Button>
        </div>
    )
}

export default ChangeSocial;