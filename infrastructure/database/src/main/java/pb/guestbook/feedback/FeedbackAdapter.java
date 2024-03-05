package pb.guestbook.feedback;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import pb.guestbook.model.feedback.*;
import pb.guestbook.port.output.feedback.FeedbackPort;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class FeedbackAdapter implements FeedbackPort {
    private static final String GET_FEEDBACKS =
        "SELECT id, author_name, feedback, feedback_date, rating FROM feedbacks ORDER BY feedback_date DESC";
    private static final String ADD_FEEDBACK =
        "INSERT INTO feedbacks (author_name, feedback, feedback_date, rating) VALUES (:authorName, :feedback, :feedbackDate, :rating)";
    private static final String UPDATE_FEEDBACK = "UPDATE feedbacks SET feedback = (:feedback), rating = (:rating) WHERE id = (:id)";
    private static final String REMOVE_FEEDBACK = "DELETE FROM feedbacks WHERE id = (:id)";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public FeedbackAdapter(@Qualifier("guestbookPostgresDb") NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Feedback> getFeedbacks() {
        return jdbcTemplate.query(GET_FEEDBACKS, (rs, rowNum) -> new Feedback(
            rs.getInt(1),
            rs.getString(2),
            rs.getString(3),
            rs.getTimestamp(4).toLocalDateTime(),
            rs.getInt(5)
        ));
    }

    @Override
    public AddFeedbackResponse addFeedback(AddFeedbackRequest addFeedbackRequest) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("authorName", addFeedbackRequest.getAuthorName());
        map.addValue("feedback", addFeedbackRequest.getFeedback());
        map.addValue("rating", addFeedbackRequest.getRating());
        map.addValue("feedbackDate", LocalDateTime.now());

        int feedbackId = jdbcTemplate.update(ADD_FEEDBACK, map);

        return new AddFeedbackResponse(feedbackId);
    }

    public UpdateFeedbackResponse updateFeedback(UpdateFeedbackRequest updateFeedbackRequest) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", updateFeedbackRequest.getId());
        map.addValue("feedback", updateFeedbackRequest.getFeedback());
        map.addValue("rating", updateFeedbackRequest.getRating());

        int updatedRecords = jdbcTemplate.update(UPDATE_FEEDBACK, map);

        return new UpdateFeedbackResponse(updatedRecords > 0);
    }

    @Override
    public RemoveFeedbackResponse removeFeedback(int feedbackId) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", feedbackId);

        int deletedRecords = jdbcTemplate.update(REMOVE_FEEDBACK, map);

        return new RemoveFeedbackResponse(deletedRecords > 0);
    }
}
