import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from '../helper';

@Injectable({
  providedIn: 'root'
})

export class ElectionService {



  constructor(private http: HttpClient) { }

  public addElection(data: any) {
    return this.http.post(`${baseUrl}/elections/addNew`, data);
  }

  public updateElection(data: any) {
    let tokenStr: any = localStorage.getItem("token");

    const headers = new HttpHeaders({
      'Authorization': tokenStr,

    });
    console.log(headers)
    return this.http.put(`${baseUrl}/elction/update`, data);
  }

  public getAllElectionData() {
    return this.http.get(`${baseUrl}/elections/`);
  }
  public getAllElectionDataNotStrating() {
    return this.http.get(`${baseUrl}/elections/notNow`);
  }
  public getElectionData(eid: any) {
    return this.http.get(`${baseUrl}/elections/${eid}`);
  }

  public deleteElectionData(eid: any) {
    return this.http.delete(`${baseUrl}/elections/${eid}`);
  }
}
