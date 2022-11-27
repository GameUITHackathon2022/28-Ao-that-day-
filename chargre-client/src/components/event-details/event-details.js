import { useState } from 'react'

import privateStyle from './event-details.module.scss'
import { useNavigate } from 'react-router-dom'

import '../../index.module.scss'
import indexStyle from '../../index.module.scss'

const EventDetails = ({ user, eventId, eventTitle, registrationDuration, eventDuration, attendances, location, description, imgPath, publisher, setModalPopUp }) => {
     const navigate = useNavigate()
     const [dialog, setDialog] = useState('')
     
     const handleEventEnrolled = () => {
          fetch("http://172.16.18.89:8086/api/v1/event/attend/",
               {
                    headers: {
                         'Accept': 'application/json, text/plain , */*',
                         'Content-Type': 'application/json'
                    },
                    method: 'POST',
                    body: JSON.stringify({
                         username: user.email,
                         eventId: eventId
                    }),
               })
               .then(res => {
                    console.log(res.status)
                    if (res.status !== 200) {
                         setDialog('Your registration is failed.')
                         setTimeout(() => setModalPopUp(false), 2000)
                    }
                    else if (res.status === 200) {
                         setDialog('Your registration is success.')
                         setTimeout(() => navigate('/'), 2000)
                    }
               })
     }


     return (
          <div>
               <div className={privateStyle.modal} style={{ top: `10%`, overflowY: 'scroll' }}>

                    <div className={privateStyle['same-line']}>
                         <p className={privateStyle.title}>{eventTitle}</p>
                         <i className={`material-icons`} style={{ cursor: 'pointer' }} onClick={() => setModalPopUp(false)}>cancel</i>
                    </div>
                    <div className={privateStyle['same-line']} style={{ width: '80%' }}>
                         <div>
                              <p className={privateStyle.thumbnail}>Registration duration: </p>
                              <p className={privateStyle.time}> {registrationDuration} </p>
                         </div>
                         <div>
                              <p className={privateStyle.thumbnail}>Event duration: </p>
                              <p className={privateStyle.time}> {eventDuration} </p>
                         </div>
                    </div>


                    <div className={privateStyle['same-line']} style={{ width: '66%' }}>
                         <div>
                              <p className={privateStyle.thumbnail}>Attendance: </p>
                              <p className={privateStyle.attendance}> {attendances} </p>
                         </div>
                         <div>
                              <p className={privateStyle.thumbnail}>Location: </p>
                              <p className={privateStyle.location}> {location} </p>
                         </div>
                    </div>





                    <p className={privateStyle.thumbnail}>Description: </p>
                    <p>{description}</p>

                    <img src={imgPath} width='50%' style = {{transform: 'translateX(50%)'}}/>

                    <p className={privateStyle.thumbnail}>Publisher: </p>
                    <p className={privateStyle.publisher}>{publisher}</p>

                    <button className={indexStyle.btn} style = {{transform: 'translateX(120%)'}} onClick={handleEventEnrolled}> Enroll me! </button>
                    { (dialog !== '') && <p> {dialog} </p>}
               </div>
               
          </div>

     )
}

export default EventDetails