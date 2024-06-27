package shop.mtcoding.ssespring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class SseController {
    @Autowired
    private SseService sseService;

    // UTF-8 데이터만 보낼 수 있음, 바이너리 데이터 지원 x
    @GetMapping(path = "/emitter", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    // 서버가 클라이언트에게 보내는 지속적 데이터 스트림을 처리하기 위한 MIME 타입이다.
    public SseEmitter subscribe() {
        //SseEmitter는 서버에서 클라이언트로 이벤트를 전달할 수 있습니다.
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE); // 무한 타임아웃을 가진 에미터 객체가 생성됨
        sseService.addEmitter(emitter);
        sseService.sendEvents();
        
        return emitter;
    }
}
