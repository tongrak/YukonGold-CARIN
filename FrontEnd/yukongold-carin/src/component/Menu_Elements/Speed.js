import './Speed.css';
import axios from 'axios';

const clickSpeed = () => {
    console.log("ClickSpeed")
    getSpeed();
}

const getSpeed = () => {
    axios.get("http://localhost:8080/speed" , {crossdomain: true})
    .then(res => {
        console.log("Speed :x" + res.data)
    })
}

export default function Credit(){
    return (
    <div id= "Speed">
        <button onClick={clickSpeed}>
            <img className="image" src = "/images/Speed.png"/>
        </button>
    </div>)
}