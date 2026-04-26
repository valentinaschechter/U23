import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../service/authService';
import { Reflectionform } from './reflectionform/reflectionform';
import { CoachDashboard } from './coachdashboard/coachdashboard';

@Component({
    selector: 'app-reflection-container',
    standalone: true,
    imports: [CommonModule, Reflectionform, CoachDashboard],
    template: `
    @if (authService.isCoach()) {
      <app-coachdashboard></app-coachdashboard>
    } @else {
      <app-reflectionform></app-reflectionform>
    }
  `
})
export class ReflectionContainer {
    constructor(public authService: AuthService) { }
}