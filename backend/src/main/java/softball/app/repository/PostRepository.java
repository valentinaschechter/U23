package softball.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import softball.app.jpa.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByOnlyForRosterFalseOrderByCreatedAtDesc();

    List<Post> findAllByOrderByCreatedAtDesc();
}