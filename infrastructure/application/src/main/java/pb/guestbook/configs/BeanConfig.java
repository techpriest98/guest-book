package pb.guestbook.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pb.guestbook.port.output.feedback.AddFeedbackPort;
import pb.guestbook.port.output.feedback.GetFeedbacksPort;
import pb.guestbook.service.FeedbackService;

@Configuration
public class BeanConfig {
    @Bean
    public FeedbackService getFeedbacksPort(
        GetFeedbacksPort getFeedbacksRepository,
        AddFeedbackPort addFeedbackRepository
    ) {
        return new FeedbackService(getFeedbacksRepository, addFeedbackRepository);
    }
}
