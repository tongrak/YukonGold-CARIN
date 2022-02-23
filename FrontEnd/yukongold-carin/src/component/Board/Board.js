import React from 'react';
import './Board.css';

// const VerticalAxis = [];
// const HorizontalAxis = [];

const clickCoor = () =>{
    // console.log(e)
}

export default function Board(props){
    let board = [];
    let x = 1;
    let y = 1;
    for(let i = props.layout;i > 0;i--){
        for(let j = 0;j < props.layout;j++){
            console.log(props.layout)
            board.push(
            <button id={(y*10)+x} className = "tile"
            onClick={clickCoor()}></button>)
            
            if(x == props.layout){
                x = 1;
                y++;
                
            }else{
                x++;
                
            }
            
        }
    }
    return <div id="Board">{board}</div>
}