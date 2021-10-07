import React, { useState, useEffect } from 'react';
import { useMediaQuery } from 'react-responsive';
import { TextField, Button } from '@mui/material';
import Logo from '../logo';
import './style.css';
import { userAPI } from '../../utils/axios';


const JoinSocial = (props) => {
    const [isKakao, setisKakao] = useState(0);
    const [id, setId] = useState(window.sessionStorage.getItem('email'));

    useEffect(() => {
        // kakaologin
        async function loadUser() {
            if (new URL(window.location.href).searchParams.get('code')) {
                setisKakao(1);
                const result = await userAPI.loginKakao(new URL(window.location.href).searchParams.get('code'));
                if (result.data.actionCode === true) setId(result.data.email);
                else if (result.data.actionCode === false) {
                    // login access token
                    window.sessionStorage.setItem('token', result.data.accessToken);
                    window.sessionStorage.setItem('refreshToken', result.data.refreshToken);
                    window.location.replace('/lookbook');
                }
            }
        }

        loadUser();
    }, []);

    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });


    const [name, setName] = useState('');
    const [nickname, setNickname] = useState('');
    const [nickChk, setnickchk] = useState(false);

    const handleName = (e) => {
        setName(e.target.value);
    }

    const handleNickname = (e) => {
        setNickname(e.target.value);
    }

    const chkNickname = async () => {
        try {
            await userAPI.checkNickName(nickname);
            alert('사용 가능한 닉네임입니다.');
            setnickchk(true);

        } catch (e) {
            alert('이미 존재하는 닉네임입니다.');
            setnickchk(false);
        }
    }

    const signin = async () => {
        if (nickChk === false) alert('닉네임 중복확인을 해주세요');
        if (nickChk) {
            await userAPI.join(id, '', name, nickname, 0);
            window.sessionStorage.removeItem('email');
            alert(`회원가입에 성공했습니다!`);
            alert('로그인을 진행해주세요.');
            window.location.replace('/');
        }
    }

    return (
        <div>
            {isMobile ? (
                <div className='Mobile'>
                    <Logo />
                    <div id='joinDivMobile'>
                        <div id='joinDivMob'>
                            <div id='joinHeader'>
                                <div id='joinMobile'>
                                    회원가입
                                </div>
                            </div>
                            <div>
                                <TextField disabled placeholder='이메일을 입력해주세요' className='textFieldWeb' value={id} style={{ marginTop: '2vh' }} />
                                <TextField placeholder='이름을 입력해주세요' className='textFieldWeb' value={name} onChange={handleName} style={{ marginTop: '2vh' }} />
                                <div id='duplicateDiv'>
                                    <TextField placeholder='닉네임을 입력해주세요' className='textFieldwithBtnWeb' value={nickname} onChange={handleNickname} style={{ marginTop: '2vh' }} />
                                    <Button id='dupBtnMobile' onClick={chkNickname}>중복확인</Button>
                                </div>
                                <Button id='btnMobile' onClick={signin}>회원가입</Button>
                            </div>
                        </div>
                    </div>
                </div>
            ) : (
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
                                <TextField disabled placeholder='이메일을 입력해주세요' className='textFieldWeb' value={id} style={{ marginTop: '2vh' }} />
                                <TextField placeholder='이름을 입력해주세요' className='textFieldWeb' value={name} onChange={handleName} style={{ marginTop: '2vh' }} />
                                <div id='duplicateDiv'>
                                    <TextField placeholder='닉네임을 입력해주세요' className='textFieldwithBtnWeb' value={nickname} onChange={handleNickname} style={{ marginTop: '2vh' }} />
                                    <Button id='dupBtn' onClick={chkNickname}>중복확인</Button>
                                </div>
                                <Button id='btnWeb' onClick={signin}>회원가입</Button>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </div>
    )
}

export default JoinSocial;