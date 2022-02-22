import Board from './component/Board/Board';
import Menu from './component/Menu';
import './App.css';

function App() {
  return (
    <div id="app">
      <h1 className='text-sky-400/100'>Hello ViRuS</h1>
      
      <Menu/>
      <Board/>
        
    </div>
  );
}

export default App;
