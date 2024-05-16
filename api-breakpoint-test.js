import http from 'k6/http';

export default function () {
    const urlRes = http.get('http://localhost:8080/medico/1');
    sleep(1);
  
  }

export const options = {
    scenarios: {
    breakpoint: {
    executor: 'ramping-arrival-rate'
    , // Incrementa la carga exponencial
    preAllocatedVUs: 100000, //VUs alocados inicialmente
    maxVUs: 1e7, //VUs maximo
    stages: [
    { duration: '10m', target: 100000 }, // just slowly ramp-up to a  HUGE load
    ]
    }
    },
    thresholds: {
    http_req_failed: [{
    threshold: 'rate<=0.01',
    abortOnFail: true,
    }]}
};
