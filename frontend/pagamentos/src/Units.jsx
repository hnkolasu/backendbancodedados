

import axios from 'axios';
import { useEffect, useState } from 'react';
import { api, getUnits } from './api';



function Units() {

    

    const [units, setUnits] = useState([])
    useEffect(() => {
            api.get('/v1/unit/find').then(function (response) {
              console.log(response.data)
              setUnits(response.data)
            }).catch(function (error) {
              console.log(error);
            })
    }, [])
    console.log("asdas", units)
    return (
      <div className="text-yellow-200 bg-slate-50">
        {units?.map((unit)=>{
            return <div className="text-lg text-yellow-300">
            {unit.block}
          </div>
        })}
      </div>
    );
  }
  
  export default Units;
  