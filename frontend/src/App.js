import React from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import Main from './pages/main';
import LoginPage from './pages/login';
import JoinPage from './pages/join';
import JoinSocialPage from './pages/joinsocial';
import BookDetailPage from './pages/detail';
import Mypage from './pages/mypage';
import SearchPage from './pages/search';
import CategoryPage from './pages/category';

const App = () =>{

  return (
    <BrowserRouter>
      <Switch>
        <Route exact path = "/" component={LoginPage}/>
        <Route exact path = "/lookbook" component={Main}/>
        <Route exact path = "/join" component={JoinPage}/>
        <Route exact path = "/joinsocial" component={JoinSocialPage}/>
        <Route exact path = "/book/:isbn" component={BookDetailPage}/>
        <Route exact path = "/mypage/:tab" component={Mypage}/>
        <Route exact path = "/search" component={SearchPage}/>
        <Route exact path = "/category" component={CategoryPage}/>
      </Switch>
    </BrowserRouter>
  );
}

export default App;