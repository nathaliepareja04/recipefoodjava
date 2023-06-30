import React from 'react'
import  "../styles/Search.css";

export const Search = ({search, inputSearch}) => {
  return (
    <div>
        <section className="col-xl-8 form-group mx-auto">
        <h1 className="text-center fw-bold fst-italic text-black">Recetas</h1>

        <div className="input-group">
            <div className="form-outline"/>
                <input type="text" class="form-control" placeholder="Search" value={inputSearch}
                onChange={(e) => search(e)}/>
                
            <button type="button" class="btn btn-primary">
                <i className="fas fa-search"></i>
            </button>
        </div>

        </section>
    </div>
  )
}
