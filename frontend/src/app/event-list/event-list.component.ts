import { Component, OnInit } from '@angular/core';
import { Event } from '../module/event';
import { EventService } from '../service/event-service.service';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit {
  
  events: Event[];

  constructor(private eventService: EventService) {
    this.events = [];
  }

  ngOnInit(): void {
    this.eventService.findAll().subscribe(data => {
      this.events = data;
    })
  }

}
