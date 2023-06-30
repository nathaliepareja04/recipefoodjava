import {BrowserRouter,Routes,Route,Navigate} from 'react-router-dom';
import { useUser } from "./context/UserContext";
import { Home } from "./pages/Home";
import { Login } from './pages/Login';
import { Navbar } from './components/Navbar';
import { Pag404 } from './pages/Pag404';
import { Receta } from './components/Receta';

function App() {
    const {isLogin}=useUser()

    const PrivateRoute=({children})=>{
        return isLogin? children : <Navigate to="/"/>
    }

    const PublicRoute=({children})=>{
        return isLogin? <Navigate to="/home"/> : children
    }

    return(
        <div>
            <BrowserRouter>
            
                <Routes>

                    <Route path="/" element={
                        <PublicRoute>
                            <Login/>
                        </PublicRoute>
                    }/>
                    
                    <Route path="/home" element={
                        <PrivateRoute>
                            <Navbar/>
                            <Home/>
                        </PrivateRoute>
                    }></Route>

                    <Route path='/description/:id' element={
                        <PrivateRoute>
                            <Receta/>
                        </PrivateRoute>
                    }></Route>

                    <Route path="*" element={
                        <Pag404/>
                    }/>

                </Routes>

            </BrowserRouter>

           
        </div>
    )
}

export default App
