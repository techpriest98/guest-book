package pb.guestbook.port.input.feedback;

import pb.guestbook.model.feedback.GetFeedbacksResponse;

public interface GetFeedbacksUseCase {
    GetFeedbacksResponse getFeedbacks();
}
