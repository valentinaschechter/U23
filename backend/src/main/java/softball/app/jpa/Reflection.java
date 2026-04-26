package softball.app.jpa;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Reflection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User player;

    @Column(nullable = false, updatable = false)
    private LocalDateTime submissionDate;

    @PrePersist
    protected void onCreate() {
        this.submissionDate = LocalDateTime.now();
    }

    @Column
    @Min(value = 1, message = "RPE moet minimaal 1 zijn")
    @Max(value = 10, message = "RPE mag maximaal 10 zijn")
    private int rpe;

    @Column
    @Min(1)
    @Max(5)
    private int focus;

    @Column
    @Min(1)
    @Max(5)
    private int selfworth;

    @Column
    @Min(1)
    @Max(5)
    private int groupfeeling;

    @Column
    @Min(1)
    @Max(5)
    private int groupenergy;

    @Column
    @Min(1)
    @Max(5)
    private int learning;

    @Column(length = 1000)
    private String feedback;

    public Reflection() {
    }

    public Reflection(User player, int rpe, int focus, int selfworth, int groupfeeling,
            int groupenergy, int learning, String feedback) {
        this.player = player;
        this.rpe = rpe;
        this.focus = focus;
        this.selfworth = selfworth;
        this.groupfeeling = groupfeeling;
        this.groupenergy = groupenergy;
        this.learning = learning;
        this.feedback = feedback;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public int getRpe() {
        return rpe;
    }

    public void setRpe(int rpe) {
        this.rpe = rpe;
    }

    public int getFocus() {
        return focus;
    }

    public void setFocus(int focus) {
        this.focus = focus;
    }

    public int getSelfworth() {
        return selfworth;
    }

    public void setSelfworth(int selfworth) {
        this.selfworth = selfworth;
    }

    public int getGroupfeeling() {
        return groupfeeling;
    }

    public void setGroupfeeling(int groupfeeling) {
        this.groupfeeling = groupfeeling;
    }

    public int getGroupenergy() {
        return groupenergy;
    }

    public void setGroupenergy(int groupenergy) {
        this.groupenergy = groupenergy;
    }

    public int getLearning() {
        return learning;
    }

    public void setLearning(int learning) {
        this.learning = learning;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
