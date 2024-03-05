package pb.guestbook.feedback.converter;

import pb.guestbook.dtos.AddFeedbackRequestDto;
import pb.guestbook.dtos.AddFeedbackResponseDto;
import pb.guestbook.dtos.FeedbackDto;
import pb.guestbook.dtos.UpdateFeedbackRequestDto;
import pb.guestbook.model.feedback.AddFeedbackResponse;
import pb.guestbook.model.feedback.AddFeedbackRequest;
import pb.guestbook.model.feedback.Feedback;
import pb.guestbook.model.feedback.UpdateFeedbackRequest;

import java.time.LocalDateTime;
import java.util.List;

public class FeedbackConverter {
    public static AddFeedbackRequest toAddFeedbackRequest(AddFeedbackRequestDto addFeedbackRequestDto) {
        return new AddFeedbackRequest(
            addFeedbackRequestDto.getAuthorName(),
            addFeedbackRequestDto.getFeedback(),
            addFeedbackRequestDto.getRating()
        );
    }

    public static UpdateFeedbackRequest toUpdateFeedbackRequest(int feedbackId, UpdateFeedbackRequestDto updateFeedbackRequestDto) {
        return new UpdateFeedbackRequest(
            feedbackId,
            updateFeedbackRequestDto.getFeedback(),
            updateFeedbackRequestDto.getRating()
        );
    }

    public static AddFeedbackResponseDto toAddFeedbackResponseDto(AddFeedbackResponse addFeedbackResponse) {
        return new AddFeedbackResponseDto(addFeedbackResponse.getFeedbackId());
    }

    public static List<FeedbackDto> toFeedbacksDto(List<Feedback> feedbacks) {
        return feedbacks.stream().map(feedback -> new FeedbackDto(
            feedback.getId(),
            feedback.getAuthorName(),
            feedback.getFeedback(),
            feedback.getFeedbackDate().toString(),
            feedback.getRating()
        )).toList();
    }
}
