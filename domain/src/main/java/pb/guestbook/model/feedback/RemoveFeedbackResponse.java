package pb.guestbook.model.feedback;

public class RemoveFeedbackResponse {
    private final boolean feedbackRemoved;

    public RemoveFeedbackResponse(boolean feedbackRemoved) {
        this.feedbackRemoved = feedbackRemoved;
    }

    public boolean isFeedbackRemoved() {
        return feedbackRemoved;
    }
}
