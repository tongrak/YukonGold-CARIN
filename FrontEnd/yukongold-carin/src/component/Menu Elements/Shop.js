import axios from 'axios';
import './Shop.css';

export default function Credit() {

    const clickToSendData = (e) => {
        console.log(e)
    }

    const sendData = (e) => {
        axios.post("http://localhost:8080/ABSelected" , {
            type : e,
        })
        .then(res => {
            console.log(res.data)
            if(e == 1) console.log("Type : KillerTCell");
            else if(e == 2) console.log("Type : Marcophage");
            else if(e == 3) console.log("Type : Neutropil");
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
            <div className='place-content-center space-x-4 pt-10'>
                <button onClick={() => {sendData(1)}}>
                    <img className=''
                        src='/images/KillerTCell48px.png' />
                </button>
                <button onClick={() => {sendData(2)}}>
                    <img className=''
                        src='/images/Marcophage48px.png' />
                </button>
                <button onClick={() => {sendData(3)}}>
                    <img className=''
                        src='/images/Neutropil48px.png' />
                </button>
            </div>


        </div>
    )
}