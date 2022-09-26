import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from '../../shared/service/event-service.service';

@Component({
  selector: 'app-event-create',
  templateUrl: './event-create.component.html',
  styleUrls: ['./event-create.component.css']
})
export class EventFormComponent implements OnInit {

  @Input() eventDetails = {name: '', published: false, dateTime: '', maxParticipants: 0};

  constructor(private route: ActivatedRoute, private router: Router, private eventService: EventService) {
  }

  ngOnInit(): void {}

  addEvent(dataEvent: any){
    this.eventService.createEvent(this.eventDetails).subscribe((data: {}) => {this.router.navigate(['/event-list'])});
  }
}
