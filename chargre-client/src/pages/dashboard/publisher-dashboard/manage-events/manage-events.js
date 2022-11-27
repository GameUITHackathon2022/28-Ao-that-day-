import { useState } from 'react'

import privateStyle from './manage-events.module.scss'
import indexStyle from '../../../../index.module.scss'

const ManageEvents = ({ user }) => {

     const [route, setRoute] = useState('my-events')
     const handleRouteChange = (event) => {
          let nextRoute = event.target.innerHTML || event.target.parentElement.innerText
          console.log(nextRoute)
          switch (nextRoute) {
               case 'My events':
                    setRoute('my-events')
                    break
               case 'Pending registered users':
                    setRoute('pending-registered-users')
                    break
               case 'Pending quality evaluated attendants':
                    setRoute('pending-quality-evaluated-users')
                    break
               default:
                    break
          }
     }

     return (
          <div className={indexStyle.container} >
               <div className={privateStyle.nav}>
                    { (route === 'my-events') && <p className = {`${privateStyle['active-route']}`} onClick={handleRouteChange}>My events</p>}
                    { (route !== 'my-events') && <p onClick={handleRouteChange}>My events</p>}

                    { (route === 'pending-registered-users') && <p className = {`${privateStyle['active-route']}`} onClick={handleRouteChange}>Pending registered users</p>}
                    { (route !== 'pending-registered-users') && <p onClick={handleRouteChange}>Pending registered users</p>}
                    
                    { (route === 'pending-quality-evaluated-users') && <p className = {`${privateStyle['active-route']}`} onClick={handleRouteChange}>Pending quality evaluated attendants</p>}
                    { (route !== 'pending-quality-evaluated-users') && <p onClick={handleRouteChange}>Pending quality evaluated attendants</p>}

               </div>
          </div>
     )

}

export default ManageEvents