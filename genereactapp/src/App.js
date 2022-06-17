import 'bootstrap/dist/css/bootstrap.min.css';
import "./App.css";
import GeneList from "./components/GeneList";
import { Container } from "react-bootstrap";

const App = () => {
  return (
    <div className="App">
      <Container className='mb-4 pb-4 pt-4'>
        <div className='d-flex justify-content-center align-items-center w-100 mb-3'><img height="150" src='/logo_geneius.jpg' alt="logo GENEius" /></div>
        <h3 className='w-100 text-center'>The best GENE database in the world!</h3>
      </Container>
      <GeneList />
    </div>
  );
};

export default App;
