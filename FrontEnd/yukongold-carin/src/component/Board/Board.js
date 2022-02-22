import React from 'react';
import './Board.css';

const VerticalAxis = ["1","2","3","4","5","6","7","8"];
const HorizontalAxis = ["a","b","c","d","e","f","g","h"];

export default function Board(){
    let board = [];
    for(let i = VerticalAxis.length-1;i >= 0;i--){
        for(let j = 0;j < HorizontalAxis.length;j++){
            board.push(<div className = "tile"></div>)
        }
    }
    return <div id="Board">{board}</div>
}