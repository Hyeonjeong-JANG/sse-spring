package shop.mtcoding.ssespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// 크론탭
@EnableScheduling // 스프링의 스케쥴링 기능을 활성화한다. 그러면 @Schedule가 붙은 작업이 주기적으로 실행되게 할 수 있다.
@SpringBootApplication
public class SseSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SseSpringApplication.class, args);
    }

}
