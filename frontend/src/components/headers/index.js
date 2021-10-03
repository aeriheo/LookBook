import React, {useState, useLayoutEffect } from 'react';
import {AppBar, Avatar, Typography, Menu, MenuItem, Button, Grid, Divider, Toolbar, IconButton, Drawer, ListItem, List, ListItemText} from '@mui/material';
import {useMediaQuery} from 'react-responsive';
import SearchIcon from '@mui/icons-material/Search';
import PersonOutlineIcon from '@mui/icons-material/PersonOutline';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import ImportContactsIcon from '@mui/icons-material/ImportContacts';
import LogoutIcon from '@mui/icons-material/Logout';
import MenuIcon from '@mui/icons-material/Menu';
import ArrowLeftIcon from '@mui/icons-material/ArrowLeft';
import StarBorderRoundedIcon from '@mui/icons-material/StarBorderRounded';
import ListRoundedIcon from '@mui/icons-material/ListRounded';
import './style.css';
import {userAPI} from '../../utils/axios';
import { useHistory } from "react-router-dom";

const Header = props=>{
    let history = useHistory();
    const [anchorEl, setAnchorEl] = useState(null);
    const [menuOpen, setMenuOpen] = useState(false);
    const [data, setData] = useState({});
    const [avatarUrl, setavatarUrl] = useState('');

    const handleDrawerOpen = () =>{  
        setMenuOpen(true);
    };

    const handleDrawerClose = () =>{ 
        setMenuOpen(false);
    };

    const open = Boolean(anchorEl);
    const handleClick = (e) =>{
        setAnchorEl(e.currentTarget);
    };
    const handleClose = () =>{
        setAnchorEl(null);
    }

    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });

    const reloadUser = async()=>{
        let retry = await userAPI.reissue();
        if(retry.statusCode!==200) {
            alert('올바른 사용자가 아닙니다.');
            logout();
            
        }
        const result = await userAPI.userinfo();
        setData(result.data);
        setavatarUrl(result.data.userProfileUrl);
    }

    useLayoutEffect (()=>{
        let completed = false;

        async function loadUser(){
            const result = await userAPI.userinfo();
            if(result.status===400){
                reloadUser();
            }else{
                setData(result.data);
                setavatarUrl(result.data.userProfileUrl);
            }
        }

        loadUser();

        return()=>{
            completed=true;
        };
    },[]);

    const logout = ()=>{
        window.sessionStorage.removeItem('refreshToken');
        window.sessionStorage.removeItem('token');
        window.location.replace (`/`);
    }

    return(
        <div className="divMobile">
            {isMobile?(
                <div>
                    <AppBar id='headerMobile'>
                        <Grid container justifyContent='space-between' direction = "row" alignItems = "center">
                            <Typography variant = "h3" id='logoMobile' onClick={() => window.location.replace (`/lookbook`)}>
                                LB
                            </Typography>
                            <Toolbar>
                                <IconButton onClick = {handleDrawerOpen}>
                                    <MenuIcon fontSize = "large" className='mobileIconColor'/>
                                </IconButton>
                            </Toolbar>
                        </Grid>
                    </AppBar>
                    <Drawer anchor = "right" open = {menuOpen}>
                        <div onClick = {handleDrawerClose}>
                            <IconButton edge = 'end' >
                                <ArrowLeftIcon fontSize = "large" className='mobileIconColor'/>
                            </IconButton>
                        </div>
                        <div>
                            <Divider variant="middle"/>
                            <div id='userMobile'>
                                {avatarUrl?(
                                    <Avatar src={`https://pjt2-lookbook.s3.ap-northeast-2.amazonaws.com/profile/${avatarUrl}`} style={{marginRight:'10px'}} />
                                ):(
                                    <Avatar sx={{bgcolor:'#C4C4C4', marginRight:'10px'}}></Avatar>
                                )}
                                {data.userNickname}
                            </div>
                            <Divider variant="middle"/>
                            <div>
                                <List id='listMobile'>
                                    <ListItem button onClick={handleClose} id = 'listItemMobile' onClick={()=>history.push(`/mypage/mypage`)}>
                                        <PersonOutlineIcon id='iconLargeMobile'/>
                                        <ListItemText primary="MYPAGE" id = 'listItemTextMobile'/>
                                    </ListItem>
                                    <ListItem button onClick={handleClose} id = 'listItemMobile' onClick={()=>history.push(`/mypage/like`)}>
                                        <FavoriteBorderIcon id='iconMediumMobile' style={{color:'#FF7474'}} />
                                        <ListItemText primary="LIKE" id = 'listItemTextMobile'/>
                                    </ListItem>
                                    <ListItem button onClick={handleClose} id = 'listItemMobile' onClick={()=>history.push(`/mypage/mylb`)}>
                                        <ImportContactsIcon id='iconMediumMobile'/>
                                        <ListItemText primary="MY LB" id = 'listItemTextMobile'/>
                                    </ListItem>
                                </List>
                            </div>
                            <Divider variant="middle"/>
                        </div>
                        <List id='listMobile'>
                            <ListItem button onClick={()=>history.push(`/search`)}>
                                <SearchIcon id = 'iconMediumMobile'/>
                                <ListItemText primary="검색" id = 'listItemTextMobile'/>
                            </ListItem>
                            <ListItem button onClick={() => window.location.replace (`/category`)}>
                                <ListRoundedIcon id = 'iconMediumMobile'/>
                                <ListItemText primary="카테고리" id = 'listItemTextMobile'/>
                            </ListItem>
                            <ListItem button >
                                <StarBorderRoundedIcon style={{color:'#FFC700'}} id = 'iconLargeMobile'/>
                                <ListItemText primary="도서평가" id = 'listItemTextMobile'/>
                            </ListItem>
                        </List>
                            <List id='listMobile'>
                                <Divider variant="middle"/>
                                <ListItem button onClick={()=>logout()} id = 'listItemMobile' >
                                    <LogoutIcon id = 'iconMediumMobile'/>
                                    <ListItemText primary="로그아웃" id='listItemTextMobile'/>
                                </ListItem>
                            </List>
                    </Drawer>
                </div>
            ):
            (
            <AppBar id='AppBarWeb'>
                <Grid container justifyContent='space-between' direction = "row" alignItems = "center">     
                    <div id = 'divWeb'>
                        <Typography variant = "h3" id='logoWeb' onClick={() => window.location.replace (`/lookbook`)}>
                            LB
                        </Typography>
                        <Button size = "large" id = 'buttonWeb' onClick={() => window.location.replace (`/category`)} >
                            카테고리
                        </Button>
                        <Button size = "large" id = 'buttonWeb' >
                            도서평가
                        </Button>
                    </div>
                    <div id = 'divWeb'>
                        <Button size = "large" id = 'buttonWeb' onClick={()=>history.push(`/search`)}>
                            <SearchIcon/>
                            <div id = 'searchBtnWeb'>
                                검색
                            </div>
                        </Button>
                        <div id='userDivWeb'>
                            {avatarUrl?(
                                <Avatar src={`https://pjt2-lookbook.s3.ap-northeast-2.amazonaws.com/profile/${avatarUrl}`} 
                                    id = 'user-avatar'
                                    onClick={handleClick} 
                                    aria-controls="user-menu"
                                    aria-haspopup="true"
                                    aria-expanded={open?'true':undefined} />
                            ):(
                                <Avatar 
                                sx={{bgcolor:'#C4C4C4'}} 
                                id = 'user-avatar'
                                onClick={handleClick} 
                                aria-controls="user-menu"
                                aria-haspopup="true"
                                aria-expanded={open?'true':undefined}>
                            </Avatar>)}
                            <Menu anchorOrigin = {{vertical:'bottom', horizontal:'right'}} transformOrigin={{vertical:'top'}} id = "user-menu" anchorEl = {anchorEl} open = {open} onClick={handleClose} MenuListProps={{'aria-labelledby':'user-avatar'}}>
                                <div id='menuUserWeb'>
                                    {avatarUrl?(
                                        <Avatar src={`https://pjt2-lookbook.s3.ap-northeast-2.amazonaws.com/profile/${avatarUrl}`} style={{marginRight:'10px'}} />
                                    ):(
                                        <Avatar sx={{bgcolor:'#C4C4C4', marginRight:'10px'}}></Avatar>
                                    )}
                                    {data.userNickname}
                                </div>
                                <Divider variant="middle"/>
                                <MenuItem onClick={handleClose} id='menuItemWeb' onClick={()=>history.push(`/mypage/mypage`)}>
                                    <PersonOutlineIcon id='menuIconLargeWeb'/>
                                    MYPAGE
                                </MenuItem>
                                <MenuItem onClick={handleClose} id='menuItemWeb' onClick={()=>history.push(`/mypage/like`)}>
                                    <FavoriteBorderIcon style={{color:'#FF7474'}} id = 'menuIconMediumWeb'/>
                                    LIKE
                                </MenuItem>
                                <MenuItem onClick={handleClose} id='menuItemWeb' onClick={()=>history.push(`/mypage/mylb`)}>
                                    <ImportContactsIcon id = 'menuIconMediumWeb'/>
                                    MY LB
                                </MenuItem>
                                <Divider variant="middle"/>
                                <MenuItem onClick={()=>logout()} id='menuItemWeb'>
                                    <LogoutIcon id = 'menuIconMediumWeb'/>
                                    로그아웃
                                </MenuItem>
                            </Menu>
                        </div>
                    </div>
                </Grid>           
            </AppBar>)}
        </div>
    );
};

export default Header;