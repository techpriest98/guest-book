package pb.guestbook.port.output.feedback;

import pb.guestbook.model.feedback.AddFeedbackResponse;
import pb.guestbook.model.feedback.Feedback;

public interface AddFeedbackPort {
    AddFeedbackResponse addFeedback (Feedback feedback);
}
