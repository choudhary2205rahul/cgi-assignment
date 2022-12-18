package com.cgi.assignment.recipe.domain;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LogsResponse {

    private List<Log> logs;
    private int count;
}
