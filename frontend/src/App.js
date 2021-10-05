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
import ScorePage from './pages/score';
import userBasedPage from './pages/userRecomm';
import otherbasedPage from './pages/otherRecomm';
import itembasedPage from './pages/itemRecomm';
import Library from './pages/library';

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
        <Route exact path = "/score" component={ScorePage}/>
        <Route exact path = "/myrecommend" component={userBasedPage}/>
        <Route exact path = "/otherrecommend" component={otherbasedPage}/>
        <Route exact path = "/itemrecommend" component={itembasedPage}/>
        <Route exact path = "/library/:isbn" component={Library}/>
      </Switch>
    </BrowserRouter>
  );
}

export default App;