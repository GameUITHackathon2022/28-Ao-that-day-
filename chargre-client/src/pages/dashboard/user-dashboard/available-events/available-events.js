import { useState } from "react";
import { Link } from 'react-router-dom'

import EventCard from '../../../../components/event-card/event-card'
import EventDetails from "../../../../components/event-details/event-details"
import privateStyle from './available-events.module.scss'
import indexStyle from '../../../../index.module.scss'


const AvailableEvents = ({ user }) => {

  const [eventList1, setEventList1] = useState([])
  const [eventList2, setEventList2] = useState([])
  const [eventList3, setEventList3] = useState([])

  if (eventList1.length === 0) {
    fetch('http://172.16.18.89:8086/api/v1/event?page=0&size=100&eventTypeId=SKCD').then(res => res.json()).then(res => {
      setEventList1(res)
    })
  }

  if (eventList2.length === 0) {
    fetch('http://172.16.18.89:8086/api/v1/event?page=0&size=100&eventTypeId=SKCN').then(res => res.json()).then(res => {
      setEventList2(res)
    })
  }

  if (eventList3.length === 0) {
    fetch('http://172.16.18.89:8086/api/v1/event?page=0&size=100&eventTypeId=HDTT').then(res => res.json()).then(res => {
      setEventList3(res)
    })
  }


  const [eventID, setEventID] = useState(0)
  const [eventDetails, setEventDetails] = useState([])
  const [modalPopUp, setModalPopUp] = useState(false)


  const handleEventChosen = (id) => {

    setEventID(id)

    fetch(`http://172.16.18.89:8086/api/v1/event/${eventID}`).then(res => res.json()).then(res => {
      setEventDetails(res)
      setModalPopUp(true)
    })

  }



  return (
    <div>
      {(!modalPopUp) && <div className={indexStyle.container} >
        <p className={privateStyle.title}> Community event </p>
        <div className={privateStyle.grid}>
          {eventList1.map((element) => <EventCard eventID={element.id} imgCode={element.img} eventTitle={element.title} registrationDuration={`${element.createdAt.slice(0, 10)} to ${element.closeFormAt.slice(0, 10)}`} currentAttendances={`${element.attendances}/${element.maxAttendances}`} handleEventChosen={handleEventChosen}></EventCard>)}
        </div>

        <br />
        <hr />
        <br />

        <p className={privateStyle.title}> Personal event </p>
        <div className={privateStyle.grid}>
          {eventList2.map((element) => <EventCard eventID={element.id} imgCode={element.img} eventTitle={element.title} registrationDuration={`${element.createdAt.slice(0, 10)} to ${element.closeFormAt.slice(0, 10)}`} currentAttendances={`${element.attendances}/${element.maxAttendances}`} handleEventChosen={handleEventChosen}></EventCard>)}
        </div>

        <br />
        <hr />
        <br />

        <p className={privateStyle.title}> Charity event </p>
        <div className={privateStyle.grid}>
          {eventList3.map((element) => <EventCard eventID={element.id} imgCode={element.img} eventTitle={element.title} registrationDuration={`${element.createdAt.slice(0, 10)} to ${element.closeFormAt.slice(0, 10)}`} currentAttendances={`${element.attendances}/${element.maxAttendances}`} handleEventChosen={handleEventChosen}></EventCard>)}
        </div>

      </div>}
      {modalPopUp && <EventDetails user={user} eventId={eventDetails.id} eventTitle={eventDetails.title} registrationDuration={`${eventDetails.createdAt.slice(0, 10)} to ${eventDetails.closeFormAt.slice(0, 10)}`} eventDuration={`${eventDetails.createdAt.slice(0, 10)} to ${eventDetails.endedAt.slice(0, 10)}`} attendances={`${eventDetails.attendances}/${eventDetails.maxAttendances}`} location={eventDetails.location} description={eventDetails.description} imgPath={eventDetails.imgPaths} publisher={eventDetails.publisherName} setModalPopUp={setModalPopUp} />}
    </div>


  );





};

export default AvailableEvents;
