import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EventListComponent } from './views/event-list/event-list.component';
import { EventFormComponent } from './views/event-create/event-create.component';
import { EventUpdateComponent } from './views/event-update/event-update.component';


const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'event-list'},
  {path: "create-event", component: EventFormComponent},
  {path: "event-list", component: EventListComponent},
  {path: "event-edit/:id", component: EventUpdateComponent}, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
