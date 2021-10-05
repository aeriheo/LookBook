import React, {useState, useLayoutEffect} from 'react';
import {userAPI} from '../../utils/axios';
import {TextField, Button} from '@mui/material';
import './style.css';

const ChangeInfo = () =>{
    const [email, setEmail] = useState('');
    const [name, setName] = useState('');
    const [nickname, setNickname] = useState('');
    const [profile, setProfile] = useState('');
    const [pw, setPw] = useState('');
    const [pwChk, setPwChk] = useState('');
    const [checkMsg, setCheckMsg] = useState('');

    const [change, setChange] = useState(false);
    const [change2, setChange2] = useState(false);

    const handleName = (e) =>{
        setName(e.target.value);
    }

    const handleNickname = (e) =>{
        setNickname(e.target.value);
        setChange(true);
    }

    const handlePw = (e) =>{
        setPw(e.target.value);
    }

    const handlePwChk = (e) =>{
        setPwChk(e.target.value);
        setChange2(true);
        if (pw === e.target.value) setCheckMsg('일치합니다');
        else setCheckMsg('일치하지 않습니다');
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
        if (pw==='') alert('비밀번호를 입력해주세요');
        if (change2===true && pw!==pwChk) alert('비밀번호를 확인해주세요.');
        if ((pw!=='' && change2===true && pw===pwChk) || (pw!=='' && change2===false)){
            const result = await userAPI.changeUser(email, pw, name, nickname, profile);
            if(result===200) {
                alert('수정에 성공했습니다.');
                window.sessionStorage.removeItem('pwcheck');
                window.location.href='/mypage/mypage';
            }   
        }
    }

    useLayoutEffect(()=>{
        loadUser();
    },[]);

    return(
        <div id='changeInfoDiv'>
            <div id='ChangeText'>회원정보 수정</div>
            <div id='ChangeMsg'>아이디</div>
            <TextField disabled value={email} style={{marginTop: '15px', marginLeft:'20px', marginBottom:'15px'}} id='ChangeTF'/>
            <div id='ChangeMsg'>이름</div>
            <TextField value={name} onChange={handleName} style={{marginTop: '15px', marginLeft:'20px', marginBottom:'15px'}} id='ChangeTF'/>
            <div id='ChangeNickLabel'>
                <div style={{fontWeight:'bold'}}>
                    닉네임
                </div>
                <div style={{fontSize:'15px', cursor: 'pointer'}} onClick={()=>checkNickname()}>중복 확인</div>
            </div>
            <TextField value={nickname} onChange={handleNickname} style={{marginTop: '15px', marginLeft:'20px', marginBottom:'15px'}} id='ChangeTF'/>
            <div id='ChangeMsg'>비밀번호</div>
            <TextField type='password' placeholder='비밀번호를 입력해주세요' value={pw} onChange={handlePw} style={{marginTop: '15px', marginLeft:'20px', marginBottom:'15px'}} id='ChangeTF'/>
            <div id='ChangeMsg'>비밀번호 확인</div>
            <TextField type='password' placeholder='비밀번호를 입력해주세요' value={pwChk} onChange={handlePwChk} style={{marginTop: '15px', marginLeft:'20px', marginBottom:'15px'}} id='ChangeTF'/>
            <div id='ChangeMsg' style={{fontSize:'15px'}}>{checkMsg}</div>
            <Button id='ChangeBtn' onClick={()=>changeUser()}>수정</Button>
        </div>
    )
}

export default ChangeInfo;