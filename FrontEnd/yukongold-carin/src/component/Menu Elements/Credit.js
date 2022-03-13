import { useState } from 'react';
import './Credit.css';

export default function Credit(){

    const [money,setMoney] = useState(0);

    return (
        <div id= "Credit">
            <a className="image">{money}</a>
            {/* <img className="image" src = "/images/Credits.png"/> */}
        </div>
    )
}