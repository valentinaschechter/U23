import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
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
        const name = "currentUser=";
        const ca = document.cookie.split(';');
        let userData = "";

        for (let i = 0; i < ca.length; i++) {
            let c = ca[i].trim();
            if (c.indexOf(name) === 0) {
                userData = c.substring(name.length, c.length);
            }
        }

        const user = userData ? JSON.parse(userData) : null;
        const token = user ? user.role : 'ANONYMOUS';

        console.log("DEBUG: We sturen deze rol mee als autorisatie:", token);

        const headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
        });

        return this.http.post<Post>(this.apiUrl, post, { headers });
    }

    deletePost(id: number): Observable<void> {
        const user = this.authService.getUser();
        const role = user ? user.role : '';

        const headers = new HttpHeaders({
            'Authorization': `Bearer ${role}`
        });

        console.log("DEBUG: Delete versturen met rol:", role);

        return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers });
    }
}