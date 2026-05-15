import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ReflectionRequest, ReflectionResponse } from '../models/reflection.model';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class ReflectionService {

    private readonly apiUrl = `${environment.apiUrl}/api/reflections`;

    constructor(private http: HttpClient) { }

    submitReflection(reflection: ReflectionRequest): Observable<string> {
        return this.http.post(`${this.apiUrl}`, reflection, { responseType: 'text' });
    }

    getTeamSummary(): Observable<ReflectionResponse[]> {
        return this.http.get<ReflectionResponse[]>(`${this.apiUrl}/summary`);
    }

    getReflectionsByPlayer(playerId: number): Observable<ReflectionResponse[]> {
        return this.http.get<ReflectionResponse[]>(`${this.apiUrl}/player/${playerId}`);
    }
}