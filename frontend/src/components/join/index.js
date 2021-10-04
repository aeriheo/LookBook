import React , {useState} from 'react';
import {useMediaQuery} from 'react-responsive';
import {TextField, Button, Divider, Modal} from '@mui/material';
import {GoogleLogin} from 'react-google-login';
import kakaobtn from '../../images/kakao_login.png';
import Logo from '../logo';
import './style.css';
import {userAPI} from '../../utils/axios';
import {KAKAO_AUTH_URL} from '../../oauth';

const Join = (props) =>{
    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });

    // google clientid
    const clientId = '1052285930140-4qqr208qoiipl1agabhpbq03pvflv5fj.apps.googleusercontent.com';
    
    const [id, setId] = useState('');
    const [pw, setPw] = useState('');
    const [name, setName] = useState('');
    const [nickname, setNickname] = useState('');
    const [pwCheck, setPwCheck] = useState('');
    const [checkEqual, setCheckEqual] = useState('');
    const [nickChk, setnickchk] = useState(false);
    const [emailChk, setEmailchk] = useState(false);
    

    const handleId = (e) =>{
        setId(e.target.value);
    }

    const handlePw = (e) =>{
        setPw(e.target.value);
    }

    const handlePwCheck = (e)=>{
        setPwCheck(e.target.value);

        if (pw === e.target.value) setCheckEqual('일치');
        else setCheckEqual('불일치');
    }

    const handleName = (e) =>{
        setName(e.target.value);
    }

    const handleNickname = (e) =>{
        setNickname(e.target.value);
    }
    
    const chkNickname = async()=>{
        try{
            await userAPI.checkNickName(nickname);
            alert('사용 가능한 닉네임입니다.');
            setnickchk(true);

        }catch(e){
            alert('이미 존재하는 닉네임입니다.');
            setnickchk(false);
        }
    }

    const chkEmail = async()=>{
        try{
            await userAPI.checkEmail(id);
            alert('사용 가능한 이메일입니다.');
            setEmailchk(true);

        }catch(e){
            alert('이미 존재하는 이메일입니다.');
            setEmailchk(false);
        }
    }

    const responseGoogle = async(guser)=>{
        const result = await userAPI.loginGoogle(guser.profileObj.email);
        if (result===false){
            window.location.href='/joinSocial';
        }else{
            window.location.replace('/lookbook');
        }
    }

    const signin = async()=>{
        if(pw==='') alert('비밀번호를 입력해주세요!');
        if(pw!==pwCheck) alert('비밀번호를 확인해주세요!');
        if(emailChk===false) alert('이메일 중복 확인을 해주세요');
        if(nickChk===false) alert('닉네임 중복확인을 해주세요');
        if(emailChk & nickChk & pw===pwCheck & pw.length !== 0){
            await userAPI.join(id, pw, name, nickname)
            .then(alert(`회원가입에 성공했습니다!
            서비스 이용을 위해 평가하기를 진행해주세요.`));
            await userAPI.login(id, pw).then(window.location.replace('/score'));
        }   
    }

    return(
        <div>
        {isMobile?(
            <div className='Mobile'>                
                <Logo/>
                <div id='joinDivMobile'>
                    <div id='joinDivMob'>
                        <div id='joinHeader'>
                            <div id='joinMobile'>
                                회원가입
                            </div>
                        </div>
                        <div>
                            <div id='duplicateDiv'>
                                <TextField placeholder='이메일을 입력해주세요' className='textFieldWeb' value={id} onChange={handleId} style={{marginTop: '2vh'}}/>
                                <Button id='duplicateBtnMobile' onClick={chkEmail}>중복확인</Button>
                            </div>
                            <TextField type='password' placeholder='비밀번호를 입력해주세요' className='textFieldWeb' value={pw} onChange={handlePw} style={{marginTop: '2vh'}}/>
                            <div id='duplicateDiv'>
                                <TextField type='password' placeholder='확인용 비밀번호를 입력해주세요' className='textFieldwithBtnMobile' value={pwCheck} onChange={handlePwCheck} style={{marginTop: '2vh'}}/>
                                <div id='equalpwMobile'>{checkEqual}</div>
                            </div>
                            <TextField placeholder='이름을 입력해주세요' className='textFieldWeb' value={name} onChange={handleName} style={{marginTop: '2vh'}}/>
                            <div id='duplicateDiv'>
                                <TextField placeholder='닉네임을 입력해주세요' className='textFieldwithBtnWeb' value={nickname} onChange={handleNickname} style={{marginTop: '2vh'}}/>
                                <Button id='dupBtnMobile' onClick={chkNickname}>중복확인</Button>
                            </div>
                            <Button id='btnMobile' onClick={signin}>회원가입</Button>
                        </div>
                        <Divider/>
                        <div  className='another'>
                            <div id='wayMobile'>다른 방법으로 회원가입하기</div>
                            <div id='socialWeb'>
                                <GoogleLogin clientId={clientId} buttonText='Login' onSuccess={responseGoogle}/>
                                <div id='kakaoDiv' onClick={()=>{window.location.href = KAKAO_AUTH_URL}}>
                                    <img src={kakaobtn} id='kakaoWay'/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        ):(
            <div className='Web'>
                <div id='logoDiv'>
                    <div id='logoDivcss'>
                        <div id='lookDiv'>Look</div>
                    </div>
                    <div id='logoDivcss'>
                        <div id='bookDiv'>Book</div>
                    </div>
                </div>
                <div id='joinDiv'>
                    <div id='joinDivWeb'>
                        <div id='joinHeader'>
                            <div id='joinWeb'>
                                회원가입
                            </div>
                        </div>
                        <div>
                            <div id='duplicateDiv'>
                                <TextField placeholder='이메일을 입력해주세요' className='textFieldWeb' value={id} onChange={handleId} style={{marginTop: '2vh'}}/>
                                <Button id='duplicateBtn' onClick={chkEmail}>중복확인</Button>
                            </div>
                            <TextField type='password' placeholder='비밀번호를 입력해주세요' className='textFieldWeb' value={pw} onChange={handlePw} style={{marginTop: '2vh'}}/>
                            <div id='duplicateDiv'>
                                <TextField type='password' placeholder='확인용 비밀번호를 입력해주세요' className='textFieldwithBtnWeb' value={pwCheck} onChange={handlePwCheck} style={{marginTop: '2vh'}}/>
                                <div id='equalpwWeb'>{checkEqual}</div>
                            </div>
                            <TextField placeholder='이름을 입력해주세요' className='textFieldWeb' value={name} onChange={handleName} style={{marginTop: '2vh'}}/>
                            <div id='duplicateDiv'>
                                <TextField placeholder='닉네임을 입력해주세요' className='textFieldwithBtnWeb' value={nickname} onChange={handleNickname} style={{marginTop: '2vh'}}/>
                                <Button id='dupBtn' onClick={chkNickname}>중복확인</Button>
                            </div>
                            <Button id='btnWeb' onClick={signin}>회원가입</Button>
                        </div>
                        <Divider/>
                        <div  className='another'>
                            <div id='wayWeb'>다른 방법으로 회원가입하기</div>
                            <div id='socialWeb'>
                                <GoogleLogin clientId={clientId} buttonText='Login' onSuccess={responseGoogle}/>
                                <div id='kakaoDiv' onClick={()=>{window.location.href = KAKAO_AUTH_URL}}>
                                    <img src={kakaobtn} id='kakaoWay'/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )}
        </div>
    )
}

export default Join;