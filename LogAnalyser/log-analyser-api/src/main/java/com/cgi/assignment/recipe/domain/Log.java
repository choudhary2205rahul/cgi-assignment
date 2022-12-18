package com.cgi.assignment.recipe.domain;

import lombok.*;

import java.util.Objects;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    private String timestamp;
    private LogType logType;
    private String threadName;
    private String message;
    private int count;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return logType == log.logType && message.equals(log.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logType, message);
    }
}
