package pb.guestbook.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedbackDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("authorName")
    private String authorName;
    @JsonProperty("feedback")
    private String feedback;
    @JsonProperty("feedbackDate")
    private String feedbackDate;
    @JsonProperty("rating")
    private Integer rating;

    public FeedbackDto(Integer id, String authorName, String feedback, String feedbackDate, Integer rating) {
        this.id = id;
        this.authorName = authorName;
        this.feedback = feedback;
        this.feedbackDate = feedbackDate;
        this.rating = rating;
    }
}
