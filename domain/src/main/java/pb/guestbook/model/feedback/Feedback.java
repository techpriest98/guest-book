package pb.guestbook.model.feedback;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Feedback {
    private final String authorName;
    private final String feedback;
    private final ZonedDateTime feedbackDate;
    private final Integer rating;

    public Feedback(String authorName, String feedback, ZonedDateTime feedbackDate, Integer rating) {
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

    public ZonedDateTime getFeedbackDate() {
        return feedbackDate;
    }

    public Integer getRating() {
        return rating;
    }
}
