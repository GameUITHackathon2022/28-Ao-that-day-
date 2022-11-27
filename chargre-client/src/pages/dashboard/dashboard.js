import { useState, useEffect } from 'react';
import { Navigate } from 'react-router-dom';

import UserDashBoard from './user-dashboard/user-dashboard'
import PublisherDashBoard from './publisher-dashboard/publisher-dashboard'

const DashBoard = ({ user, removeCookie }) => {
     if (!user) 
          return <Navigate to={'/authentication'} />
     else return (
          <>
               {(user.role === 'OrdinaryUser') && <UserDashBoard user={user} removeCookie = {removeCookie} />}
               {(user.role === 'Publisher') && <PublisherDashBoard user={user} removeCookie = {removeCookie} />}
          </>
     )

     // return <UserDashBoard user={user} removeCookie = {removeCookie} />
}

export default DashBoard