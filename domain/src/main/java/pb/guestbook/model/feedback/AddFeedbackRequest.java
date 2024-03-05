package pb.guestbook.model.feedback;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class AddFeedbackRequest {
    private final String authorName;
    private final String feedback;
    private final LocalDateTime feedbackDate;
    private final Integer rating;

    public AddFeedbackRequest(String authorName, String feedback, LocalDateTime feedbackDate, Integer rating) {
        this.authorName = authorName;
        this.feedback = feedback;
        this.feedbackDate = feedbackDate;
        this.rating = rating;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getFeedback() {
        return feedback;
    }

    public LocalDateTime getFeedbackDate() {
        return feedbackDate;
    }

    public Integer getRating() {
        return rating;
    }
}
