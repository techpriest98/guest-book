package pb.guestbook.feedback.converter;

import pb.guestbook.dtos.AddFeedbackRequestDto;
import pb.guestbook.dtos.AddFeedbackResponseDto;
import pb.guestbook.dtos.FeedbackDto;
import pb.guestbook.model.feedback.AddFeedbackResponse;
import pb.guestbook.model.feedback.Feedback;

import java.time.ZonedDateTime;
import java.util.List;

public class FeedbackConverter {
    public static Feedback toAddFeedbackRequest(AddFeedbackRequestDto addFeedbackRequestDto) {
        Feedback feedback = new Feedback(
            addFeedbackRequestDto.getAuthorName(),
            addFeedbackRequestDto.getFeedback(),
            ZonedDateTime.parse(addFeedbackRequestDto.getFeedbackDate()),
            addFeedbackRequestDto.getRating()
        );

        return feedback;
    }

    public static AddFeedbackResponseDto toAddFeedbackResponseDto(AddFeedbackResponse addFeedbackResponse) {
        return new AddFeedbackResponseDto(addFeedbackResponse.getStatus(), addFeedbackResponse.getMessage());
    }

    public static List<FeedbackDto> toFeedbacksDto(List<Feedback> feedbacks) {
        return feedbacks.stream().map(feedback -> new FeedbackDto(
            feedback.getAuthorName(),
            feedback.getFeedback(),
            feedback.getFeedbackDate().toString(),
            feedback.getRating()
        )).toList();
    }
}
