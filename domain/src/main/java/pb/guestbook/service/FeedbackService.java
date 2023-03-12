package pb.guestbook.service;

import pb.guestbook.model.feedback.*;
import pb.guestbook.port.input.feedback.FeedbacksUseCase;
import pb.guestbook.port.output.feedback.FeedbackPort;

import java.util.List;

public class FeedbackService implements FeedbacksUseCase {
    private final FeedbackPort feedbackPort;

    public FeedbackService(
        FeedbackPort feedbackPort
    ) {
        this.feedbackPort = feedbackPort;
    }

    @Override
    public AddFeedbackResponse addFeedback(AddFeedbackRequest addFeedbackRequest) {
        return feedbackPort.addFeedback(addFeedbackRequest);
    }

    @Override
    public List<Feedback> getFeedbacks() {
        return feedbackPort.getFeedbacks();
    }

    @Override
    public UpdateFeedbackResponse updateFeedback(UpdateFeedbackRequest updateFeedbackRequest) {
        return feedbackPort.updateFeedback(updateFeedbackRequest);
    }

    @Override
    public RemoveFeedbackResponse removeFeedback(int feedbackId) {
        return feedbackPort.removeFeedback(feedbackId);
    }
}
