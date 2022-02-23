import Board from './component/Board/Board';
import Menu from './component/Menu';
import Title from './component/title/Title';
import './App.css';
import { BrowserRouter as Router, Switch,Route,Link} from "react-router-dom";
import { useState } from 'react';

function App() {

  const [inputData,setInputdata] = useState(0)
  const [grid,setGrid] = useState(0)


  const inputOnchange = (e) =>{
    setInputdata(e.target.value)
    console.log(inputData)
  }

  const createGrid = () => {
    setGrid(inputData)
    console.log(inputData)
  }


  return (
    
    <div className='bg-white'>

      <div className='flex justify-center pt-10 pb-5 '>
        <Title/>
      </div>

      <div className='flex justify-center'>
        <input type="number" className='border-2 rounded-md p-1 placeholder:italic placeholder:text-slate-400' 
        placeholder="input M x N" onChange={inputOnchange}></input>
      </div>

      <div className='flex justify-center pt-3'>
        <button className='border-2 border-lime-500 border-double rounded px-5'
        onClick={createGrid}>Start</button>
      </div>
      

      <div className='flex justify-center pt-7 '>
        <Menu/>
        <Board layout={grid}/>
      </div>
        
    </div>
  );
}

export default App;
