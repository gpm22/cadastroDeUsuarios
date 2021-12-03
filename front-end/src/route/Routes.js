import React from "react";
import { Route, Routes, BrowserRouter } from "react-router-dom";

import Home from "../home/Home";
import About from "../about/About";
import Login from "../login/Login";

const MyRoutes = () => {
   return(
       <BrowserRouter>
       <Routes>
           <Route component = { Home }  path="/" exact />
           <Route component = { About }  path="/sobre" />
           <Route component = { Login }  path="/login" />
       </Routes>    
       </BrowserRouter>
   )
}

export default MyRoutes;