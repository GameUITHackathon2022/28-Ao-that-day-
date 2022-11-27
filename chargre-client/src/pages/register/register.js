import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';

import Dialog from '../../components/dialog';

import indexStyle from '../../index.module.scss'

const Register = ({ user, setCookie }) => {

  const navigate = useNavigate();

  useEffect(() => {
    if (user) navigate(`/`)
  }, [])


  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [cPassword, setCPassword] = useState('')

  const [role, setRole] = useState('OrdinaryUser');

  const [dialog, setDialog] = useState(false)

  const [msgType, setMsgType] = useState('')
  const [msgContent, setMsgContent] = useState('')

  const handleSubmit = (e) => {

    e.preventDefault();

    if (!email) {
      setMsgType('error')
      setMsgContent('You have not typed in email.')
      setDialog(true)
      setTimeout(() => setDialog(false), 2000)
    }

    else if (!password) {
      setMsgType('error')
      setMsgContent('You have not typed in password.')
      setDialog(true)
      setTimeout(() => setDialog(false), 2000)
    }

    else if (cPassword !== password) {
      setMsgType('error')
      setMsgContent('Your passwords are not the same.')
      setDialog(true)
      setTimeout(() => setDialog(false), 2000)
    }

    else {
      console.log(email, password)
      let serverMsg = undefined
      console.log(JSON.stringify({email, password}))
      fetch("http://172.16.18.89:8090/api/v1/account/signup/",
        {
          headers: {
            'Accept': 'application/json, text/plain , */*',
            'Content-Type': 'application/json'
          },
          method: 'POST',
          body: JSON.stringify({email, password}),
        })
        .then(res => {
          console.log(res.status)
          if (res.status === 400) {
            setMsgType('error')
            setMsgContent('Your email was occupied before.')
            setDialog(true)
            setTimeout(() => setDialog(false), 2000)
          }
          else if (res.status === 201) {
            setCookie('user', { email, password, role }, { path: '/' });
            navigate(`/`)
          }
        })
      
    }


  };

  return (
    <div className={`${indexStyle.bg} ${indexStyle['center-box']}`}>

      <div className={`${indexStyle['same-line']} ${indexStyle.logo}`}>
        <img src='./logo.png' width='50px' />
        <p className={indexStyle['app-name']}> <i> chargre </i> </p>
      </div>

      <form className={indexStyle.form} onSubmit={handleSubmit}>

        <p className={`${indexStyle.title}`}> Welcome new member! </p>

        <label className={indexStyle.label}>
          <p> Email </p>
          <i className={`material-icons ${indexStyle['icon-inside-input']}`}>mail</i>
          <input type='text' className={`${indexStyle.input}`} onChange={(e) => setEmail(e.target.value)} />
        </label>

        <label>
          <p> Password </p>
          <i className={`material-icons ${indexStyle['icon-inside-input']}`}>key</i>
          <input type='password' className={indexStyle.input} onChange={(e) => setPassword(e.target.value)} />
        </label>

        <label>
          <p> Confirmed Password </p>
          <i className={`material-icons ${indexStyle['icon-inside-input']}`}>vpn_key</i>
          <input type='password' className={indexStyle.input} onChange={(e) => setCPassword(e.target.value)} />
        </label>

        <br />

        <button type='submit' className={indexStyle.btn}> Submit </button>

        {dialog === true && <Dialog type={msgType} content={msgContent} />}
      </form>
    </div>


  );
};

export default Register;
