import axios from 'axios';
import { useEffect, useState } from 'react';
import { api } from './api';

function Units() {

    const [pagamentos, setPagamentos] = useState([])
    useEffect(() => {
            api.get('/v1/payment/find').then(function (response) {
              console.log(response.data)
              setPagamentos(response.data)
            }).catch(function (error) {
              console.log(error);
            })
    }, [])

    const deletarPagamento = (id)=>{
      api.get(`/v1/payment/delete/${id}`).then(function (response) {
        alert("Deletado com sucesso")

      }).catch(function (error) {
        console.log(error);
      })
    }

    return (
      <div className="text-white">
        {pagamentos?.map((el)=>{
            return <div className="text-lg border-2 border-slate-400 rounded  m-4 p-4">
              <div>

              <div>id : {el.id}</div>
              <div>debtorName : {el.debtorName}</div>
              <div>paymentDate : {el.paymentDate}</div>
              <div>referenceMonth : {el.referenceMonth}</div>
              <div>referenceYear : {el.referenceYear}</div>
              <div>registerDate : {el.registerDate}</div>
              <div>unitId : {el.unitId}</div>
              </div>
              <div className='mt-4'>
              <button onClick={()=>deletarPagamento(el.id)}>Button</button>

              </div>
          </div>
        })}
      </div>
    );
  }
  
  export default Units;
  