package pb.guestbook.service;

import org.springframework.stereotype.Service;
import pb.guestbook.model.feedback.AddFeedbackRequest;
import pb.guestbook.model.feedback.AddFeedbackResponse;
import pb.guestbook.model.feedback.Feedback;
import pb.guestbook.port.input.feedback.AddFeedbackUseCase;
import pb.guestbook.port.input.feedback.GetFeedbacksUseCase;

import java.time.LocalDateTime;
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
    public List<Feedback> getFeedbacks() {
        return List.of(
            new Feedback("Andrii", "Fine", LocalDateTime.now(), 5),
            new Feedback("Vova", "Good", LocalDateTime.now(), 4)
        );
    }
}
