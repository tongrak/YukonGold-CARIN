import axios from 'axios';
import { useState } from 'react';
import './Shop.css';

export default function Credit() {

    

    const clickToSendData = (e) => {
        console.log(e)
    }

    const sendData = (e) => {
        axios.post("http://localhost:8080/ABSelected" , {
            spawn : true,
            type : e,
            credit : 100   //cost to spawn AB
        })
        .then(res => {
            console.log(res.data)
            if(e == 0) console.log("Type : KillerTCell");
            else if(e == 1) console.log("Type : Marcophage");
            else if(e == 2) console.log("Type : Neutropil");
        })
        .catch(err => {
            console.log(err)
        })
    }
    
    return (
        // <div id="">
        //     {/* <img className="image"
        //         src="/images/Shop.png"
        //     /> */}
        //     <div className='flex place-content-center space-x-2 pb-5'>


        //         <button onClick={() => {sendData(0)}}>
        //             <img className=''
        //                 src='/images/KillerTCell48px.png' />
        //         </button>
        //         <button onClick={() => {sendData(1)}}>
        //             <img className=''
        //                 src='/images/Marcophage48px.png' />
        //         </button>
        //         <button onClick={() => {sendData(2)}}>
        //             <img className=''
        //                 src='/images/Neutropil48px.png' />


        //         </button>
        //     </div>


        // </div>
        <div id="Shop">
            {/* <img className="image"
                src="/images/Shop.png"
            /> */}
                <div>
                    <div className='flex pt-5'>
                        <button className='flex pt-5 px-5' onClick={() => {sendData(0)}}>
                        <img className='flex flex-row justify-center'
                        src='/images/KillerTCell48px.png' />
                        </button>
                        <div>
                            <div className='flex pt-5'>
                            <p className='font-mono font-bold text-lg'>Killer T-Cell</p>
                            </div>
                            <div>
                            <p className='font-mono font-bold text-lg'>???? points</p>
                            </div>
                        </div>
                    </div>
                </div>
               
                <div>
                    <div className='flex pt-5'> 
                        <button className='flex px-5' onClick={() => {sendData(1)}}>
                        <img className='flex flex-row justify-center'
                        src='/images/Marcophage48px.png' />
                        </button>
                        <div>
                            <div className='flex'>
                            <p className='font-mono font-bold text-lg'>Marcophage</p>
                            </div>
                            <div>
                            <p className='font-mono font-bold text-lg'>???? points</p>
                            </div>
                        </div>
                    </div>  
                </div>
                
                <div>
                    <div className='flex pt-5'>
                        <button className='flex px-5' onClick={() => {sendData(2)}}>
                        <img className='flex flex-row justify-center'
                        src='/images/Neutropil48px.png' />
                        </button>
                        <div>
                            <div className='flex'>
                            <p className='font-mono font-bold text-lg'>Neutropil</p>
                            </div>
                            <div>
                            <p className='font-mono font-bold text-lg'>???? points</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    )
}