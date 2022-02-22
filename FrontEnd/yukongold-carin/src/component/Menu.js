import React from 'react';
import Credit from './Menu Elements/Credit';
import Shop from './Menu Elements/Shop';
import Speed from './Menu Elements/Speed';
import Pause from './Menu Elements/Pause';
import './Menu.css';


export default function Menu(){
    let menu = [];
    menu.push(<Credit/>);
    menu.push(<Shop/>);
    menu.push(<Speed/>);
    menu.push(<Pause/>);
    return<div id= "Menu">{menu}</div>;
    // return<div id= "Menu"><img src="images/Credits.png"/></div>;
};