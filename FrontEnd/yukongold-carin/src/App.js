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

  const start = () => {
    axios.get("http://localhost:8080/start")
      .then(res => {
        console.log(res.data)
      })
  }

  const inputOnchange = (e) => {
    setInputdata(e.target.value)
    console.log(inputData)
  }

  const createGrid = () => {
    setGrid(inputData)
    console.log(inputData)
    start();
    setButtondisable(false)
    setInputDisable(false)
  }


  return (

    <div className='bg-white'>

      <div className='flex justify-center pt-10 pb-5 '>
        <Title />
      </div>

      <div className='flex justify-center'>
        {inputDisabled ?
          <input
            type="number"
            className='border-2 rounded-md p-1 placeholder:italic placeholder:text-slate-400'
            placeholder="input m x n"
            onChange={inputOnchange}
          >
          </input> : null}
      </div>

      <div className='flex justify-center pt-3'>
        {buttonDisabled ?
          <button
            className='border-2 border-lime-500 border-double rounded px-5'
            onClick={createGrid}
          >
            Start
          </button> : null}

      </div>


      <div className='flex justify-center pt-7'>
        <Menu />
        <Board layout={grid} />
      </div>

    </div>
  );
}

export default App;
