import React from "react";
import Header from "../commons/ProjectHeader";
import Footer from "../commons/ProjectFooter";

import "./NotFound.css";

const NotFound = () => {
  return (
    <div className="not-found-block">
      <Header />
      <h1> 404 - Not Found </h1>
      <footer>
        <Footer />
      </footer>
    </div>
  );
};

export default NotFound;
