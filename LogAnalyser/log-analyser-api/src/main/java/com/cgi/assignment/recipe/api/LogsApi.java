package com.cgi.assignment.recipe.api;

import com.cgi.assignment.recipe.domain.Log;
import com.cgi.assignment.recipe.domain.LogsResponse;
import com.cgi.assignment.recipe.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/logs")
public class LogsApi {

    private LogService logService;

    public LogsApi(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/{log-type}")
    public ResponseEntity<LogsResponse> getLogs(@PathVariable("log-type") String input) {
        List<Log> logs = logService.getLogs(input);
        return new ResponseEntity<>(LogsResponse.builder().logs(logs).count(logs.size()).build(), HttpStatus.OK);
    }
}
