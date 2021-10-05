import React, {useState} from 'react';
import {useMediaQuery} from 'react-responsive';
import Logo from '../logo';
import Tabs, {TabPane} from 'rc-tabs';
import './style.css';
import "rc-tabs/assets/index.css";
import Mylike from '../mylike';
import Review from '../review';
import UserInfo from '../userinfo';
import MYLB from '../mylb';
import { useLocation } from "react-router-dom";

const MyInfo = () =>{
    const location = useLocation();
    const [value, setValue] = useState(location.pathname.split("/")[2]);
    const isMobile = useMediaQuery({
        query: "(max-width : 768px)"
    });
    const handleTabs= (event, newValue) => {
        setValue(newValue);
      };

    return(
        <div>
            {isMobile?(
                <div id='TabMobile'>
                    <div id='mypageDiv'>
                        <Tabs onChange={handleTabs} defaultActiveKey={value} tabBarGutter={20} id='TabListMobile'>
                            <TabPane tab="MY PAGE" key="mypage" style={{height:'70vh', overflow: 'auto', outline: 'none'}}>
                                <UserInfo/>
                            </TabPane>
                            <TabPane tab="MY LB" key = "mylb" style={{height:'70vh', overflow: 'auto', outline: 'none'}} >
                                <MYLB/>
                            </TabPane>
                            <TabPane tab="LIKE" key = "like" style={{height:'70vh', overflow: 'auto', outline: 'none'}} >
                                <Mylike/>
                            </TabPane>
                            <TabPane tab="REVIEW" key = "review" style={{height:'70vh', overflow: 'auto', outline: 'none'}} >
                                <Review/>
                            </TabPane>
                        </Tabs>
                    </div>
                </div>
            ):(
                <div id='TabWeb'>
                    <Logo/>
                    <div>
                        <div id='mypageDiv'>
                            <Tabs onChange={handleTabs} tabPosition = "left"  defaultActiveKey={value} tabBarGutter={50} id='TabListWeb'>
                                <TabPane tab="MY PAGE　" key="mypage" style={{height:'100vh', overflow: 'auto', outline: 'none'}} >
                                    <UserInfo/>
                                </TabPane>
                                <TabPane tab="MY LB " key = "mylb" style={{height:'100vh', overflow: 'auto', outline: 'none'}} >
                                    <MYLB/>
                                </TabPane>
                                <TabPane tab="LIKE " key = "like" style={{height:'100vh', overflow: 'auto', outline: 'none'}} >
                                    <Mylike/>
                                </TabPane>
                                <TabPane tab="REVIEW" key = "review" style={{height:'100vh', overflow: 'auto', outline: 'none'}} >
                                    <Review/>
                                </TabPane>
                            </Tabs>
                        </div>
                    </div>
                </div>
            )}
        </div>
    )
}

export default MyInfo;