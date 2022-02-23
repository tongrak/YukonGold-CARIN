import './Pause.css';
import axios from "axios"
import { useState } from 'react';


export default function Credit(){
    const [pause,setPaues] = useState('/images/UnPause.png')


    const clickPause = () =>{
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
    }

    return(
    <div id= "Pause">
        <button onClick={clickPause}>
            <img className="image" src = {pause}/>
        </button>
    </div>
    )
}