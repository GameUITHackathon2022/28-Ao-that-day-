import { useState } from "react";
import { Link } from 'react-router-dom'


import AvailableEvents from './available-events/available-events'
import EnrolledEvents from "./enrolled-events/enrolled-events";
import ManageRewards from "./manage-rewards/manage-rewards"

import privateStyle from './user-dashboard.module.scss'
import indexStyle from '../../../index.module.scss'


const UserDashboard = ({ user, removeCookie }) => {

  const [profileMenu, setProfileMenu] = useState(false)

  const [route, setRoute] = useState('available-events')

  const dropdown = <div className={indexStyle.dropdown}>
    <p> View complete profile </p>
    <Link to='/' className={indexStyle.link} onClick={() => removeCookie('user')}> Log out </Link>
  </div>

  const handleRouteChange = (event) => {
    let nextRoute = event.target.innerHTML || event.target.parentElement.innerText

    switch (nextRoute) {
      case 'Available events':
        setRoute('available-events')
        break
      case 'Enrolled events':
        setRoute('enrolled-events')
        break
      case 'Rewards management':
        setRoute('manage-rewards')
        break
      default:
        break
    }
  }

  const [enrolledEvents, setEnrolledEvents] = useState([])

  return (
    <div>
      <div className={privateStyle.nav}>
        <div className={indexStyle['same-line']}>
          <div className={indexStyle['same-line']}>
            <img src='logo.png' className={indexStyle.avatar} />
            <p className={indexStyle['app-name']}> <i> chargre </i> </p>
          </div>

          <label className={indexStyle.label}>
            <i className={`material-icons ${indexStyle['icon-inside-input']}`}>search</i>
            <input type='text' className={`${indexStyle.input}`} />
          </label>
        </div>


        <div className={indexStyle['same-line']}>
          <i className={`material-icons`}>notifications</i>

          <div style={{ position: 'relative' }} className={indexStyle['same-line']}>
            <p className={privateStyle.username} onClick={() => setProfileMenu(!profileMenu)}> {user.email} </p>
            {profileMenu && dropdown}
            <div>
              <img src='avatar.jpg' className={indexStyle.avatar} />
            </div>
          </div>
        </div>


      </div>

      <div className={`${indexStyle.sideBar}`}>


        {(route === 'available-events') &&
          <div className={`${indexStyle['icon-box']} ${indexStyle.success}`} onClick={handleRouteChange}>
            <i className="material-icons">calendar_month</i>
            <p>Available events</p>
          </div>
        }

        {(route !== 'available-events') &&
          <div className={`${indexStyle['icon-box']}`} onClick={handleRouteChange}>
            <i className="material-icons">calendar_month</i>
            <p onClick={handleRouteChange}>Available events</p>
          </div>
        }


        {(route === 'enrolled-events') &&
          <div className={`${indexStyle['icon-box']} ${indexStyle.success}`} onClick={handleRouteChange}>
            <i className="material-icons">date_range</i>
            <p onClick={handleRouteChange}>Enrolled events</p>
          </div>
        }

        {(route !== 'enrolled-events') &&
          <div className={`${indexStyle['icon-box']}`} onClick={handleRouteChange}>
            <i className="material-icons">date_range</i>
            <p onClick={handleRouteChange}>Enrolled events</p>
          </div>
        }

        {(route === 'manage-rewards') &&
          <div className={`${indexStyle['icon-box']} ${indexStyle.success}`} onClick={handleRouteChange}>
            <i className="material-icons">loyalty</i>
            <p onClick={handleRouteChange}>Rewards management</p>
          </div>
        }

        {(route !== 'manage-rewards') &&
          <div className={`${indexStyle['icon-box']}`} onClick={handleRouteChange}>
            <i className="material-icons">loyalty</i>
            <p onClick={handleRouteChange}>Rewards management</p>
          </div>
        }
      </div>

      <div>
        {(route === 'available-events') && <AvailableEvents user={user} />}
        {(route === 'enrolled-events') && <EnrolledEvents />}
        {(route === 'manage-rewards') && <ManageRewards />}
      </div>




    </div>
  );





};

export default UserDashboard;
