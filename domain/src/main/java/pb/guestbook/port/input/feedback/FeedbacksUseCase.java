package pb.guestbook.port.input.feedback;

import pb.guestbook.model.feedback.*;

import java.util.List;

public interface FeedbacksUseCase {
    AddFeedbackResponse addFeedback(AddFeedbackRequest addFeedbackRequest);
    List<Feedback> getFeedbacks();
    RemoveFeedbackResponse removeFeedback(int feedbackId);
    UpdateFeedbackResponse updateFeedback(UpdateFeedbackRequest updateFeedbackRequest);
}
