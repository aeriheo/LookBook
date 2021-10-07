import axios from 'axios';

let request = axios.create({
    baseURL: "https://j5A502.p.ssafy.io/api",
    headers: {
        'Authorization': `Bearer ${window.sessionStorage.getItem('token')}`
    }
});

export const userAPI = {
    login: async (userEmail, userPassword) => {
        return await request.post(`/auth/login`, {
            userEmail, userPassword
        }).then(function (response) {
            window.sessionStorage.setItem('token', response.data.accessToken);
            window.sessionStorage.setItem('refreshToken', response.data.refreshToken);
            return response;
        }).catch(function (err) {
            return err.response;
        })
    },
    userinfo: async () => {
        return await request.get(`/users/me`, {

        }).then(function (response) {
            return response;
        }).catch(function (e) {
            return e.response;
        })
    },
    checkNickName: async (userNickname) => {
        return await request.get(`/users/nickname/${userNickname}`, {
            userNickname
        }).then(function (response) {
            return response;
        })
    },
    join: async (userEmail, userPassword, userName, userNickname, userJoinType) => {
        return await request.post(`/users`, {
            userEmail, userPassword, userName, userNickname, userJoinType
        }).then(function (response) {
            return response;
        })
    },
    checkEmail: async (userEmail) => {
        return await request.get(`users/email/${userEmail}`, {
            userEmail
        }).then(function (response) {
            return response;
        })
    },
    loginGoogle: async (email) => {
        return await request.post(`/google/login`, {
            email
        }).then((response) => {
            if (response.status === 200) {
                window.sessionStorage.setItem('token', response.data.accessToken);
                window.sessionStorage.setItem('refreshToken', response.data.refreshToken);
                return response;
            }
        }).catch((err) => {
            window.sessionStorage.setItem('email', email);
            return false;
        })
    },
    loginKakao: async (code) => {
        return await request.get('/kakao/login', {
            params: {
                code: code
            }
        }).then((response) => {
            return response;
        })
    },
    changeProfile: async (userProfileUrl) => {
        return await request.put('users/profile', {
            userProfileUrl
        })
    },
    updateList: async () => {
        return await request.get('/users/recomm', {

        })
    },
    changeUser: async (userEmail, userPassword, userName, userNickname, userProfileUrl) => {
        return await request.put('/users/me', {
            userEmail, userPassword, userName, userNickname, userProfileUrl
        }).then(response => {
            return response.status;
        })
    },
    checkPw: async (userPassword) => {
        return await request.post('/users/password', {
            userPassword
        }).then((response) => {
            return response.status;
        })
    },
    reissue: async () => {
        return await request.post('auth/reissue', {
            refreshToken: window.sessionStorage.getItem('refreshToken')
        }).then((response) => {
            window.sessionStorage.setItem('token', response.data.accessToken);
            window.sessionStorage.setItem('refreshToken', response.data.refreshToken);
            return response.data;
        }).catch((error) => {
            return error.response;
        })
    },
}

export const bookAPI = {
    detail: async (bookIsbn) => {
        return await request.get(`/books/${bookIsbn}`, {
            bookIsbn
        }).then((response) => {
            return response.data;
        })
    },
    addBookGrade: async (bookIsbn, bookGrade) => {
        return await request.post('/bookgrade', {
            bookIsbn, bookGrade
        });
    },
    modifyBookGrade: async (bookIsbn, bookGrade) => {
        return await request.put('/bookgrade', {
            bookIsbn, bookGrade
        })
    },
    search: async (searchKey, searchWord) => {
        return await request.get('/books', {
            params: {
                searchKey: searchKey,
                searchWord: searchWord
            }
        }).then((response) => {
            return response.data;
        })
    },
    category: async (categoryId) => {
        return await request.get(`/books/category/${categoryId}`, {
            categoryId
        }).then(response => {
            return response.data;
        })
    },
    mainBooks: async () => {
        return await request.get('/books/main', {

        }).then(response => {
            return response.data;
        })
    },
    gradeList: async () => {
        return await request.get('/bookgrade', {

        }).then(response => {
            return response.data;
        })
    }
}

export const reviewAPI = {
    write: async (bookIsbn, reviewContent) => {
        return await request.post('/reviews', {
            bookIsbn, reviewContent
        }).then((response) => {
            return response.data.status;
        })
    },
    like: async (reviewId) => {
        return await request.post(`/reviewlikes/${reviewId}`, {

        })
    },
    likeremove: async (reviewId) => {
        return await request.delete(`/reviewlikes/${reviewId}`, {

        })
    },
    myreviews: async () => {
        return await request.get('/reviews', {

        }).then((response) => {
            return response.data;
        })
    },
    deletemyreivew: async (reviewId) => {
        return await request.delete(`/reviews/${reviewId}`, {

        }).then((response) => {
            return response;
        })
    }
}

export const likeAPI = {
    addlike: async (bookIsbn) => {
        return await request.post(`/likes/${bookIsbn}`, {

        })
    },
    removelike: async (bookIsbn) => {
        return await request.delete(`/likes/${bookIsbn}`, {

        })
    },
    mylikelist: async () => {
        return await request.get('/likes', {

        }).then((response) => {
            return response.data;
        })
    }
}

export const recommendAPI = {
    firstRecomm: async () => {
        return await request.get('/recommends', {

        }).then((response) => {
            return response.data;
        })
    },
    userPredict: async () => {
        return await request.get('/recommends/predict', {

        }).then((response) => {
            return response.data;
        })
    },
    otherPredict: async () => {
        return await request.get('/recommends/user', {

        }).then((response) => {
            return response.data;
        })
    },
    itemBased: async () => {
        return await request.get('/recommends/item', {

        }).then((response) => {
            return response.data;
        })
    }
}

export const libraryAPI = {
    getLibrary: async (bookIsbn, libGugun) => {
        return await request.get(`/libraries`, {
            params: {
                bookIsbn: bookIsbn,
                libGugun: libGugun
            }
        }).then(function (response) {
            return response.data;
        }).catch(function (e) {
        })
    }
}