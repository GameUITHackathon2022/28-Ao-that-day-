import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';

import Dialog from '../../components/dialog';
import indexStyle from '../../index.module.scss'

const PasswordReset = () => {

  const navigate = useNavigate();

  const [email, setEmail] = useState('');
  const [vCode, setVCode] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [cPassword, setCPassword] = useState('')
  const [hasSubmitted, setHasSubmitted] = useState(false)
  const [codeCorrect, setCodeCorrect] = useState(false)

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
    else setHasSubmitted(true)
    // fetch mail to send code
  };

  const handleVerification = (e) => {
    e.preventDefault();

    if (!vCode) {
      setMsgType('error')
      setMsgContent('You have not typed in the verification code.')
      setDialog(true)
      setTimeout(() => setDialog(false), 2000)
    }
    // fetch email API
    setCodeCorrect(true)
  };

  const handlePasswordSetUp = (e) => {
    e.preventDefault();

    if (!newPassword) {
      setMsgType('error')
      setMsgContent('You have not typed in the new password.')
      setDialog(true)
      setTimeout(() => setDialog(false), 2000)
    }
    else if (!cPassword) {
      setMsgType('error')
      setMsgContent('You have not typed in the confirmed password.')
      setDialog(true)
      setTimeout(() => setDialog(false), 2000)
    }
    else if (cPassword !== newPassword) {
      setMsgType('error')
      setMsgContent('Your passwords are not the same.')
      setDialog(true)
      setTimeout(() => setDialog(false), 2000)
    }
    // fetch update password
    else navigate('/')
  };

  const emailCollection = <>
    <label className={indexStyle.label}>
      <p> Email </p>
      <i className={`material-icons ${indexStyle['icon-inside-input']}`}>mail</i>
      <input type='text' className={`${indexStyle.input}`} onChange={(e) => setEmail(e.target.value)} />
    </label>

    <br />

    <button type='submit' className={indexStyle.btn} onClick={handleSubmit}> Send code to my mailbox </button>
  </>

  const verification = <>
    <label className={indexStyle.label}>
      <p> Verification code </p>
      <i className={`material-icons ${indexStyle['icon-inside-input']}`}>code</i>
      <input type='text' className={`${indexStyle.input}`} onChange={(e) => setVCode(e.target.value)} />
    </label>
    <br />
    <button type='submit' className={indexStyle.btn} onClick={handleVerification}> Submit </button>
  </>

  const passwordSetUp = <>
    <label className={indexStyle.label}>
      <p> New password </p>
      <i className={`material-icons ${indexStyle['icon-inside-input']}`}>key</i>
      <input type='text' className={`${indexStyle.input}`} onChange={(e) => setNewPassword(e.target.value)} />
    </label>
    <label className={indexStyle.label}>
      <p> Confirm new password </p>
      <i className={`material-icons ${indexStyle['icon-inside-input']}`}>vpn_key</i>
      <input type='text' className={`${indexStyle.input}`} onChange={(e) => setCPassword(e.target.value)} />
    </label>

    <br />
    <button type='submit' className={indexStyle.btn} onClick={handlePasswordSetUp}> Enter </button>
  </>

  return (
    <div className={`${indexStyle.bg} ${indexStyle['center-box']}`}>

      <div className={`${indexStyle['same-line']} ${indexStyle.logo}`}>
        <img src='./logo.png' width='50px' />
        <p className={indexStyle['app-name']}> <i> chargre </i> </p>
      </div>

      <form className={indexStyle.form}>

        <p className={`${indexStyle.title}`}> Password reset </p>

        {!hasSubmitted && emailCollection}

        {hasSubmitted && !codeCorrect && verification}

        {codeCorrect && passwordSetUp}

      </form>

      {dialog === true && <Dialog type={msgType} content={msgContent} />}
    </div>


  );
};
export default PasswordReset;
