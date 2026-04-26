package softball.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import softball.app.jpa.Reflection;

import java.util.List;

@Repository
public interface ReflectionRepository extends JpaRepository<Reflection, Long> {

    List<Reflection> findByPlayerIdOrderBySubmissionDateDesc(Long userId);

    List<Reflection> findAllByOrderBySubmissionDateDesc();
}