import React from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import Main from './pages/main';
import LoginPage from './pages/login';
import JoinPage from './pages/join';
import JoinSocialPage from './pages/joinsocial';
import BookDetailPage from './pages/detail';

const App = () =>{

  return (
    <BrowserRouter>
      <Switch>
        <Route exact path = "/" component={LoginPage}/>
        <Route exact path = "/lookbook" component={Main}/>
        <Route exact path = "/join" component={JoinPage}/>
        <Route exact path = "/joinsocial" component={JoinSocialPage}/>
        <Route exact path = "/book/:isbn" component={BookDetailPage}/>
      </Switch>
    </BrowserRouter>
  );
}

export default App;