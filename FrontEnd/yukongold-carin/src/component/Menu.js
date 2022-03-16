import React from 'react';
import Credit from './Menu_Elements/Credit';
import Shop from './Menu_Elements/Shop';
import Speed from './Menu_Elements/Speed';
import Pause from './Menu_Elements/Pause';
import './Menu.css';


export default function Menu(){
    let menu = [];
    menu.push(<Credit/>);
    menu.push(<Shop/>);
    menu.push(<Speed/>);
    menu.push(<Pause/>);
    return (
        <div>
            <div id= "Menu">
                <div className='justify-items-center'>
                    <Credit/>
                    <Shop/>
                    <Speed/>
                    <Pause/>
                </div>
                
            </div>
            
            
        </div>
        )
    // return<div id= "Menu"><img src="images/Credits.png"/></div>;
};