import React from "react";
import { Link} from "react-router-dom";
import "../styles/Card.css"

export const Card = ({receta}) => {
  return (

    <Link to={`/description/${receta.id}`} key={receta.id} className="col-md-4 my-3 text-decoration-none">

      <div className="card-group h-100">
        <div className="card d-flex rounded-4">
          <div className="card-header">
            <img src={receta.imageUrl} alt="img" />
          </div>
          <div className="card-body">
            <h3 className='fw-bold text-start mt-4'><strong>{receta.nombre}</strong></h3>
            <p className='fst-italic my-2 text-end'>{receta.categoria.nombre}</p>
            <p className='fst-italic my-2 text-end'>{receta.pais.nombre}</p>

          </div>
        
        </div>
      </div>

    </Link>
  )
}
