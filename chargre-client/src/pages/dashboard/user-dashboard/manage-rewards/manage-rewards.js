import { useState } from "react";

import privateStyle from './manage-rewards.module.scss'
import indexStyle from '../../../../index.module.scss'


const ManageRewards = ({ user }) => {

  return (
    <div className={indexStyle.container} >
      <p className={privateStyle.title}> Your current accumulation point </p>
      <p className={privateStyle.title}> Your affordable rewards </p>
      <p className={privateStyle.title}> Your potential rewards </p>
      <p className={privateStyle.title}> Your current rewards </p>
      <p className={privateStyle.title}> Your used rewards </p>
     
    </div>
  )
};

export default ManageRewards
