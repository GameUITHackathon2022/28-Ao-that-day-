import { BrowserRouter, Routes, Route, Outlet } from 'react-router-dom';
import { useCookies } from 'react-cookie';

import Introduction from './pages/landing/landing'
import Login from './pages/login/login';
import Register from './pages/register/register'
import PasswordReset from './pages/password-reset/password-reset';
import Error from './pages/error/error';
import DashBoard from './pages/dashboard/dashboard'
import { useState } from 'react';


function App() {

  const [cookies, setCookie, removeCookie] = useCookies(['user']);
  const [accessToken, setAccessToken] = useState('')

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Outlet />}>
          <Route index element={<DashBoard user = {cookies.user} removeCookie = {removeCookie} />} />

          <Route path = 'introduction' element = {<Introduction />} />
          
          <Route path = 'authentication' element = {<Login user = {cookies.user} setCookie = {setCookie} setAccessToken = {setAccessToken}/>} />
          
          <Route path='registration' element = {<Register user = {cookies.user} setCookie = {setCookie}/>} /> 
          
          <Route path='reset-password' element = {<PasswordReset user = {cookies.user} />} />    



          <Route path='*' element = {<Error />} />

        </Route>
      </Routes>

    </BrowserRouter>

  );
}

export default App;
