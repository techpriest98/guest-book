package pb.guestbook.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedbackDto {
    @JsonProperty("authorName")
    private String authorName;
    @JsonProperty("feedback")
    private String feedback;
    @JsonProperty("feedbackDate")
    private String feedbackDate;
    @JsonProperty("rating")
    private Integer rating;

    public FeedbackDto(String authorName, String feedback, String feedbackDate, Integer rating) {
        this.authorName = authorName;
        this.feedback = feedback;
        this.feedbackDate = feedbackDate;
        this.rating = rating;
    }
}
