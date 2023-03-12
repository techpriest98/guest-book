package pb.guestbook.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddFeedbackResponseDto {
    @JsonProperty("feedbackId")
    private int feedbackId;

    public AddFeedbackResponseDto(int feedbackId) {
        this.feedbackId = feedbackId;
    }
}
