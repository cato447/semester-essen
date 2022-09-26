import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from 'src/app/shared/service/event-service.service';

@Component({
  selector: 'app-event-update',
  templateUrl: './event-update.component.html',
  styleUrls: ['./event-update.component.css']
})
export class EventUpdateComponent implements OnInit {

  id = this.actRoute.snapshot.params['id'];
  eventData: any =  {};

  constructor(
    public eventService: EventService,
    public actRoute: ActivatedRoute,
    public router: Router,
  ) { }

  ngOnInit(): void {
    this.eventService.getEvent(this.id).subscribe((data: {}) => {
      this.eventData = data;
    })
  }

  updateEvent() {
    if(window.confirm('Are you sure, you want to update?')) {
      this.eventService.updateEvent(this.id, this.eventData).subscribe((data) => {
        this.router.navigate(['/event-list'])
      })
    }
  }
}
