import { FieldValues } from "react-hook-form";

export function sendRequestPOST(data: FieldValues, url: string) {
    // let url = "http://localhost:8080/employees/login";
    url = 'http://localhost:8080/' + url
    return fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `${localStorage.getItem("token")}`
        },
        body: JSON.stringify(data)
    });
}

export function sendRequestGET(url: string) {
    // let url = "http://localhost:8080/employees/login";
    url = 'http://localhost:8080/' + url
    return fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `${localStorage.getItem("token")}`
        }
    });
}