package pb.guestbook.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.security.web.firewall.RequestRejectedHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class ControllerAdvice implements RequestRejectedHandler {
    private static final String BAD_REQUEST = "Bad request";

    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto adminValidationException(@NonNull ValidationException e) {
        return new ErrorDto(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @Override
    public void handle(
        HttpServletRequest request,
        HttpServletResponse response,
        RequestRejectedException requestRejectedException
    ) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), BAD_REQUEST);
    }
}
