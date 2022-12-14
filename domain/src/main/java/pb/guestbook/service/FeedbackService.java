package pb.guestbook.service;

import org.springframework.stereotype.Service;
import pb.guestbook.model.feedback.AddFeedbackRequest;
import pb.guestbook.model.feedback.AddFeedbackResponse;
import pb.guestbook.model.feedback.GetFeedbacksResponse;
import pb.guestbook.port.input.feedback.AddFeedbackUseCase;
import pb.guestbook.port.input.feedback.GetFeedbacksUseCase;

import java.util.List;

@Service
public class FeedbackService implements AddFeedbackUseCase, GetFeedbacksUseCase {

    public FeedbackService () {
    }

    @Override
    public AddFeedbackResponse addFeedback(AddFeedbackRequest addFeedbackRequest) {
        return new AddFeedbackResponse(200, "Feedback has been added");
    }

    @Override
    public GetFeedbacksResponse getFeedbacks() {
        return new GetFeedbacksResponse(List.of());
    }
}
