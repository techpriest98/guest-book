package pb.guestbook.port.input.feedback;

import pb.guestbook.model.feedback.Feedback;

import java.util.List;

public interface GetFeedbacksUseCase {
    List<Feedback> getFeedbacks();
}
