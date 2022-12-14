package pb.guestbook.model.feedback;

public class AddFeedbackResponse {
    private final Integer status;
    private final String message;

    private AddFeedbackResponse(AddFeedbackResponseBuilder builder) {
        this.status = builder.status;
        this.message = builder.message;
    }

    public static AddFeedbackResponseBuilder newBuilder() {
        return new AddFeedbackResponseBuilder();
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public AddFeedbackResponseBuilder toBuilder() {
        return new AddFeedbackResponseBuilder()
            .setStatus(this.status)
            .setMessage(this.message);
    }

    public static final class AddFeedbackResponseBuilder {
        private Integer status;
        private String message;

        private AddFeedbackResponseBuilder() {

        }

        public AddFeedbackResponseBuilder setStatus(Integer status) {
            this.status = status;
            return this;
        }

        public AddFeedbackResponseBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public AddFeedbackResponse build() {
            return new AddFeedbackResponse(this);
        }
    }
}
