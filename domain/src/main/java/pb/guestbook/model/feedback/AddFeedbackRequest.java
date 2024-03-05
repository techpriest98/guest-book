package pb.guestbook.model.feedback;

import java.time.LocalDateTime;

public class AddFeedbackRequest {
    private final String authorName;
    private final String feedback;
    private final Integer rating;

    public AddFeedbackRequest(String authorName, String feedback, Integer rating) {
        this.authorName = authorName;
        this.feedback = feedback;
        this.rating = rating;
    }

    public String getAuthorName() {
        return authorName;
    }
    public String getFeedback() {
        return feedback;
    }

    public Integer getRating() {
        return rating;
    }
}
