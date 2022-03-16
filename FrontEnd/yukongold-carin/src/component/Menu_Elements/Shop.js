import axios from 'axios';
import './Shop.css';

export default function Credit() {

    const clickToSendData = (e) => {
        console.log(e)
    }

    const sendData = (e) => {
        axios.post("http://localhost:8080/ABSelected" , {
            spawn : true,
            type : e,
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
        <div id="">
            {/* <img className="image"
                src="/images/Shop.png"
            /> */}
            <div className='flex place-content-center space-x-2 pb-5'>


                <button onClick={() => {sendData(0)}}>
                    <img className=''
                        src='/images/KillerTCell48px.png' />
                </button>
                <button onClick={() => {sendData(1)}}>
                    <img className=''
                        src='/images/Marcophage48px.png' />
                </button>
                <button onClick={() => {sendData(2)}}>
                    <img className=''
                        src='/images/Neutropil48px.png' />


                </button>
            </div>


        </div>
    )
}