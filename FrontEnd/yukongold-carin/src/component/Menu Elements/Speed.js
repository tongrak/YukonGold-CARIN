import './Speed.css';

const clickSpeed = () => {
    console.log("ClickSpeed")
}


export default function Credit(){
    return (
    <div id= "Speed">
        <button onClick={clickSpeed}>
            <img className="image" src = "/images/Speed.png"/>
        </button>
    </div>)
}