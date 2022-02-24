import React, { useState } from 'react';
import './Board.css';
import axios from 'axios';


function Board(props) {

    const sendData = (e) => {
        axios.post("http://localhost:8080/click", {
            id: 1,
            coor: e
        })
            .then(res => {
                console.log(res)
            })
            .catch(err => {
                console.log(err)
            })
    }

    const clickCoor = (e) => {
        let fst = Math.floor(e / 10)
        let snd = Math.floor(e % 10)
        let fstSring = fst.toString();
        let sndString = snd.toString();
        let toSending = fstSring.concat(",", sndString);
        console.log(toSending)
        sendData(toSending)
    }

    let board = [];

    for (let i = props.layout; i > 0; i--) {
        for (let j = 0; j < props.layout; j++) {
            let s = (i - 1).toString();
            let q = j.toString();
            let p = q.concat(s);
            board.push(
                <button id={p} className="tile"
                    style={{
                        width: 480 / props.layout + "px",
                        height: 480 / props.layout + "px"
                    }}
                    onClick={(e) => clickCoor(e.target.id)}></button>)

        }
    }
    return <div id="Board"
        style={{
            gridTemplateColumns: "repeat(" + props.layout + ", " + 480 / props.layout + "px)",
            gridTemplateRows: "repeat(" + props.layout + ", " + 480 / props.layout + "px)",
        }}>{board}</div>
}

export default Board;