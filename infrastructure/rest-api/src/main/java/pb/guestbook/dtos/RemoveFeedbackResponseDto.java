package pb.guestbook.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoveFeedbackResponseDto {
    @JsonProperty("feedbackRemoved")
    private boolean feedbackRemoved;

    public RemoveFeedbackResponseDto(boolean feedbackRemoved) {
        this.feedbackRemoved = feedbackRemoved;
    }
}
