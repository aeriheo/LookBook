import React, {useState} from 'react';
import {userAPI} from '../../utils/axios';
import {TextField, Button} from '@mui/material';
import './style.css';

const PwCheck = () =>{
    const [pw, setPw] = useState('');
    const handlePw = (e) =>{
        setPw(e.target.value);
    }
    const checkPassword = async()=>{
        const result = await userAPI.checkPw(pw);
        if(result===200) {
            window.sessionStorage.setItem('pwcheck', true);
            window.location.href='/mypage/mypage';
        }
    }

    return(
        <div id='pwCheckDiv'>
            <div id='pwCheckText'>회원정보 수정</div>
            <div id='pwCheckMsg'>비밀번호 확인</div>
            <TextField type='password' placeholder='비밀번호를 입력해주세요' value={pw} onChange={handlePw} id='pwCheckTF' style={{marginTop: '15px', marginLeft:'20px'}}/>
            <Button id='pwCheckBtn' onClick={()=>checkPassword()}>확인</Button>
        </div>
    )
}

export default PwCheck;