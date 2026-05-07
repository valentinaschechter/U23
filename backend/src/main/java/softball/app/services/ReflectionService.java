package softball.app.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import softball.app.dto.ReflectionDTO;
import softball.app.jpa.Reflection;
import softball.app.jpa.User;
import softball.app.repository.ReflectionRepository;
import softball.app.repository.UserRepository;

@Service
public class ReflectionService {

    private final ReflectionRepository reflectionRepository;
    private final UserRepository userRepository;

    public ReflectionService(ReflectionRepository reflectionRepository, UserRepository userRepository) {
        this.reflectionRepository = reflectionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveReflection(ReflectionDTO dto) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        User player = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Ingelogde gebruiker niet gevonden"));

        Reflection reflection = new Reflection();
        reflection.setPlayer(player);
        reflection.setRpe(dto.getRpe());
        reflection.setFocus(dto.getFocus());
        reflection.setSelfworth(dto.getSelfworth());
        reflection.setGroupfeeling(dto.getGroupfeeling());
        reflection.setGroupenergy(dto.getGroupenergy());
        reflection.setLearning(dto.getLearning());
        reflection.setFeedback(dto.getFeedback());
        reflection.setPositiveNote(dto.getPositiveNote());

        reflectionRepository.save(reflection);
    }
}
