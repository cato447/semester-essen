import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Event } from '../module/event';
import { Observable } from 'rxjs';

@Injectable()
export class EventService {

  private eventUrl: string;

  constructor(private http: HttpClient) {
    this.eventUrl = "http://localhost:8080/api/events"  
  }

  public findAll(): Observable<Event[]> {
    return this.http.get<Event[]>(this.eventUrl)
  }

  public save(event: Event) {
    return this.http.post<Event>(this.eventUrl, event);
  }
}
