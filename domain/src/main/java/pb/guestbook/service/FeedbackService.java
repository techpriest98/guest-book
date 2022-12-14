package pb.guestbook.service;

import org.springframework.stereotype.Service;
import pb.guestbook.model.feedback.AddFeedbackRequest;
import pb.guestbook.model.feedback.AddFeedbackResponse;
import pb.guestbook.port.input.feedback.AddFeedbackUseCase;

@Service
public class FeedbackService implements AddFeedbackUseCase {

    public FeedbackService () {
    }

    @Override
    public AddFeedbackResponse addFeedback(AddFeedbackRequest addFeedbackRequest) {

        return AddFeedbackResponse
            .newBuilder()
            .setStatus(200)
            .setMessage("Guest has been added")
            .build();
    }
}
