package pb.guestbook.service;

import pb.guestbook.exception.ValidationException;
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
        if (addFeedbackRequest.getFeedback().isBlank()) {
            throw new ValidationException("The feedback is required");
        }

        if (addFeedbackRequest.getAuthorName().isBlank()) {
            throw new ValidationException("The author name is required");
        }

        if (addFeedbackRequest.getAuthorName().length() > 128) {
            throw new ValidationException("The author name is too long");
        }

        if (addFeedbackRequest.getFeedback().length() > 256) {
            throw new ValidationException("The feedback is too long");
        }

        return feedbackPort.addFeedback(addFeedbackRequest);
    }

    @Override
    public List<Feedback> getFeedbacks() {
        return feedbackPort.getFeedbacks();
    }

    @Override
    public UpdateFeedbackResponse updateFeedback(UpdateFeedbackRequest updateFeedbackRequest) {
        if (updateFeedbackRequest.getFeedback().isBlank()) {
            throw new ValidationException("The feedback is required");
        }

        if (updateFeedbackRequest.getFeedback().length() > 256) {
            throw new ValidationException("The feedback is too long");
        }

        return feedbackPort.updateFeedback(updateFeedbackRequest);
    }

    @Override
    public RemoveFeedbackResponse removeFeedback(int feedbackId) {
        return feedbackPort.removeFeedback(feedbackId);
    }
}
