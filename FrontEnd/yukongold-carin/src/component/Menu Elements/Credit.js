import { useState } from 'react';
import './Credit.css';

export default function Credit(){

    const [money,setMoney] = useState(0);

    return (
        <div  className="flex justify-center pt-5">
            <a >{money}</a>
            {/* <img className="image" src = "/images/Credits.png"/> */}
        </div>
    )
}