import './Pause.css';
import axios from "axios"

const clickPause = () =>{
    console.log("Paused")
    getPause();
}

const getPause = () => {
    axios.get("http://localhost:8080/start" , {crossdomain: true})
    .then(res => {
        console.log(res.data)
    })
}

export default function Credit(){
    return(
    <div id= "Pause">
        <button onClick={clickPause}>
            <img className="image" src = "/images/Pause.png"/>
        </button>
    </div>
    )
}