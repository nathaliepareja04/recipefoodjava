import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import "../styles/Home.css"
import { Card } from "../components/Card";
import { Loading } from "../components/Loading";
import { Search } from "../components/Search";
import "../styles/Card.css"



export const Home = () => {
    const navigate = useNavigate();

    const [params] = useSearchParams();
  
    const [recetas, setRecetas] = useState([]);
  
    const [inputSearch, setInputSearch] = useState("");
  
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const q = params.get("q") || "";
        setInputSearch(q);
        q === "" ? setRecetas([]) : getRecetas(q);
      }, [params]);
    
      const getRecetas = async (q) => {
        try {
          setLoading(true);
          const { data } = await axios.get(
            `http://localhost:8080/recetas/search?nombre=${q}`
          );
          setRecetas(data);
          setLoading(false);
        } catch (error) {
          setLoading(false);
          console.log("Ha sucedido un error al obtener las recetas", error.message);
        }
    };
    
    const search = (e) => {
        if (e.target.value === "") {
          return clean();
        }
    
        setInputSearch(e.target.value);
        getRecetas(e.target.value);
        navigate(`?q=${e.target.value}`);
    };
    
    const clean = () => {
        setRecetas("");
        setInputSearch("");
        navigate("");
    };

    return (
        <div id="body">
          {/* Search */}
          <Search search={search} inputSearch={inputSearch}/>

          {/* Recetas */}
          {loading ? (
            <Loading />
          ) : (
            <section className="row">
              {recetas ? (
                recetas.map((receta) => (
                  <Card receta={receta}/>
                ))
              ) : (
                Swal.fire({
                    icon: 'error',
                    title: 'Receta no encontrada',
                    position: center,
                    showConfirmButton: false,
                    timer:3000
                  })
              )}
            </section>
          )}
        </div>
      );
    
  
}
    
    
