import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ReflectionRequest } from '../../models/reflection.model';
import { ReflectionService } from '../../service/reflectionService';

@Component({
  selector: 'app-reflectionform',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './reflectionform.html',
  styleUrl: './reflectionform.css',
})
export class Reflectionform implements OnInit {
  reflectionform!: FormGroup;
  submitted = false;

  constructor(private fb: FormBuilder, private reflectionService: ReflectionService) { }

  ngOnInit(): void {
    this.reflectionform = this.fb.group({
      rpe: [5, [Validators.required, Validators.min(1), Validators.max(10)]],
      focus: [3, [Validators.required, Validators.min(1), Validators.max(5)]],
      selfworth: [3, [Validators.required, Validators.min(1), Validators.max(5)]],
      groupfeeling: [3, [Validators.required, Validators.min(1), Validators.max(5)]],
      groupenergy: [3, [Validators.required, Validators.min(1), Validators.max(5)]],
      learning: [3, [Validators.required, Validators.min(1), Validators.max(5)]],
      feedback: ['', [Validators.maxLength(1000)]],
      positiveNote: ['', [Validators.maxLength(1000)]]
    })
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.reflectionform.valid) {
      const reflectionData: ReflectionRequest = this.reflectionform.value;

      this.reflectionService.submitReflection(reflectionData).subscribe({
        next: (response) => {
          console.log('Succesvol verzonden:', response);
        },
        error: (err) => {
          console.error('Er ging iets mis:', err);
        }
      });
    }
  }
}
