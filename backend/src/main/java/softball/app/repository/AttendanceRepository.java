package softball.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import softball.app.jpa.Attendance;
import softball.app.jpa.User;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByUserIdAndActivityId(Long userId, Long activityId);

    List<Attendance> findByActivityId(Long activityId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Attendance a WHERE a.user = :user")
    void deleteByUser(User user);

}
