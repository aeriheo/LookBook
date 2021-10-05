import React , {useState, useEffect} from 'react';
import {useMediaQuery} from 'react-responsive';
import {TextField, Button, Divider} from '@mui/material';
import {GoogleLogin} from 'react-google-login';
import kakaobtn from '../../images/kakao_login.png';
import Logo from '../logo';
import './style.css';
import {userAPI} from '../../utils/axios';
import {KAKAO_AUTH_URL, GOOGLE_CLIENT_ID} from '../../oauth';
import { useHistory } from "react-router-dom";

const Login = () =>{
    let history = useHistory();
    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });
    
    const [id, setId] = useState('');
    const [pw, setPw] = useState('');

    const handleId = (e) =>{
        setId(e.target.value);
    }

    const handlePw = (e) =>{
        setPw(e.target.value);
    }

    const login = async () =>{
        if (id==='') alert('아이디를 입력해주세요.');
        if (pw ==='') alert('비밀번호를 입력해주세요.');
        if(id!=='' && pw !== '') {
            try{
                await userAPI.login(id, pw);
                window.location.replace('/lookbook');
            }catch(e){
                alert('아이디 혹은 비밀번호를 틀리셨습니다.');
                setId('');
                setPw('');
            }
        }
    }

    const responseGoogle = async(guser)=>{
        const result = await userAPI.loginGoogle(guser.profileObj.email);
        if (result===false){
            alert(`회원이 아닙니다.
            회원가입 페이지로 이동합니다.`);
            window.location.href=`/joinSocial`;
        }else{
            window.location.replace('/lookbook');
        }
    }

    useEffect(()=>{
        if(window.sessionStorage.getItem('token')!==null){
            alert('로그아웃됩니다.');
            window.sessionStorage.removeItem('token');
            window.sessionStorage.removeItem('refreshToken');
        }
    })

    return(
        <div>
        {isMobile?(
            <div>                
                <Logo/>
                <div id='mobileDiv'>
                    <div id='loginDivMobile'>
                        <div id='loginHeaderMobile'>
                            <div id='loginMobile'>
                                로그인
                            </div>
                        </div>
                        <div>
                            <TextField placeholder='이메일을 입력해주세요' className='idMobile' value={id} onChange={handleId}  style={{marginTop: '2vh'}}/>
                            <TextField type='password' placeholder='비밀번호를 입력해주세요' className='pwMobile' value={pw} onChange={handlePw}  style={{marginTop: '2vh'}}/>
                            <Button variant='contained' id='btnMobile' onClick={login}>로그인</Button>
                        </div>
                        <div id='goSignInMobile' onClick={() => history.push(`/join`)}>
                            아직 회원이 아니신가요?
                        </div>
                        <Divider/>
                        <div className='another'>
                            <div id='wayMobile'>다른 방법으로 로그인하기</div>
                            <GoogleLogin clientId={GOOGLE_CLIENT_ID} buttonText='Login' onSuccess={responseGoogle}/>
                            <div  onClick={()=>{window.location.href = KAKAO_AUTH_URL}}>
                                <img src={kakaobtn} id='kakaoWay'/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        ):(
            <div id='Web'>
                <div id='loginDiv'>
                    <div id='loginDivWeb'>
                        <div id='loginHeaderWeb'>
                            <div id='loginWeb'>
                                로그인
                            </div>
                        </div>
                        <div>
                            <TextField placeholder='이메일을 입력해주세요' className='idWeb' value={id} onChange={handleId} style={{marginTop: '2vh'}}/>
                            <TextField type='password' placeholder='비밀번호를 입력해주세요' className='pwWeb' value={pw} onChange={handlePw} style={{marginTop: '2vh'}}/>
                            <Button variant='contained' id='btnWeb' onClick={login}>로그인</Button>
                        </div>
                        <div id='goSignInWeb' onClick={() => history.push(`/join`)}>
                            아직 회원이 아니신가요?
                        </div>
                        <Divider/>
                        <div  className='another'>
                            <div id='wayWeb'>다른 방법으로 로그인하기</div>
                            <div style={{display: 'flex'}}> 
                                <GoogleLogin clientId={GOOGLE_CLIENT_ID} buttonText='Login' onSuccess={responseGoogle}/>
                                <div style={{marginLeft: '1vw'}} onClick={()=>{window.location.href = KAKAO_AUTH_URL}}>
                                    <img src={kakaobtn} id='kakaoWay'/>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                </div>
                <div id='footer'>Look Book</div>
            </div>
        )}
        </div>
    )
}

export default Login;