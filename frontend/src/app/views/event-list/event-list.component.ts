import { Component, OnInit } from '@angular/core';
import { Event } from '../../shared/models/event';
import { EventService } from '../../shared/service/event-service.service';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css'],
})
export class EventListComponent implements OnInit {
  
  Event: any = [];

  constructor(private eventService: EventService) {}

  ngOnInit(): void {
    this.loadEvents();
  }

  loadEvents() {
    return this.eventService.getEvents().subscribe((data: {}) => {
      this.Event = data;
    })
  }

  deleteEvent(id: any){
    if(window.confirm('Are you sure, you want to delete?')) {
      this.eventService.deleteEvent(id).subscribe((data) => {
        this.loadEvents();
      });
    }
  }

}
