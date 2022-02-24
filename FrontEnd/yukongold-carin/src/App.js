import Board from './component/Board/Board';
import Menu from './component/Menu';
import Title from './component/title/Title';
import './App.css';
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import { useState } from 'react';
import axios from 'axios';

function App() {

  const [inputData, setInputdata] = useState(0)
  const [grid, setGrid] = useState(0)
  const [buttonDisabled, setButtondisable] = useState(true)
  const [inputDisabled, setInputDisable] = useState(true)
  const [showBoard, setShowBoard] = useState(false)
  const [erroInput, setErrorInput] = useState(true)


  const start = () => {
    axios.get("http://localhost:8080/start")
      .then(res => {
        console.log(res.data)
      })
  }

  const inputOnchange = (e) => {
    setInputdata(e.target.value)
    if (e.target.value > 0) setErrorInput(false)
    else setErrorInput(true)
    console.log(inputData)
  }

  const createGrid = () => {
    setGrid(inputData)
    console.log(inputData)
    start();
    setButtondisable(false)
    setInputDisable(false)
    setShowBoard(true)
  }


  return (

    <div id='app' className='bg-white dark:bg-slate-900'>

      {/* Title */}
      <div className='flex justify-center pt-10 pb-5 '>
        <Title />
      </div>

      {/* Input Box */}
      <div className='place-content-center flex pt-5'>
        <div>
          {inputDisabled ?
            <input
              type="number"
              className='peer mt-1 block px-3 py-2 bg-white border border-slate-300 rounded-md text-sm shadow-sm placeholder-slate-400
            focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500
            disabled:bg-slate-50 disabled:text-slate-500 disabled:border-slate-200 disabled:shadow-none
            invalid:border-pink-500 invalid:text-pink-600
            focus:invalid:border-pink-500 focus:invalid:ring-pink-500'
              placeholder="input m x n"
              onChange={inputOnchange}
            >
            </input> : null}
          <p className='mt-2 invisible peer-invalid:visible text-pink-600 text-sm'>Please provide a valid Number.</p>
        </div>
      </div>


      {/* Start Button */}
      <div className='flex justify-center pt-10'>
        {buttonDisabled ?
          <button
            className={'rounded px-5 bg-emerald-600 hover:bg-emerald-700 text-white'}
            onClick={createGrid}
            disabled={erroInput}
          >
            Start
          </button> : null}
      </div>


      {/* Board and Menu */}
      <div
        className='flex justify-center'>
        {showBoard ? <Menu /> : null}
        {showBoard ? <Board layout={grid} /> : null}
      </div>

    </div>
  );
}

export default App;
