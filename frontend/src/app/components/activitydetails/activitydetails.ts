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
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));

      if (id) {
        this.planningService.getActivityById(id).subscribe({
          next: (data) => {
            this.activity = data;
            console.log("Activiteit succesvol geladen:", data);
          },
          error: (err) => {
            console.error("Fout bij ophalen activiteit:", err);
          }
        });
      }
    });
  }

  get presentList() {
    return this.activity?.attendances?.filter(a => a.isPresent) || [];
  }

  get absentList() {
    return this.activity?.attendances?.filter(a => a.isPresent === false) || [];
  }
}