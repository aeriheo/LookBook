import React , {useState} from 'react';
import {useMediaQuery} from 'react-responsive';
import {TextField, Button, Divider} from '@mui/material';
import {GoogleLogin} from 'react-google-login';
import kakaobtn from '../../images/kakao_login.png';
import Logo from '../logo';
import './style.css';
import {userAPI} from '../../utils/axios';

const Login = () =>{
    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });

    // google clientid
    const clientId = '1052285930140-4qqr208qoiipl1agabhpbq03pvflv5fj.apps.googleusercontent.com';
    
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
                window.location.href='/lookbook';
            }catch(e){
                console.log(e);
                alert('아이디 혹은 비밀번호를 틀리셨습니다.');
                setId('');
                setPw('');
            }
        }
    }

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
                            <div id='forgotMobile'>
                                아이디/비밀번호를 잊어버리셨나요?
                            </div>
                        </div>
                        <div>
                            <TextField placeholder='이메일을 입력해주세요' className='idMobile' value={id} onChange={handleId}  style={{marginTop: '2vh'}}/>
                            <TextField type='password' placeholder='비밀번호를 입력해주세요' className='pwMobile' value={pw} onChange={handlePw}  style={{marginTop: '2vh'}}/>
                            <Button variant='contained' id='btnMobile' onClick={login}>로그인</Button>
                        </div>
                        <div id='goSignInMobile' onClick={() => window.location.replace (`/join`)}>
                            아직 회원이 아니신가요?
                        </div>
                        <Divider/>
                        <div className='another'>
                            <div id='wayMobile'>다른 방법으로 로그인하기</div>
                            <GoogleLogin clientId={clientId} buttonText='Login'/>
                            <div>
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
                            <div id='forgotWeb'>
                                아이디/비밀번호를 잊어버리셨나요?
                            </div>
                        </div>
                        <div>
                            <TextField placeholder='이메일을 입력해주세요' className='idWeb' value={id} onChange={handleId} style={{marginTop: '2vh'}}/>
                            <TextField type='password' placeholder='비밀번호를 입력해주세요' className='pwWeb' value={pw} onChange={handlePw} style={{marginTop: '2vh'}}/>
                            <Button variant='contained' id='btnWeb' onClick={login}>로그인</Button>
                        </div>
                        <div id='goSignInWeb' onClick={() => window.location.replace (`/join`)}>
                            아직 회원이 아니신가요?
                        </div>
                        <Divider/>
                        <div  className='another'>
                            <div id='wayWeb'>다른 방법으로 로그인하기</div>
                            <div style={{display: 'flex'}}> 
                                <GoogleLogin clientId={clientId} buttonText='Login'/>
                                <div style={{marginLeft: '1vw'}}>
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