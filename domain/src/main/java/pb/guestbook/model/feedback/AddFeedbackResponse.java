package pb.guestbook.model.feedback;

public class AddFeedbackResponse {
    private final Integer status;
    private final String message;

    public AddFeedbackResponse(Integer status, String message) {
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
