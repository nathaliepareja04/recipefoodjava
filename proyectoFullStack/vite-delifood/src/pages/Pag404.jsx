import React from 'react'
import { Link } from "react-router-dom";
import '../styles/Pag404.css';

export const Pag404 = () => {
  return (
    <div id="page">
    <div className="container justify-content-center align-items-center text-center">
      <h1><strong>Error 404: Page Not Found</strong></h1>
      <Link to="/" className="btn btn-outline-info text-white mt-3 fw-bold rounded-pill px-4">
        Volver a Login
      </Link>
    </div>
    </div>
  )
}
