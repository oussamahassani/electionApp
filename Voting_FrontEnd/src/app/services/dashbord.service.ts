
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import baseUrl from './helper';


@Injectable({
    providedIn: 'root',
})
export class DashbordService {
    constructor(private http: HttpClient) { }

    getDashbordInfo(): Observable<any> {

        return this.http.get<any>(`${baseUrl}/user/dashbordInfo`
        );
    }
    getElectionDashbordInfo(id: any): Observable<any> {

        return this.http.get<any>(`${baseUrl}/elections/dashbordInfo/${id}`
        );
    }


}

