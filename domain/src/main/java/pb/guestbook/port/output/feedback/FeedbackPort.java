package pb.guestbook.port.output.feedback;

import pb.guestbook.model.feedback.*;

import java.util.List;

public interface FeedbackPort {
    AddFeedbackResponse addFeedback(AddFeedbackRequest addFeedbackRequest);
    List<Feedback> getFeedbacks();
    UpdateFeedbackResponse updateFeedback(UpdateFeedbackRequest updateFeedbackRequest);
    RemoveFeedbackResponse removeFeedback(int feedbackId);
}
