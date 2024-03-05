package pb.guestbook.model.feedback;

import java.time.LocalDateTime;

public class Feedback {
    private final Integer id;
    private final String authorName;
    private final String feedback;
    private final LocalDateTime feedbackDate;
    private final Integer rating;

    public Feedback(Integer id, String authorName, String feedback, LocalDateTime feedbackDate, Integer rating) {
        this.id = id;
        this.authorName = authorName;
        this.feedback = feedback;
        this.feedbackDate = feedbackDate;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
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
