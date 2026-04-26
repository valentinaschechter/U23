package softball.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import softball.app.dto.ReflectionDTO;
import softball.app.jpa.Reflection;
import softball.app.repository.ReflectionRepository;
import softball.app.services.ReflectionService;

@RestController
@RequestMapping("/api/reflections")
public class ReflectionController {

    private final ReflectionService reflectionService;
    private final ReflectionRepository reflectionRepository;

    public ReflectionController(ReflectionService reflectionService, ReflectionRepository reflectionRepository) {
        this.reflectionService = reflectionService;
        this.reflectionRepository = reflectionRepository;
    }

    @PostMapping
    public ResponseEntity<String> submitReflection(@Valid @RequestBody ReflectionDTO reflectionDTO) {
        try {
            reflectionService.saveReflection(reflectionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Reflectie succesvol opgeslagen!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is iets fout gegaan bij het opslaan: " + e.getMessage());
        }
    }

    @GetMapping("/summary")
    @PreAuthorize("hasRole('COACH')")
    public ResponseEntity<List<Reflection>> getTeamSummary() {
        return ResponseEntity.ok(reflectionRepository.findAllByOrderBySubmissionDateDesc());
    }

    @GetMapping("/player/{playerId}")
    @PreAuthorize("hasRole('COACH')")
    public List<Reflection> getReflectionsByPlayer(Long playerId) {
        return reflectionRepository.findByPlayerIdOrderBySubmissionDateDesc(playerId);
    }
}