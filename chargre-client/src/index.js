import React from 'react';
import ReactDOM from 'react-dom';
import indexStyle from './index.module.scss';
import { CookiesProvider } from 'react-cookie';
import App from './App';
import { BrowserRouter, Routes } from 'react-router-dom';

ReactDOM.render(
  <CookiesProvider>
    <App />
  </CookiesProvider>,
  document.getElementById('root')
);
