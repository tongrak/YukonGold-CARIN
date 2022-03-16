import { useState } from 'react';
import './Credit.css';

export default function Credit(){

    const [money,setMoney] = useState(1000);

    return (
        <div  className="flex justify-center pt-5 pb-5 rounded-full border-2 border-orange-300 text-4xl">
            <a className='text-4xl'>{money}</a>
            {/* <img className="image" src = "/images/Credits.png"/> */}
        </div>
    )
}