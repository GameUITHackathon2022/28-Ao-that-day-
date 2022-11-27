import { Link } from 'react-router-dom';

import indexStyle from '../../index.module.scss'

const Error = () => {
  return (
    <div style = {{width: '100vw', height: '100vh', display: 'flex', flexFlow: 'column', justifyContent: 'center', alignItems: 'center'}}>
      <p> Page not found </p>
      <div>
        <Link to='/' className = {indexStyle.link}>Return to homepage</Link>
      </div>
      
    </div>
  )
}

export default Error
