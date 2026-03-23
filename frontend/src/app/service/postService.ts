import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../models/post.model';
import { AuthService } from './authService';

@Injectable({ providedIn: 'root' })
export class PostService {
    private http = inject(HttpClient);
    private authService = inject(AuthService);
    private apiUrl = 'https://softballu23.eu/api/posts';

    getPosts(): Observable<Post[]> {
        const user = this.authService.getUser();
        let params = new HttpParams();

        if (user?.role) {
            params = params.set('role', user.role);
        }

        return this.http.get<Post[]>(this.apiUrl, { params });
    }

    createPost(post: Post): Observable<Post> {
        const token = localStorage.getItem('token');
        const headers = { 'Authorization': `Bearer ${token}` };
        return this.http.post<Post>(this.apiUrl, post, { headers });
    }

    deletePost(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}