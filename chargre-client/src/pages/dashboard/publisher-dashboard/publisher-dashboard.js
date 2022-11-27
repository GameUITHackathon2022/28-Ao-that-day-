import { useState } from "react";
import { Link } from 'react-router-dom'

import CreateEvent from './create-event/create-event'
import ManageEvents from './manage-events/manage-events'

import privateStyle from './publisher-dashboard.module.scss'
import indexStyle from '../../../index.module.scss'


const PublisherDashboard = ({ user, removeCookie }) => {

  const [profileMenu, setProfileMenu] = useState(false)

  const [route, setRoute] = useState('manage-events')

  const dropdown = <div className={indexStyle.dropdown}>
    <p> View complete profile </p>
    <Link to='/' className={indexStyle.link} onClick={() => removeCookie('user')}> Log out </Link>
  </div>

  const handleRouteChange = (event) => {
    let nextRoute = event.target.innerHTML || event.target.parentElement.innerText

    switch (nextRoute) {
      case 'Create event':
        setRoute('create-event')
        break
      case 'Manage events':
        setRoute('manage-events')
        break
      default:
        break
    }
  }

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


        {(route === 'create-event') &&
          <div className={`${indexStyle['icon-box']} ${indexStyle.success}`} onClick={handleRouteChange}>
            <i className="material-icons">draw</i>
            <p>Create event</p>
          </div>
        }

        {(route !== 'create-event') &&
          <div className={`${indexStyle['icon-box']}`} onClick={handleRouteChange}>
            <i className="material-icons">draw</i>
            <p onClick={handleRouteChange}>Create event</p>
          </div>
        }


        {(route === 'manage-events') &&
          <div className={`${indexStyle['icon-box']} ${indexStyle.success}`} onClick={handleRouteChange}>
            <i className="material-icons">manage_accounts</i>
            <p onClick={handleRouteChange}>Manage events</p>
          </div>
        }

        {(route !== 'manage-events') &&
          <div className={`${indexStyle['icon-box']}`} onClick={handleRouteChange}>
            <i className="material-icons">manage_accounts</i>
            <p onClick={handleRouteChange}>Manage events</p>
          </div>
        }

      </div>

      <div>
        {(route === 'create-event') && <CreateEvent user={user} />}
        {(route === 'manage-events') && <ManageEvents />}
      </div>




    </div>
  );





};

export default PublisherDashboard;
