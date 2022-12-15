package pb.guestbook.service;

import pb.guestbook.model.feedback.AddFeedbackResponse;
import pb.guestbook.model.feedback.Feedback;
import pb.guestbook.port.input.feedback.AddFeedbackUseCase;
import pb.guestbook.port.input.feedback.GetFeedbacksUseCase;
import pb.guestbook.port.output.feedback.AddFeedbackPort;
import pb.guestbook.port.output.feedback.GetFeedbacksPort;

import java.util.List;

public class FeedbackService implements AddFeedbackUseCase, GetFeedbacksUseCase {
    private final GetFeedbacksPort getFeedbacksPort;
    private final AddFeedbackPort addFeedbackPort;

    public FeedbackService (GetFeedbacksPort getFeedbacksPort, AddFeedbackPort addFeedbackPort) {
        this.getFeedbacksPort = getFeedbacksPort;
        this.addFeedbackPort = addFeedbackPort;
    }

    @Override
    public AddFeedbackResponse addFeedback(Feedback feedback) {
        return addFeedbackPort.addFeedback(feedback);
    }

    @Override
    public List<Feedback> getFeedbacks() {
        return getFeedbacksPort.getFeedbacks();
    }
}
