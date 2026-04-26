package softball.app.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class ReflectionDTO {
    private Long playerId;

    @Min(1)
    @Max(10)
    private int rpe;

    @Min(1)
    @Max(5)
    private int focus;

    @Min(1)
    @Max(5)
    private int selfworth;

    @Min(1)
    @Max(5)
    private int groupfeeling;

    @Min(1)
    @Max(5)
    private int groupenergy;

    @Min(1)
    @Max(5)
    private int learning;

    @Size(max = 1000, message = "Feedback mag niet langer zijn dan 1000 tekens")
    private String feedback;

    @Size(max = 1000, message = "Positief ding van de dag mag niet langer zijn dan 1000 tekens")
    private String positiveNote;

    public ReflectionDTO() {
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
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

    public String getPositiveNote() {
        return positiveNote;
    }

    public void setPositiveNote(String positiveNote) {
        this.positiveNote = positiveNote;
    }
}
