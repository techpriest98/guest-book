package pb.guestbook.feedback;

import org.springframework.web.bind.annotation.*;
import pb.guestbook.dtos.AddFeedbackRequestDto;
import pb.guestbook.dtos.AddFeedbackResponseDto;
import pb.guestbook.dtos.FeedbackDto;
import pb.guestbook.dtos.UpdateFeedbackRequestDto;
import pb.guestbook.model.feedback.AddFeedbackResponse;
import pb.guestbook.port.input.feedback.FeedbacksUseCase;

import java.util.List;

import static pb.guestbook.feedback.converter.FeedbackConverter.*;

@RestController
@RequestMapping("/api")
public class FeedbackController {
    private final FeedbacksUseCase feedbackUseCase;

    public FeedbackController(FeedbacksUseCase feedbacksUseCase) {
        this.feedbackUseCase = feedbacksUseCase;
    }

    @PostMapping("/feedbacks")
    public AddFeedbackResponseDto addFeedback(@RequestBody AddFeedbackRequestDto addFeedbackRequestDto) {
        AddFeedbackResponse result = feedbackUseCase.addFeedback(toAddFeedbackRequest(addFeedbackRequestDto));

        return toAddFeedbackResponseDto(result);
    }

    @GetMapping("/feedbacks")
    public List<FeedbackDto> getFeedbacks() {
        return toFeedbacksDto(feedbackUseCase.getFeedbacks());
    }

    @PutMapping("/feedbacks/{feedbackId}")
    public boolean updateFeedback(@PathVariable Integer feedbackId, @RequestBody UpdateFeedbackRequestDto updateFeedbackRequestDto) {
        return feedbackUseCase.updateFeedback(toUpdateFeedbackRequest(feedbackId, updateFeedbackRequestDto)).isFeedbackUpdated();
    }

    @DeleteMapping("/feedbacks/{feedbackId}")
    public boolean removeFeedback(@PathVariable Integer feedbackId) {
        return feedbackUseCase.removeFeedback(feedbackId).isFeedbackRemoved();
    }
}
