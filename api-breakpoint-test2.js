import http from 'k6/http';

export default function () {
    const urlRes = http.get('http://localhost:8080/medico/1');
    sleep(1);
  
  }

  export const options = {
    stages: [
    { duration: '10m', target: 100000 }, // just slowly ramp-up to a HUGE load
    ],
    thresholds: {
    http_req_failed: [{
    threshold: 'rate<=0.01',
    abortOnFail: true,
    }]}
    };