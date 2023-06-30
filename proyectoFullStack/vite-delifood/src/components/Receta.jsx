import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import Swal from 'sweetalert2'
import { Loading } from './Loading'
import { Pag404 } from '../pages/Pag404'

export const Receta=()=>{

    const navigate=useNavigate()
    const{id}=useParams()
    const[receta,setReceta]=useState({})
    const[error,setError]=useState(false)
    const[loading,setLoading]=useState(false)

    useEffect(()=>{
        const searchReceta=async()=>{
            try {
                setLoading(true)
                const {data} =await axios.get(`http://localhost:8080/recetas/${id}`)
                setLoading(false)

                if(data.Error){
                    setError(true)
                    Swal.fire({
                        icon: 'error',
                        title: 'Receta no encontrada por el id',
                        position: center,
                        showConfirmButton: false,
                        timer:3000
                      })
                    return
                }
                setReceta(data.data)
            } catch (error) {
                setLoading(false)
                console.log("Ha sucedido un error al encontrar la receta",error.message)
            }
        }
        searchReceta()
    },[id])

    return (
        <div>
          {loading?(<Loading/>):
                (<div className="card-group h-100">
                    <div className="card mt-4 d-flex" >
                    {!error? (
                        <div className="row">
                            <div className="col-xxl-4 ">
                                <img src={receta.imageUrl} alt="img"></img>
                            </div>
                            <div className="col-xxl-8">
                                <div className="card-body">

                                    <div className='my-5'>
                                    <h2 className="card-title">
                                        <strong>{receta.nombre}</strong>
                                    </h2>

                                        <p><strong>Ingredientes</strong></p>
                                        <span className="card-text ">{receta.ingredientes}</span>

                                        <p><strong>Instrucciones</strong></p>
                                        <span className="card-text">{receta.instrucciones}</span>
                                        
                                    </div>

                                    <div className="d-flex justify-content-between align-items-center">
                                        <a href={receta.videoUrl} target='_blank' rel='noreferrer' className="btn btn-outline-info rounded-pill px-3 mx-3">Video</a>

                                        <button className="btn btn-outline-success rounded-pill px-3 mx-3" onClick={()=>navigate(-1)}>Volver</button>
                                    </div>
                                </div>
                            </div>
                      </div>
                    ): (
                        Swal.fire({
                            icon: 'error',
                            title: 'Receta no encontrada',
                            position: center,
                            showConfirmButton: false,
                            timer:3000
                          })
                    )}
                    </div>
                
                    
            </div>
            )}
            </div>
        );
    };

                    
                    
                    
                    
                    
                    
                    
                    
                    
                    {/* <div className="row">
                            <div className="col-xl-4">
                                <img src={receta.imageUrl} alt="img"/>
                            </div>
                            <div className="col-xl-8">
                                <div className="card-body">
                                    <h4 className="card-title">
                                        <strong>{receta.nombre}</strong>
                                    </h4>

                                    <div>
                                        <h6>Ingredientes</h6>
                                        <p className="card-text">{receta.ingredientes}</p>
                                        <h6>Instrucciones</h6>
                                        <p className="card-text">{receta.instrucciones}</p>
                                        <h6>Video</h6>
                                        <a href={receta.videoUrl} target='_blank' rel='noreferrer'>Ver vídeo</a>
                                    </div>

                                    <div className="d-flex justify-content-between">
                                        <button className="btn btn-outline-success rounded-pill" onClick={()=>navigate(-1)}>Volver</button>
                                    </div>
                                </div>
                            </div>
                        </div> */}
                        
 
