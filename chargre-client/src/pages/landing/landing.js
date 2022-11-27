import { Link } from 'react-router-dom';

import privateStyle from './landing.module.scss'
import indexStyle from '../../index.module.scss'

const Introduction = () => {
     return (
          <div>
               <div className={`${indexStyle['same-line']} ${indexStyle.logo}`}>
                    <img src='./logo.png' width='50px' />
                    <p className={indexStyle['app-name']}> <i> chargre </i> </p>
               </div>

               <div className = {indexStyle.container}>
                    <div>
                         <p className = {privateStyle.title}> chargre </p>
                         <p> is a great place </p>
                    </div>
                    <div>
                         <img src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCn7Zd-rSjKafV90Wb1RYudgrQJyNSPNahrA&usqp=CAU' />
                    </div>
               </div>
          </div>
     )
}

export default Introduction