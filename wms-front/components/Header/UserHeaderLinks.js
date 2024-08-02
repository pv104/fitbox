/*eslint-disable*/
import React from "react";
import Link from "next/link";

// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import Tooltip from "@material-ui/core/Tooltip";
import Icon from "@material-ui/core/Icon";

// @material-ui/icons
import { Apps, CloudDownload } from "@material-ui/icons";
import DeleteIcon from "@material-ui/icons/Delete";
import IconButton from "@material-ui/core/IconButton";

// core components
import CustomDropdown from "/components/CustomDropdown/CustomDropdown.js";
import Button from "/components/CustomButtons/Button.js";

import styles from "/styles/jss/nextjs-material-kit/components/userHeaderLinksStyle.js";

const useStyles = makeStyles(styles);

export default function HeaderLinks(props) {
  const classes = useStyles();
  return (
    <List className={classes.list}>
      <ListItem className={classes.listItem}>
          <Button
          href="/login"
          color="transparent"
          className={classes.navLink}>
          <Icon className={classes.icons}>unarchive</Icon>로그인
        </Button>
      </ListItem>
      <ListItem className={classes.listItem}>
        <Button
          href="/signup"
          color="transparent"
          // target="_blank" // 이를 통해 새로운 페이지로 띄워버릴 수 있다.
          className={classes.navLink}>
          <Icon className={classes.icons}>unarchive</Icon>회원가입
        </Button>
      </ListItem>
      <ListItem className={classes.listItem}>
        <Button
          href="/components"
          color="transparent"
          className={classes.navLink}>
          <CloudDownload className={classes.icons} />사용방법
        </Button>
      </ListItem>
    </List>
  );
}