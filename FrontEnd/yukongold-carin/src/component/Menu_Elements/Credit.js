import { useState } from 'react';
import './Credit.css';

export default function Credit(){

    const [money,setMoney] = useState(1000);

    return (
        <div  className="flex justify-center pt-5 pb-5">
            <a className='text-xl'>{money}</a>
            {/* <img className="image" src = "/images/Credits.png"/> */}
        </div>
    )
}