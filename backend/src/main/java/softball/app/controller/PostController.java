package softball.app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import softball.app.jpa.Post;
import softball.app.repository.PostRepository;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "https://softballu23.eu")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<Post> getPosts(@RequestParam(required = false) String role) {
        if ("COACH".equals(role) || "PLAYER".equals(role)) {
            return postRepository.findAllByOrderByCreatedAtDesc();
        }
        return postRepository.findByOnlyForRosterFalseOrderByCreatedAtDesc();
    }

    @PostMapping
    public Post createPost(@RequestBody Post post, Authentication authentication) {
        if (authentication == null) {
            System.out.println("DEBUG: Er is GEEN authenticatie gevonden (null)!");
        } else {
            System.out.println("DEBUG: Gebruiker: " + authentication.getName());
            System.out.println("DEBUG: Autoriteiten: " + authentication.getAuthorities());
        }

        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
    }
}