import './Pause.css';
import axios from "axios"
import { useState } from 'react';


export default function Credit(){
    const [pause,setPaues] = useState('/images/UnPause.png')
    const [isFirst,setIsFirst] = useState(true)
    const [gameData,setGameData] = useState()

    const clickPause = () =>{
        // if(isFirst){
        //     setIsFirst(false)
        //     axios.get("http://localhost:8080/getdata" , {crossdomain: true})
        //     .then(res => {
        //         console.log(res.data)
        //         setGameData(res.data)
        //     })
        //     .catch(err => {
        //         console.log(err)
        //     })
        // }
        getPause();
    }

    const getPause = () => {
        axios.get("http://localhost:8080/pause" , {crossdomain: true})
        .then(res => {
            console.log("Pause is " + res.data)
            if(res.data){
                console.log("Paused")
                setPaues("/images/UnPause.png")
            }else{
                console.log("Playing")
                setPaues("/images/pause.png")
            }
        })
        .catch(err => {
            console.log(err)
        })
    }

    return(
    <div id= "Pause" data={gameData}>
        <button onClick={clickPause}>
            <img className="image" src = {pause}/>
        </button>
    </div>
    )
}