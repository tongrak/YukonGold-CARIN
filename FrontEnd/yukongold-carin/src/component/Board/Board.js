import React, { useState } from 'react';
import './Board.css';
import axios from 'axios';


function Board(props) {

    const img = ["/images/Alpha48px.png","/images/Beta48px.png","/images/Gamma48px.png"]
    const [piture,setPiture] = useState('');
    console.log("M = " + props.M)
    console.log("N = " + props.N)

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

    const clickCoor = (e,im) => {
        setPiture(img[im])
        let fst = Math.floor(e / 10)
        let snd = Math.floor(e % 10)
        let fstSring = fst.toString();
        let sndString = snd.toString();
        let toSending = fstSring.concat(",", sndString);
        console.log(toSending)
        sendData(toSending)
    }

    let board = [];

    let k = 480;
    let l = 10;
    if (props.M > props.N && props.N < l) {

    }

    for (let i = props.N; i > 0; i--) {
        for (let j = 0; j < props.M; j++) {
            let s = (i - 1).toString();
            let q = j.toString();
            let p = q.concat(s);
            board.push(
                <button id={p} className="tile"
                    style={{
                        width: k / l + "px",
                        height: k / l + "px"
                    }}
                    onClick={(e,im) => clickCoor(e.target.id,im)}>
                    <img src={piture}></img>
                </button>)

        }
    }
    return <div id="Board"
        style={{
            gridTemplateColumns: "repeat(" + props.M + ", " + k / l + "px)",
            gridTemplateRows: "repeat(" + props.N + ", " + k / l + "px)",
            width: "px",
            height: "px",
        }}>{board}</div>
}

export default Board;