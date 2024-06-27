package shop.mtcoding.ssespring;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SseService {
    /*
     * 주로 순회가 일어나는 용도로 사용할 때는 안전한 스레드 처리를 위해 CopyOnWriteArrayList를 사용
     */
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    // 이렇게 묶어서 컨트롤러에서 호출하면 에미터를 세트로 묶어서 생명 주기를 관리할 수 있다.
    public void addEmitter(SseEmitter emitter) {
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
    }

    @Scheduled(fixedRate = 1000) // 1초마다 이 메서드를 실행한다.
    public void sendEvents() {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send("Hello, World!");
            } catch (IOException e) {
                emitter.complete();
                emitters.remove(emitter);
            }
        }
    }
}
