import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReflectionService } from '../../service/reflectionService';
import { ReflectionResponse } from '../../models/reflection.model';

@Component({
  selector: 'app-coachdashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './coachdashboard.html',
  styleUrls: ['./coachdashboard.css']
})
export class CoachDashboard implements OnInit {
  reflections: ReflectionResponse[] = [];

  constructor(private reflectionService: ReflectionService) { }

  ngOnInit(): void {
    this.reflectionService.getTeamSummary().subscribe(data => {
      this.reflections = data;
    });
  }

  // Hulpmethode om kleuren te bepalen voor RPE
  getRpeClass(score: number): string {
    if (score >= 8) return 'status-critical'; // Te zwaar
    if (score >= 6) return 'status-warning';  // Pittig
    return 'status-ok';                       // Goed
  }
}