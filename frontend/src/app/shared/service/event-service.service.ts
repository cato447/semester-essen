import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Event } from '../models/event';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class EventService {

  apiUrl = "http://localhost:8080/api";

  constructor(private http: HttpClient) {}

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  getEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(this.apiUrl + '/events').pipe(retry(1), catchError(this.handleError));
  }

  getEvent(id: any): Observable<Event> {
    return this.http.get<Event>(this.apiUrl + '/events/' + id).pipe(retry(1), catchError(this.handleError));
  }

  createEvent(event: any): Observable<Event> {
    return this.http.post<Event>(this.apiUrl + '/events', JSON.stringify(event), this.httpOptions).pipe(retry(1), catchError(this.handleError));
  }

  updateEvent(id: any, event: any): Observable<Event> {
    return this.http.put<Event>(this.apiUrl + '/events/' + id, JSON.stringify(event), this.httpOptions).pipe(retry(1), catchError(this.handleError));
  }

  deleteEvent(id: any): Observable<Event> {
    return this.http.delete<Event>(this.apiUrl + '/events/' + id, this.httpOptions).pipe(retry(1), catchError(this.handleError));
  }

  // Error handling
  handleError(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(() => {
      return errorMessage;
    });
  }
}
