package pb.guestbook.feedback.converter;

import pb.guestbook.dtos.AddFeedbackRequestDto;
import pb.guestbook.dtos.AddFeedbackResponseDto;
import pb.guestbook.dtos.FeedbackDto;
import pb.guestbook.model.feedback.AddFeedbackRequest;
import pb.guestbook.model.feedback.AddFeedbackResponse;
import pb.guestbook.model.feedback.Feedback;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.Objects.isNull;

public class FeedbackConverter {
    public static AddFeedbackRequest toAddFeedbackRequest(AddFeedbackRequestDto addFeedbackRequestDto) {
        AddFeedbackRequest.AddFeedBackRequestBuilder addFeedBackRequestBuilder = AddFeedbackRequest.newBuilder();
        if (isNull(addFeedbackRequestDto)) {
            return addFeedBackRequestBuilder.build();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        addFeedBackRequestBuilder
            .setAuthorName(addFeedbackRequestDto.getAuthorName())
            .setFeedback(addFeedbackRequestDto.getFeedback())
            .setFeedbackDate(LocalDateTime.parse(addFeedbackRequestDto.getFeedbackDate()))
            .setRating(addFeedbackRequestDto.getRating());

        return addFeedBackRequestBuilder.build();
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
