package pb.guestbook.feedback;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pb.guestbook.dtos.AddFeedbackRequestDto;
import pb.guestbook.dtos.AddFeedbackResponseDto;

public interface AddFeedBackAPI {
    @RequestMapping(
        value = {"/feedback/add"},
        consumes = {"application/json"},
        produces = {"application/json"},
        method = RequestMethod.POST
    )
    AddFeedbackResponseDto addFeedback(@RequestBody(required = false) AddFeedbackRequestDto addFeedbackRequestDto);
}
