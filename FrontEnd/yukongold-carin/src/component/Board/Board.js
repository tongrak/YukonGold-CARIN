import React, { useEffect, useState } from 'react';
import './Board.css';
import axios from 'axios';
import '../Menu Elements/Pause'


function Board(props) {

    const img = ["/images/KillerTCell48px.png", "/images/Marcophage48px.png", "/images/Neutropil48px.png"]
    const [piture, setPiture] = useState();
    const [spawn, setSpawn] = useState(false);
    const [playing, setPlaying] = useState(false)
    const [gameData, setGameData] = useState()
    const [isFirst, setIsFirst] = useState(true);
    const [speed, setSpeed] = useState(10000000000000000)


    const [count, setCount] = useState(0);

    // Loop game
    useEffect(() => {
        setTimeout(() => {
            setCount((count) => count + 1);
            axios.get("http://localhost:8080/getdata", { crossdomain: true })
                .then(res => {
                    console.log(res.data)
                    setGameData(res.data)
                    setSpeed((res.data.speed) * 1000)
                    console.log(speed)
                })
                .catch(err => {
                    console.log(err)
                })
        }, speed);
        console.log(count)
    });

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

    const spawnAB = (e) => {
        axios.get("http://localhost:8080/spawn", { crossdomain: true })
            .then(res => {
                if (res.data.spawn) {
                    // setSpawn(res.data.spawn)
                    setPiture(res.data.type)
                    
                    console.log("Spawn Type: " + res.data.type + " Coor : " +
                        res.data.coor.x + "," + res.data.coor.y)
                    let pp = e.concat("0")
                    let setP = document.getElementById(pp)
                    console.log(piture)
                    setP.setAttribute('src', img[piture])
                    console.log(setP)
                    setSpawn(false)
                }

            })
    }

    const clickCoor = (e) => {

        // let ts = document.getElementById("Pause")
        // console.log(ts)
        //if AB is spawn
        
        // if (spawn) {
        //     let pp = e.concat("0")
        //     let setP = document.getElementById(pp)
        //     setP.setAttribute('src', img[piture])
        //     console.log(setP)
        //     setSpawn(false)
        // }

        let fst = Math.floor(e / 10)
        let snd = Math.floor(e % 10)
        let fstSring = fst.toString();
        let sndString = snd.toString();
        let toSending = fstSring.concat(",", sndString);
        console.log(toSending)
        sendData(toSending)
        spawnAB(e);
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