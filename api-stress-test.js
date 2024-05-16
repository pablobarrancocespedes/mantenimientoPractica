import http from 'k6/http';
import { sleep } from 'k6';


export default function () {
  const urlRes = http.get('http://localhost:8080/medico/1');
  sleep(1);

}

export const options = {
    stages: [
    { duration: '3m', target: 1455 }, // subimos a un carga de estres de 1364 vu en 2 minutos. Queremos un 80% de vu (teniamos 1701, pues ahora ponemos 1364)
    { duration: '3m', target: 1455 } ,
    { duration: '2m', target: 0 } 
    ],
    };