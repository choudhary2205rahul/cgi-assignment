import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Logs} from "../domain/logs";

@Injectable({
  providedIn: 'root'
})
export class LogsService {
  constructor(public http: HttpClient) { }

  getLogs(input: string): Observable<Logs> {
    const href = 'http://localhost:8080/api/v1/logs';
    const requestUrl = `${href}/${input}`;
    return this.http.get<Logs>(requestUrl);
  }
}
