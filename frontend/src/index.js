import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

// store 넣고 reducer 반영
// import {createStore} from 'redux';
// import {Provider} from 'react-redux';

// const store = createStore(reducer);

ReactDOM.render(
  <React.StrictMode>
  {/* <Provider store={store}> */}
    <App />
  </React.StrictMode>,
  // </Provider>, 
  document.getElementById('root')
);

reportWebVitals();