package com.cgi.assignment.recipe.service;

import com.cgi.assignment.recipe.domain.Log;

import java.util.List;
import java.util.Map;

public interface LogService {

    List<Log> getLogs(String input);
}
