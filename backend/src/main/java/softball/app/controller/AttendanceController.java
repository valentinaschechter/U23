package softball.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import softball.app.jpa.Activity;
import softball.app.jpa.Attendance;
import softball.app.jpa.User;
import softball.app.repository.ActivityRepository;
import softball.app.repository.AttendanceRepository;
import softball.app.repository.UserRepository;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    public AttendanceController(AttendanceRepository attendanceRepository, UserRepository userRepository,
            ActivityRepository activityRepository) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
    }

    @GetMapping("/{activityId}")
    public List<Attendance> getAttendanceByActivity(@PathVariable Long activityId) {
        return attendanceRepository.findByActivityId(activityId);
    }

    @PostMapping
    public Attendance saveAttendance(@RequestBody Attendance dto) {
        User user = userRepository.findById(dto.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User niet gevonden"));
        Activity activity = activityRepository.findById(dto.getActivity().getId())
                .orElseThrow(() -> new RuntimeException("Activity niet gevonden"));

        // 2. Zoek of er al een aanwezigheid bestaat voor deze combinatie
        return attendanceRepository.findByUserIdAndActivityId(user.getId(), activity.getId())
                .map(existingAttendance -> {
                    // Update bestaande
                    existingAttendance.setIsPresent(dto.getIsPresent());
                    return attendanceRepository.save(existingAttendance);
                })
                .orElseGet(() -> {
                    // Maak nieuwe aan met de gekoppelde objecten
                    Attendance newAttendance = new Attendance();
                    newAttendance.setUser(user);
                    newAttendance.setActivity(activity);
                    newAttendance.setIsPresent(dto.getIsPresent());
                    return attendanceRepository.save(newAttendance);
                });
    }
}
