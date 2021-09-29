import axios from 'axios';

let request = axios.create({
    baseURL: "https://j5A502.p.ssafy.io/api",
    headers:{
        'Authorization' : `Bearer ${window.sessionStorage.getItem('token')}`
    }
});

export const userAPI = {
    login: async (userEmail, userPassword)=>{
        return await request.post(`/auth/login`,{
            userEmail, userPassword
        }).then(function(response) {
            // accessToken은 header에 refreshToken은 sessionStorage에 넣어주기
            window.sessionStorage.setItem('token', response.data.accessToken);
            window.sessionStorage.setItem('refreshToken', response.data.refreshToken);
            return response;
        })
    },
    userinfo:async ()=>{
        return await request.get(`/users/me`,{
            
        }).then(function(response){
            return response;
        }).catch(function(e){
            console.log(e);
        })
    },
    checkNickName:async (userNickname)=>{
        return await request.get(`/users/nickname/${userNickname}`,{
            userNickname
        }).then(function(response){
            return response;
        })
    },
    join:async (userEmail, userPassword, userName, userNickname) => {
        return await request.post(`/users`, {
            userEmail, userPassword, userName, userNickname
        }).then(function(response){
            return response;
        })
    },
    checkEmail:async (userEmail)=>{
        return await request.get(`users/email/${userEmail}`,{
            userEmail
        }).then(function(response){
            return response;
        })
    },
    loginGoogle: async (email)=>{
        return await request.post(`/google/login`,{
            email
        }).then((response)=>{
            if(response.status===200){
                window.sessionStorage.setItem('token', response.data.accessToken);
                window.sessionStorage.setItem('refreshToken', response.data.refreshToken);
                return response;
            }
        }).catch((err)=>{
                console.log(err);
                window.sessionStorage.setItem('email', email);
                return false;
        })
    },
    loginKakao: async(code)=>{
        return await request.get('/kakao/login',{
            params:{
                code: code
            }
        }).then((response)=>{
            return response;
            // console.log(response);
        }).catch((error)=>{
            console.log(error);
        })
    }
}