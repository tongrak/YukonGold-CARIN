import './Speed.css';
import axios from 'axios';
import { useState } from 'react';



export default function Credit(){


    const clickSpeed = () => {
        console.log("ClickSpeed")
        getSpeed();
    }
    
    const img = ["/images/SpeedX1.png","/images/SpeedX2.png"]
    const [pic,setPic] = useState(0)
    
    const getSpeed = async() => {
        await axios.get("http://localhost:8080/speed" , {crossdomain: true})
        .then(res => {
            console.log("Speed : x" + res.data)
            if(res.data == 1){
                setPic(2)
            }else if(res.data == 2){
                setPic(1)
            }else{
                setPic(1)
            }
        })
    }

    return (
    <div id= "Speed" className='pb-5 flex place-content-center'>
        <button onClick={clickSpeed} className='button'>
            <img className="image" src = {img[pic]}/>
        </button>
    </div>)
}