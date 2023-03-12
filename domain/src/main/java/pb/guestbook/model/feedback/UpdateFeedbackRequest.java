package pb.guestbook.model.feedback;

import java.time.ZonedDateTime;

public class UpdateFeedbackRequest {

    private final Integer id;
    private final String feedback;
    private final Integer rating;

    public UpdateFeedbackRequest(Integer id, String feedback, Integer rating) {
        this.id = id;
        this.feedback = feedback;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public String getFeedback() {
        return feedback;
    }

    public Integer getRating() {
        return rating;
    }
}
