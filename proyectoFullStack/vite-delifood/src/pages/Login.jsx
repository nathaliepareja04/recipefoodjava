import React, {useState} from "react";
import {useNavigate} from 'react-router-dom';
import {useUser} from '../context/UserContext';
import Swal from "sweetalert2";
import '../styles/Login.css';


export const Login=()=>{

    const navigate=useNavigate()
    const [email,setEmail]=useState("")
    const [password,setPassword]=useState("")

    const {setIsLogin}=useUser()

    const [userForm,setUserForm]=useState({
        email:"",
        password:""
    })

    const handleChange=(e)=>{
        setUserForm({...userForm,[e.target.name]:e.target.value})
    }

    const loginUser=(e)=>{

        e.preventDefault()

        if(userForm.email==="admin@admin.com" && userForm.password==="admin"){
            Swal.fire({
                title: 'Bienvenid@ Chefcit@',
                imageUrl: 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/Chef_icon.svg/1500px-Chef_icon.svg.png',
                imageWidth: 200,
                imageHeight: 400,
                imagePosition: "top-center",
                imageAlt: 'Custom image',
                showConfirmButton: false,
                timer: 4000
            })
            localStorage.setItem("login",true); setIsLogin(true); navigate("/home");
        }else{
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Usuario o contraseña incorrecto',
                showConfirmButton: false,
                timer: 3000
            })
        }
    }

    return (
        <div id="body">
                    <div className="container-fluid position-absolute top-50 start-150 translate-middle-y">
                    <div className="user_card">
                    <div className="d-flex justify-content-center">
                    <div className="brand_logo_container">
                        <img src="public\img\iconfood.png" className="brand_logo" alt="Logo" />
                    </div>
                    </div>
                    <div className="d-flex justify-content-center form_container">
                    <form onSubmit={loginUser}>

                        <div className="input-group mb-3">
                        <div className="input-group-append">
                            <span className="input-group-text"><i className="fas fa-user" /></span>
                        </div>

                        <input type="email" name="email" className="form-control input_user" autoFocus  placeholder="Email" onChange={(e)=>handleChange(e)}/>
                        </div>

                        <div className="input-group mb-2">
                        <div className="input-group-append">
                            <span className="input-group-text"><i className="fas fa-key" /></span>
                        </div>

                        <input type="password" name="password" className="form-control input_pass" autoFocus placeholder="Password" onChange={(e)=>handleChange(e)}/>
                        </div>
                        
                        <div className="form-group">
                        <div className="custom-control custom-checkbox">
                            <input type="checkbox" className="custom-control-input" id="customControlInline" />
                            <label className="custom-control-label text-white" htmlFor="customControlInline">Remember me</label>
                        </div>
                        </div>
                        <div className="d-flex justify-content-center mt-3 login_container">
                        <button type="submit" name="button" className="btn btn-outline-dark rounded-pill login_btn text-white">Login</button>
                        </div>
                    </form>
                    </div>
                </div>
        </div>

        </div>
        
    )
}