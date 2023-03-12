package pb.guestbook.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateFeedbackRequestDto {
    @JsonProperty
    private String feedback;
    @JsonProperty
    private Integer rating;

    public String getFeedback() {
        return feedback;
    }

    public Integer getRating() {
        return rating;
    }
}
