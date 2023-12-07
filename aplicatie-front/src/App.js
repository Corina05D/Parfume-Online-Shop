import Home from "./pages/Home"
import Register from "./pages/Register";
import Login from "./pages/Login";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Cart from "./pages/Cart";

const App = () => {

    return (<Router>
        <Routes>
            <Route path="/" exact element={<Home/>}/>
            <Route path="/login" exact element={<Login/>}/>
            <Route path="/register" exact element={<Register/>}/>
            <Route path="/cart" exact element={<Cart/>}/>
        </Routes>
    </Router>);
};

export default App;
