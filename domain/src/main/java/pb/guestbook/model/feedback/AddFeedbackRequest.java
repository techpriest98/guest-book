package pb.guestbook.model.feedback;

import java.time.LocalDateTime;

public class AddFeedbackRequest {
    private final String authorName;
    private final String feedback;
    private final LocalDateTime feedbackDate;
    private final Integer rating;

    public AddFeedbackRequest(AddFeedBackRequestBuilder builder) {
        this.authorName = builder.authorName;
        this.feedback = builder.feedback;
        this.feedbackDate = builder.feedbackDate;
        this.rating = builder.rating;
    }

    public static AddFeedBackRequestBuilder newBuilder() {
        return new AddFeedBackRequestBuilder();
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

    public AddFeedBackRequestBuilder toBuilder() {
        return new AddFeedBackRequestBuilder()
            .setAuthorName(this.authorName)
            .setFeedback(this.feedback)
            .setFeedbackDate(this.feedbackDate)
            .setRating(this.rating);
    }

    public static final class AddFeedBackRequestBuilder {
        private String authorName;
        private String feedback;
        private LocalDateTime feedbackDate;
        private Integer rating;

        private AddFeedBackRequestBuilder() {

        }

        public AddFeedBackRequestBuilder setAuthorName(String authorName) {
            this.authorName = authorName;
            return this;
        }

        public AddFeedBackRequestBuilder setFeedback(String feedback) {
            this.feedback = feedback;
            return this;
        }

        public AddFeedBackRequestBuilder setFeedbackDate(LocalDateTime feedbackDate) {
            this.feedbackDate = feedbackDate;
            return this;
        }

        public AddFeedBackRequestBuilder setRating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public AddFeedbackRequest build() {
            return new AddFeedbackRequest(this);
        }
    }
}
