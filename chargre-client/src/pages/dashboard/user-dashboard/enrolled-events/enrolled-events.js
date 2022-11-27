import { useState } from "react";

import EventCard from '../../../../components/event-card/event-card'
import EventDetails from "../../../../components/event-details/event-details"

import privateStyle from './enrolled-events.module.scss'
import indexStyle from '../../../../index.module.scss'


const EnrolledEvents = ({ user }) => {

  return (
    <div className={indexStyle.container} >
      <p className={privateStyle.title}> Your pending approved events </p>
      <div className={privateStyle.grid}>
        <EventCard eventID = {'1'} imgCode={'https://images.pexels.com/photos/1757363/pexels-photo-1757363.jpeg?auto=compress&cs=tinysrgb&w=600'} eventTitle={'The beauty of nature'} registrationDuration={`11/26/2022 to 11/28/2022`} currentAttendances={`47/50`}></EventCard>
      </div>
      <p className={privateStyle.title}> Your approved events </p>
      <div className={privateStyle.grid}>
        <EventCard eventID = {'1'} imgCode={'https://images.pexels.com/photos/1757363/pexels-photo-1757363.jpeg?auto=compress&cs=tinysrgb&w=600'} eventTitle={'The beauty of nature'} registrationDuration={`11/26/2022 to 11/28/2022`} currentAttendances={`47/50`}></EventCard>
        <EventCard eventID = {'1'} imgCode={'https://images.pexels.com/photos/1757363/pexels-photo-1757363.jpeg?auto=compress&cs=tinysrgb&w=600'} eventTitle={'The beauty of nature'} registrationDuration={`11/26/2022 to 11/28/2022`} currentAttendances={`47/50`}></EventCard>
      </div>
      <p className={privateStyle.title}> Your participated events </p>
      <div className={privateStyle.grid}>
        <EventCard eventID = {'1'} imgCode={'https://images.pexels.com/photos/1757363/pexels-photo-1757363.jpeg?auto=compress&cs=tinysrgb&w=600'} eventTitle={'The beauty of nature'} registrationDuration={`11/26/2022 to 11/28/2022`} currentAttendances={`47/50`}></EventCard>
      </div>
      <p className={privateStyle.title}> Your pending evaluated quality events </p>
      <div className={privateStyle.grid}>
        <EventCard eventID = {'1'} imgCode={'https://images.pexels.com/photos/1757363/pexels-photo-1757363.jpeg?auto=compress&cs=tinysrgb&w=600'} eventTitle={'The beauty of nature'} registrationDuration={`11/26/2022 to 11/28/2022`} currentAttendances={`47/50`}></EventCard>
        <EventCard eventID = {'1'} imgCode={'https://images.pexels.com/photos/1757363/pexels-photo-1757363.jpeg?auto=compress&cs=tinysrgb&w=600'} eventTitle={'The beauty of nature'} registrationDuration={`11/26/2022 to 11/28/2022`} currentAttendances={`47/50`}></EventCard>
        <EventCard eventID = {'1'} imgCode={'https://images.pexels.com/photos/1757363/pexels-photo-1757363.jpeg?auto=compress&cs=tinysrgb&w=600'} eventTitle={'The beauty of nature'} registrationDuration={`11/26/2022 to 11/28/2022`} currentAttendances={`47/50`}></EventCard>
      </div>
      <p className={privateStyle.title}> Your success events </p>
      <div className={privateStyle.grid}>
        <EventCard eventID = {'1'} imgCode={'https://images.pexels.com/photos/1757363/pexels-photo-1757363.jpeg?auto=compress&cs=tinysrgb&w=600'} eventTitle={'The beauty of nature'} registrationDuration={`11/26/2022 to 11/28/2022`} currentAttendances={`47/50`}></EventCard>
        <EventCard eventID = {'1'} imgCode={'https://images.pexels.com/photos/1757363/pexels-photo-1757363.jpeg?auto=compress&cs=tinysrgb&w=600'} eventTitle={'The beauty of nature'} registrationDuration={`11/26/2022 to 11/28/2022`} currentAttendances={`47/50`}></EventCard>
      </div>
    </div>
  )
};

export default EnrolledEvents;
