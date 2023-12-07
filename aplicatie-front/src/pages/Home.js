import React from 'react'
import Navbar from "../components/Navbar";
import Announcement from "../components/Announcement";
import Slider from "../components/Slider";
import {useLocation} from "react-router-dom";
import Categories from "../components/Categories";
import Products from "./Products";
const Home=()=>{
    /*const location = useLocation();
    function f() {
        if(location.state.userId !== null)
            console.log(location.state.userId);
        else {
            console.log("nu avem id");
        }
    }
    {f()}
*/
    return(
        <div>
            <Announcement/>
            <Navbar/>
            <Slider/>
            <Categories/>
            <Products userId={-1}/>
        </div>
    )
}

export default Home