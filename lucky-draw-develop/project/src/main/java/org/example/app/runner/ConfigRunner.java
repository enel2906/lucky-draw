package org.example.app.runner;

import org.example.app.service.SessionService;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ConfigRunner implements ApplicationRunner {

    private final SessionService sessionService;

    public ConfigRunner(SessionService sessionService){
        this.sessionService = sessionService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Xóa toàn bộ dữ liệu trong collection session khi ứng dụng khởi động
        sessionService.deleteAllSessions();
    }
}
