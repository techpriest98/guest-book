package pb.guestbook.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddFeedbackResponseDto {
    @JsonProperty("status")
    private Integer status;

    @JsonProperty("message")
    private String message;

    public AddFeedbackResponseDto(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
