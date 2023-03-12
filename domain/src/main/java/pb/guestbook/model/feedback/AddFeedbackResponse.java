package pb.guestbook.model.feedback;

public class AddFeedbackResponse {
    private final int feedbackId;

    public AddFeedbackResponse(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }
}
