package pb.guestbook.feedback;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import pb.guestbook.model.feedback.AddFeedbackResponse;
import pb.guestbook.model.feedback.Feedback;
import pb.guestbook.port.output.feedback.AddFeedbackPort;
import pb.guestbook.port.output.feedback.GetFeedbacksPort;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.TimeZone;

@Repository
public class FeedbackAdapter implements GetFeedbacksPort, AddFeedbackPort {
    private static final String GET_FEEDBACKS =
        "SELECT author_name, feedback, feedback_date, rating FROM feedbacks ORDER BY feedback_date DESC";
    private static final String ADD_FEEDBACK = "INSERT INTO feedbacks "
        + "(author_name, feedback, feedback_date, rating) VALUES (:authorName, :feedback, :feedbackDate, :rating)";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public FeedbackAdapter(@Qualifier("guestbookPostgresDb") NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Feedback> getFeedbacks() {
        return jdbcTemplate.query(GET_FEEDBACKS, new RowMapper<Feedback>(){
            public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Feedback(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getTimestamp(3).toLocalDateTime().atZone(ZoneId.of("UTC")),
                    rs.getInt(4)
                );
            }
        });
    }

    @Override
    public AddFeedbackResponse addFeedback(Feedback feedback) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("authorName", feedback.getAuthorName());
        map.addValue("feedback", feedback.getFeedback());
        map.addValue("feedbackDate", Timestamp.valueOf(feedback.getFeedbackDate().toLocalDateTime()));
        map.addValue("rating", feedback.getRating());

        jdbcTemplate.update(ADD_FEEDBACK, map);

        return new AddFeedbackResponse(200, "Feedback has been added");
    }
}
