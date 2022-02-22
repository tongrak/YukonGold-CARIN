import Board from './component/Board/Board';
import Menu from './component/Menu';
import Title from './component/title/Title';
import './App.css';

function App() {
  return (
    
    <div id="app" className='bg-white'>


      <div className=''>
        <div className='pt-10 pb-6 px-72'>
          <Title/>
        </div>
          
        <div className='pt-10 flex px-10'>
          <Menu/>
          <Board/>
        </div>
        
      </div>
      
      
        
    </div>
  );
}

export default App;
