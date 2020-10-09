package jpabook.jpashop.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberAdvice {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseDto checkMember(IllegalStateException e) {
        return ResponseDto.of(e.getMessage());
    }

    public static class ResponseDto {
        private final String message;
        public ResponseDto(String message) {
            this.message = message;
        }
        public static ResponseDto of(String message) {
            return new ResponseDto(message);
        }
        public String getMessage() {
            return message;
        }
    }
}
