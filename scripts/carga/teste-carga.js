import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    scenarios: {
        hello_world: {
            executor: 'ramping-vus',
            startVUs: 0,
            stages: [
                { duration: '10s', target: 30 },
                { duration: '20s', target: 100 },
                { duration: '30s', target: 100 },
                { duration: '10s', target: 0 },
            ],
            gracefulRampDown: '10s',
        },
    },
    thresholds: {
        checks: ['rate==1'],
        http_req_failed: ['rate<0.01'],
        http_req_duration: ['p(95)<40'],
    },
};

const TARGET_URL = __ENV.TARGET_URL || 'http://localhost/api/v1/hello';

export default function () {
    const res = http.get(TARGET_URL, {
        headers: { Accept: 'text/plain' },
        tags: { endpoint: 'hello' },
        timeout: '5s',
    });

    check(res, {
        'status é 200 OK': (response) => response.status === 200,
        'resposta contém o texto esperado': (response) => response.body === 'Hello World!',
    });

    sleep(0.01);
}
