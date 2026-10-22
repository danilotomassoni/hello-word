import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    stages: [
        { duration: '10s', target: 30 },  
        { duration: '20s', target: 100 }, 
        { duration: '10s', target: 0 },   
    ],
    thresholds: {
        http_req_failed: ['rate<0.01'],   
        http_req_duration: ['p(95)<150'], 
    },
};

// Como usamos network_mode host, o localhost aponta diretamente para o seu NGINX local
const TARGET_URL = 'http://localhost/api/v1/hello';

export default function () {
    const res = http.get(TARGET_URL);

    const statusOk = res.status === 200;
    const bodyOk = res && res.body && res.body.includes('Hello World');

    check(res, {
        'status é 200 OK': () => statusOk,
        'conteúdo contém Hello World': () => bodyOk,
    });

    sleep(Math.random() * 0.02 + 0.01);
}
