import React, { useEffect, useState } from 'react';
import './Board.css';
import axios from 'axios';
import '../Menu_Elements/Pause'


function Board(props) {

    const img = ["/images/KillerTCell48px.png", "/images/Marcophage48px.png", "/images/Neutropil48px.png"]
    
    const [spawn, setSpawn] = useState(false);
    const [gameData, setGameData] = useState()
    const [speed, setSpeed] = useState(5000)
    // const [isFirst, setIsFirst] = useState(true);
    // const [piture, setPiture] = useState();
    // const [count, setCount] = useState(0);

    const getTest = async() => {
        await axios.get("http://localhost:8080/test", { crossdomain: true })
                .then(res => {
                    console.log(res.data)
                })
                .catch(err => {
                    console.log(err)
                })
    }


    const getData = async() => {
        await axios.get("http://localhost:8080/getdata", { crossdomain: true })
                .then(res => {
                    console.log(res.data)
                    setGameData(res.data)
                    console.log(res.data.dataLL)
                    setSpeed((res.data.speed) * 1000)
                    console.log("speed : " + speed)
                })
                .catch(err => {
                    console.log(err)
                })
    }
    // Loop game
    useEffect(() => {
        
        const interval = setInterval(() => {
            getData()
            getTest()
        }, speed)
        return () => clearInterval(interval)

    });

    const sendData = async(e) => {
        await axios.post("http://localhost:8080/click", {
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

    const spawnAB = async(e) => {
        await axios.get("http://localhost:8080/spawn", { crossdomain: true })
            .then(res => {
                // setPiture(res.data.type)
                if (res.data.spawn) {
                    // setSpawn(res.data.spawn)
                    console.log("Spawn Type: " + res.data.type + " Coor : " +
                        res.data.coor.x + "," + res.data.coor.y + "Cost : " + res.data.credit)
                    let pp = e.concat("0")
                    let setP = document.getElementById(pp)
                    
                    console.log("img : " + res.data.type)
                    setP.setAttribute('src', img[res.data.type])
                    console.log(setP)
                    setSpawn(false)
                }
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