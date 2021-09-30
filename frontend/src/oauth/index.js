const CLIENT_ID = 'a765ac439be73b3505f709a713a0dcd0';
// const REDIRECT_URI = 'http://localhost:3000/joinsocial';
const REDIRECT_URI = 'https://j5A502.p.ssafy.io/joinsocial';

export const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;