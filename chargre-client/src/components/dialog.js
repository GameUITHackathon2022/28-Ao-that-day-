import indexStyle from '../index.module.scss'

const Dialog = ({type, content}) => {
     let icon = undefined
     switch (type) {
          case 'error':
               icon = 'priority_high'
               break
          case 'success':
               icon = 'verified'
               break
     }
     return (
          <div className = {`${indexStyle.dialog} ${indexStyle[`${type}`]}`}>
               <i className="material-icons"> {icon} </i>
               <p> {content} </p>
          </div>
     )
}

export default Dialog