package pb.guestbook.feedback;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pb.guestbook.dtos.AddFeedbackRequestDto;
import pb.guestbook.dtos.AddFeedbackResponseDto;
import pb.guestbook.model.feedback.AddFeedbackResponse;
import pb.guestbook.port.input.feedback.AddFeedbackUseCase;

import static pb.guestbook.feedback.converter.FeedbackConverter.toAddFeedbackRequest;
import static pb.guestbook.feedback.converter.FeedbackConverter.toAddFeedbackResponseDto;

@RestController
@RequestMapping("/api")
public class FeedbackController {
    private final AddFeedbackUseCase addFeedbackUseCase;
    public FeedbackController(AddFeedbackUseCase addFeedbackUseCase) {
        this.addFeedbackUseCase = addFeedbackUseCase;
    }

    @PostMapping("/feedback/add")
    public AddFeedbackResponseDto addFeedback(@RequestBody AddFeedbackRequestDto addFeedbackRequestDto) {
        AddFeedbackResponse result = addFeedbackUseCase.addFeedback(toAddFeedbackRequest(addFeedbackRequestDto));

        return toAddFeedbackResponseDto(result);
    }
}
