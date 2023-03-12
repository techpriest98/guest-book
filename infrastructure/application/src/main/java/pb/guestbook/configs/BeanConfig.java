package pb.guestbook.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pb.guestbook.port.output.feedback.FeedbackPort;
import pb.guestbook.service.FeedbackService;

@Configuration
public class BeanConfig {
    @Bean
    public FeedbackService feedbackService(FeedbackPort feedbacksRepository) {
        return new FeedbackService(feedbacksRepository);
    }
}
