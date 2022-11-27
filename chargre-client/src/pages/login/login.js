import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';

import Dialog from '../../components/dialog';

import indexStyle from '../../index.module.scss'

const Login = ({ user, setCookie, setAccessToken }) => {

  const navigate = useNavigate();

  useEffect(() => {
    if (user) navigate(`/`)
  }, [])


  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  let role = undefined


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

    else {

      let serverStatus = undefined

      fetch("http://172.16.18.89:8090/api/v1/account/login/",
        {
          headers: {
            'Accept': 'application/json, text/plain , */*',
            'Content-Type': 'application/json'
          },
          method: 'POST',
          body: JSON.stringify({ email, password }),
        })
        .then(res => res.json())
        .then(res => {
          console.log(res)
          // setAccessToken(res.accessToken)
          role = res.mainRole
          setCookie('user', { email, password, role }, { path: '/' });
          navigate(`/`)
        }
        )
        .catch(() => {
          setMsgType('error')
          setMsgContent('Your account does not exist or your password is incorrect.')
          setDialog(true)
          setTimeout(() => setDialog(false), 2000)
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

        <p className={`${indexStyle.title}`}> Welcome back! </p>

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

        <br />

        <button type='submit' className={indexStyle.btn}> Submit </button>

        <p className={indexStyle['center-text']}> Have forgotten your password? </p>

        <p className={indexStyle['center-text']}>
          <Link to='/reset-password' className={`${indexStyle.link} ${indexStyle.title} `}> Reset it now </Link>
        </p>

        <p className={indexStyle['center-text']}> Have not owned an account? </p>

        <p className={indexStyle['center-text']}>
          <Link to='/registration' className={`${indexStyle.link} ${indexStyle.title} `}> Register now </Link>
        </p>

        {dialog === true && <Dialog type={msgType} content={msgContent} />}

      </form>
    </div>


  );
};
export default Login;
