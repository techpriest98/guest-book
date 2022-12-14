package pb.guestbook.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class AddFeedbackRequestDto {
    @JsonProperty
    private String authorName;
    @JsonProperty
    private String feedback;
    @JsonProperty
    private LocalDateTime feedbackDate;
    @JsonProperty
    private Integer rating;

    public String getAuthorName() {
        return authorName;
    }

    public String getFeedback() {
        return feedback;
    }

    public LocalDateTime getFeedbackDate() {
        return feedbackDate;
    }

    public Integer getRating() {
        return rating;
    }
}
