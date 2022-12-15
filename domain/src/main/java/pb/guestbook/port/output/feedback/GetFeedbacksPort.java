package pb.guestbook.port.output.feedback;

import pb.guestbook.model.feedback.Feedback;

import java.util.List;

public interface GetFeedbacksPort {
    List<Feedback> getFeedbacks();
}
