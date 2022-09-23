import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from '../service/event-service.service';
import { Event } from '../module/event';

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
  styleUrls: ['./event-form.component.css']
})
export class EventFormComponent {

  event: Event;

  constructor(private route: ActivatedRoute, private router: Router, private eventService: EventService) {
    this.event = new Event();
  }

  onSubmit() {
    this.eventService.save(this.event).subscribe(result => this.gotoUserList());
  }

  gotoUserList() {
    this.router.navigate(['/events'])
  }

}
