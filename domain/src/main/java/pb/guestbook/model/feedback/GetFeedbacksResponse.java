package pb.guestbook.model.feedback;

import java.util.List;

public class GetFeedbacksResponse {
    private final List<Feedback> feedbacks;

    public GetFeedbacksResponse(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }
}
