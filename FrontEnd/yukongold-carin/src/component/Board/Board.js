import React, { useState } from 'react';
import './Board.css';
import axios from 'axios';
// const VerticalAxis = [];
// const HorizontalAxis = [];


function Board(props){
    
    const sendData = (e) => {
        axios.post("http://localhost:8080/click" , {
            id : 1,
            coor : e
        })
        .then(res => {
            console.log(res)
        })
        .catch( err => {
            console.log(err)
        })
    }

    const clickCoor = (e) =>{
        let fst = Math.floor(e/10)
        let snd = Math.floor(e%10)
        let fstSring = fst.toString();
        let sndString = snd.toString();
        let toSending = fstSring.concat(",",sndString);
        console.log(toSending)
        sendData(toSending)
    }

    let board = [];
    
    for(let i = props.layout;i > 0;i--){
        for(let j = 0;j < props.layout;j++){
            // console.log(props.layout)
            let s = (i-1).toString();
            let q = j.toString();
            let p = q.concat(s);
            board.push(
            <button id={p} k={p} className = "tile"
            onClick={(e) => clickCoor(e.target.id)}></button>)
        
        }
    }
    return <div id="Board">{board}</div>
}

export default Board;