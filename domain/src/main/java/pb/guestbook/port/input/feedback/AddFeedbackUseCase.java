package pb.guestbook.port.input.feedback;

import pb.guestbook.model.feedback.AddFeedbackRequest;
import pb.guestbook.model.feedback.AddFeedbackResponse;

public interface AddFeedbackUseCase {
    AddFeedbackResponse addFeedback (AddFeedbackRequest addFeedbackRequest);
}
