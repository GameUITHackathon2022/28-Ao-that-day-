import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';

import Dialog from '../../../../components/dialog'
import indexStyle from '../../../../index.module.scss'

const CreateEvent = ({ user }) => {
     const navigate = useNavigate();


     const [email, setEmail] = useState('');
     const [password, setPassword] = useState('');
     const [role, setRole] = useState('publisher');


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

               fetch("http://172.16.18.89:8084/api/v1/account/login/",
                    {
                         headers: {
                              'Accept': 'application/json, text/plain , */*',
                              'Content-Type': 'application/json'
                         },
                         method: 'POST',
                         body: JSON.stringify({ email, password }),
                    })
                    .then(res => {
                         serverStatus = res.status
                         res.json()
                    })
                    .then(res => {
                         // setAccessToken(res.accessToken)
                         if (serverStatus === 400) {
                              setMsgType('error')
                              setMsgContent('Your password is incorrect.')
                              setDialog(true)
                              setTimeout(() => setDialog(false), 2000)
                         }
                         else if (serverStatus === 404) {
                              setMsgType('error')
                              setMsgContent('Your account does not exist.')
                              setDialog(true)
                              setTimeout(() => setDialog(false), 2000)
                         }
                         else if (serverStatus === 200) {
                              setMsgType('success')
                              setMsgContent('New event has been created.')
                              setDialog(true)
                              setTimeout(() => setDialog(false), 2000)
                         }
                    })

          }

     };
     return (
          <div className={indexStyle.container} >


               <form onSubmit={handleSubmit} style={{margin: '3vh 0 0 30vw'}}>



                    <label className={indexStyle.label}>
                         <p> Event name </p>
                         <i className={`material-icons ${indexStyle['icon-inside-input']}`}>format_color_text</i>
                         <input type='text' className={`${indexStyle.input}`} onChange={(e) => setEmail(e.target.value)} />
                    </label>

                    <label>
                         <p> Event Duration </p>
                         <i className={`material-icons ${indexStyle['icon-inside-input']}`}>schedule</i>
                         <input type='password' className={indexStyle.input} onChange={(e) => setPassword(e.target.value)} />
                    </label>

                    <label>
                         <p> Registration Duration </p>
                         <i className={`material-icons ${indexStyle['icon-inside-input']}`}>call_to_action</i>
                         <input type='password' className={indexStyle.input} onChange={(e) => setPassword(e.target.value)} />
                    </label>

                    <label>
                         <p> Location </p>
                         <i className={`material-icons ${indexStyle['icon-inside-input']}`}>location_on</i>
                         <input type='password' className={indexStyle.input} onChange={(e) => setPassword(e.target.value)} />
                    </label>

                    <label>
                         <p> Description </p>
                         <i className={`material-icons ${indexStyle['icon-inside-input']}`}>description</i>
                         <input type='password' className={indexStyle.input} onChange={(e) => setPassword(e.target.value)} />
                    </label>

                    <br />

                    <button type='submit' className={indexStyle.btn}> Submit </button>


                    {dialog === true && <Dialog type={msgType} content={msgContent} />}

               </form>

          </div>
     )
}

export default CreateEvent