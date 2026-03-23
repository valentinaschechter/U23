import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PostService } from '../../service/postService';
import { AuthService } from '../../service/authService';
import { Post } from '../../models/post.model';

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './homepage.html',
  styleUrl: './homepage.css'
})
export class Homepage implements OnInit {

  private postService = inject(PostService);
  public authService = inject(AuthService);

  posts = signal<Post[]>([]);

  newPost: Post = {
    title: '',
    content: '',
    author: '',
    onlyForRoster: false
  };

  ngOnInit() {
    this.loadPosts();
  }

  loadPosts() {
    this.postService.getPosts().subscribe(data => this.posts.set(data));
  }

  savePost() {
    // Auteur invullen op basis van ingelogde gebruiker
    this.newPost.author = this.authService.getUser()?.firstName || 'Coach';

    this.postService.createPost(this.newPost).subscribe({
      next: () => {
        this.loadPosts(); // Ververs de lijst
        this.newPost = { title: '', content: '', author: '', onlyForRoster: false };
      },
      error: (err) => alert("Alleen coaches mogen berichten plaatsen!")
    });
  }

  onDelete(id: number | undefined) {
    if (id && confirm('Bericht verwijderen?')) {
      this.postService.deletePost(id).subscribe(() => this.loadPosts());
    }
  }
}