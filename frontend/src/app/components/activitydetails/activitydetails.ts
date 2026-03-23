import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { PlanningService } from '../../service/planningService';
import { Activity } from '../../models/activity.model';

@Component({
  selector: 'app-activitydetails',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './activitydetails.html',
  styleUrl: './activitydetails.css'
})
export class Activitydetails implements OnInit {
  private route = inject(ActivatedRoute);
  private planningService = inject(PlanningService);

  activity: Activity | undefined;

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    if (id) {
      this.planningService.getActivities().subscribe(activities => {
        this.activity = activities.find(a => a.id === id);
      });
    }
  }

  get presentList() {
    return this.activity?.attendances?.filter(a => a.isPresent) || [];
  }

  get absentList() {
    return this.activity?.attendances?.filter(a => a.isPresent === false) || [];
  }
}