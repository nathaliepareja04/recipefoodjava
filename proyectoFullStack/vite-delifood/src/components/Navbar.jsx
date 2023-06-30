import { NavLink } from "react-router-dom";
import { useUser } from "../context/UserContext";


export const Navbar = () => {

    const {isLogin,setIsLogin}=useUser()

    const Exit=()=>{
        setIsLogin(false)
        localStorage.setItem("login",false)
    }

  return (
    <nav className="navbar navbar-expand-lg text-white" style={{background: "#000"}}>
      <div className="container-fluid">
      <a className="navbar-brand" href="/img/iconfood.png"><img src="/img/iconfood.png" alt="img" width="40" height="50"/></a>

      <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"/>
      </button>

      <div className="collapse navbar-collapse" id="navbarSupportedContent">
        <div>
          
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
              <li className="nav-item">
                <NavLink className="nav-link text-white fw-bold" to={"/home"}>HOME</NavLink>
              </li>

              <li className="nav-item position-absolute top-2 end-0 me-3">
                <button className="btn btn-outline-danger rounded-pill px-4 text-white" onClick={()=>Exit()}><i className="fa-solid fa-arrow-right-from-bracket"></i></button>
              </li>
              
            </ul>
        </div>
      </div>
  </div>
</nav>
  )
}

