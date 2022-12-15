package pb.guestbook.port.input.feedback;

import pb.guestbook.model.feedback.AddFeedbackResponse;
import pb.guestbook.model.feedback.Feedback;

public interface AddFeedbackUseCase {
    AddFeedbackResponse addFeedback(Feedback feedback);
}
