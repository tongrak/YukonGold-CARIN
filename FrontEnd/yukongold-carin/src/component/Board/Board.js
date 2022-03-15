import React, { useState } from 'react';
import './Board.css';
import axios from 'axios';
import '../Menu Elements/Pause'


function Board(props) {

    const img = ["/images/Alpha48px.png", "/images/Beta48px.png", "/images/Gamma48px.png"]
    const [piture, setPiture] = useState();
    const [spawn, setSpawn] = useState(false);
    const [playing, setPlaying] = useState(false)
    const [gameData,setGameData] = useState()
    // const [isFirst,setIsFirst] = useState(true);


    while (playing) {

        
        axios.get("http://localhost:8080/getdata", { crossdomain: true })
            .then(res => {
                console.log(res.data)
                setGameData(res.data)
            })
            .catch(err => {
                console.log(err)
            })

    }

    const sendData = (e) => {
        axios.post("http://localhost:8080/click", {
            id: 1,
            coor: e
        })
            .then(res => {
                console.log(res.data)
            })
            .catch(err => {
                console.log(err)
            })
    }

    const spawnAB = () => {
        axios.get("http://localhost:8080/spawn", { crossdomain: true })
            .then(res => {
                setPiture(res.data.type)
                console.log("Spawn Type: " + res.data.type + " Coor : " +
                    res.data.coor.x + "," + res.data.coor.y)
            })
    }

    const clickCoor = (e) => {
        let ts = document.getElementById("Pause")
        console.log(ts)
        //if AB is spawn
        if (spawn) {
            spawnAB();
            let pp = e.concat("0")
            let setP = document.getElementById(pp)
            setP.setAttribute('src', img[piture])
            console.log(setP)
        }

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

    for (let i = props.N; i > 0; i--) {
        for (let j = 0; j < props.M; j++) {
            let s = (i - 1).toString();
            let q = j.toString();
            let p = q.concat(s);
            let pp = p.concat("0")
            board.push(
                <button id={p} className="tile"
                    style={{
                        width: k / l + "px",
                        height: k / l + "px"
                    }}
                    onClick={(e) => clickCoor(e.target.id)}>
                    <img id={pp} src=''></img>
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