package pb.guestbook.feedback;

import org.springframework.web.bind.annotation.*;
import pb.guestbook.dtos.AddFeedbackRequestDto;
import pb.guestbook.dtos.AddFeedbackResponseDto;
import pb.guestbook.dtos.FeedbackDto;
import pb.guestbook.model.feedback.AddFeedbackResponse;
import pb.guestbook.port.input.feedback.AddFeedbackUseCase;
import pb.guestbook.port.input.feedback.GetFeedbacksUseCase;

import java.util.List;

import static pb.guestbook.feedback.converter.FeedbackConverter.*;

@RestController
@RequestMapping("/api")
public class FeedbackController {
    private final AddFeedbackUseCase addFeedbackUseCase;
    private final GetFeedbacksUseCase getFeedbacksUseCase;

    public FeedbackController(GetFeedbacksUseCase getFeedbacksUseCase, AddFeedbackUseCase addFeedbackUseCase) {
        this.getFeedbacksUseCase = getFeedbacksUseCase;
        this.addFeedbackUseCase = addFeedbackUseCase;
    }

    @GetMapping("/feedbacks")
    public List<FeedbackDto> getFeedbacks() {
        return toFeedbacksDto(getFeedbacksUseCase.getFeedbacks());
    }

    @PostMapping("/feedback/add")
    public AddFeedbackResponseDto addFeedback(@RequestBody AddFeedbackRequestDto addFeedbackRequestDto) {
        AddFeedbackResponse result = addFeedbackUseCase.addFeedback(toAddFeedbackRequest(addFeedbackRequestDto));

        return toAddFeedbackResponseDto(result);
    }
}
