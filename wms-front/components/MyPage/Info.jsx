import { makeStyles } from "@material-ui/core";
import styles from "/styles/jss/nextjs-material-kit/pages/componentsSections/infoStyle.js";

const useStyles = makeStyles(styles)

// 회원정보랜더링
export default function Info({name, email, nickname, statusEnum, businessNumber}) {
    
    const classes = useStyles();
    const initialUserInfo = statusEnum !== 'DELETED' ? { name, email, nickname, businessNumber } : { name: '', email: '', nickname: '', businessNumber: '' };

    return (
        <div>
      <h2>기본 정보</h2>
      {statusEnum === 'DELETED' ? (
        <h4><strong>이메일: {initialUserInfo.email}</strong></h4>
      ) : (
        <div className={classes.container}>
          <h4><strong>이메일: {initialUserInfo.email}</strong></h4>
          <h4><strong>사업자 명: {initialUserInfo.name}</strong></h4>
          <h4><strong>사업자 번호: {initialUserInfo.businessNumber}</strong></h4>
        </div>
      )}
    </div>
    )
}