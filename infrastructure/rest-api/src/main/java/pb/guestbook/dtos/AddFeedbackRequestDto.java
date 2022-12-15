package pb.guestbook.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddFeedbackRequestDto {
    @JsonProperty
    private String authorName;
    @JsonProperty
    private String feedback;
    @JsonProperty
    private String feedbackDate;
    @JsonProperty
    private Integer rating;

    public String getAuthorName() {
        return authorName;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public Integer getRating() {
        return rating;
    }
}
