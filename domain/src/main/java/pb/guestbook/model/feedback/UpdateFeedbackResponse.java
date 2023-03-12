package pb.guestbook.model.feedback;

public class UpdateFeedbackResponse {
    private final boolean feedbackUpdated;

    public UpdateFeedbackResponse(boolean feedbackUpdated) {
        this.feedbackUpdated = feedbackUpdated;
    }

    public boolean isFeedbackUpdated() {
        return feedbackUpdated;
    }
}
