import http from 'k6/http';
import { sleep } from 'k6';


export default function () {
  const urlRes = http.get('http://localhost:8080/medico/1');
  sleep(1);

}

export const options = {
    scenarios: {
    spike: {
    executor: 'ramping-arrival-rate'
    , // rampa!!
    preAllocatedVUs: 1000, //VUs alocados inicialmente
    maxVUs: 1e7, //VUs maximo
    stages: [
    { duration: '2m', target: 720 }, // rampa muy rápida
    { duration: '1m', target: 0 }, // baja muy rapid0
    ],
    }
    }
    };