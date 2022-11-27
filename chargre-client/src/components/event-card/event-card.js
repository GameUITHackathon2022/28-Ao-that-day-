import privateStyle from './event-card.module.scss'
import '../../index.module.scss'
import indexStyle from '../../index.module.scss'

const EventCard = ({ eventID, imgCode, eventTitle, registrationDuration, currentAttendances, handleEventChosen }) => {
     return (
          <div className={privateStyle.card} onClick = {() => handleEventChosen(eventID)}>
               <div className={`${privateStyle.image}`} style={{ backgroundImage: `url(${imgCode})` }}> </div>
               <div className={privateStyle.content}>
                    <p className={privateStyle.title}> {eventTitle} </p>
                    <div className = {indexStyle['same-line']}>
                         <i className = {`material-icons`}>running_with_errors</i>
                         <p> Registration duration: <span> {registrationDuration} </span></p>
                    </div>
                    <div className = {indexStyle['same-line']}>
                         <i className = {`material-icons`}>group_add</i>
                         <p> Current attendances: <span> {currentAttendances} </span></p>
                    </div>
               </div>

          </div>
     )
}

export default EventCard