package com.cgi.assignment.recipe.service.impl;

import com.cgi.assignment.recipe.domain.Log;
import com.cgi.assignment.recipe.domain.LogType;
import com.cgi.assignment.recipe.exception.LogsNotFoundException;
import com.cgi.assignment.recipe.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class LogServiceImpl implements LogService {

    // Regex to fetch the data from logs
    final static String timestampRgx = "(?<timestamp>\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3})";
    final static String levelRgx = "(?<level>INFO|ERROR|WARN|DEBUG)";
    final static String threadRgx = "\\[(?<thread>[^\\]]+)]";
    final static String textRgx = "(?<text>.*?)(?=\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3}|\\Z)";
    private static Pattern PatternFullLog = Pattern.compile(timestampRgx + " " + levelRgx + "\\s+" + threadRgx + "\\s+" + textRgx, Pattern.DOTALL);

    // Map for INFO|ERROR|WARN|DEBUG
    Map<String, Log> infoMap = new HashMap<>();
    Map<String, Log> warnMap = new HashMap<>();
    Map<String, Log> debugMap = new HashMap<>();
    Map<String, Log> errorMap = new HashMap<>();

    Map<String, Integer> map = new HashMap<>();


    public LogServiceImpl() {
        try {
            // read log file
            File recipesFile = new ClassPathResource("logFile-2018-09-10.log").getFile();
            FileInputStream fileInputStream = new FileInputStream(recipesFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            String lines;

            while ((lines = bufferedReader.readLine()) != null) {
                Matcher matcher = PatternFullLog.matcher(lines);
                while (matcher.find()) {
                    if (matcher.group("level").contains(LogType.INFO.name())) {
                        extracted(matcher, LogType.INFO, map);
                    } else if (matcher.group("level").contains(LogType.DEBUG.name())) {
                        extracted(matcher, LogType.DEBUG, map);
                    } else if (matcher.group("level").contains(LogType.WARN.name())) {
                        extracted(matcher, LogType.WARN, map);
                    } else if (matcher.group("level").contains(LogType.ERROR.name())) {
                        extracted(matcher, LogType.ERROR, map);
                    }
                }
            }
        } catch (IOException e) {
            log.error("Error file reading log json from resources {}", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Log> getLogs(String input) {
        if (input.equalsIgnoreCase("INFO")) {
            return getLogs(infoMap);
        } else if (input.equalsIgnoreCase("DEBUG")) {
            return getLogs(debugMap);
        } else if (input.equalsIgnoreCase("WARN")) {
            return getLogs(warnMap);
        } else if (input.equalsIgnoreCase("ERROR")) {
            return getLogs(errorMap);
        } else {
            throw new LogsNotFoundException("Logs not found with given input : " + input);
        }

    }

    /**
     * Method to get sorted list of logs from map
     * @param mapInput
     * @return
     */
    private List<Log> getLogs(Map<String, Log> mapInput) {
        List<Log> sortedList = new ArrayList<>();
        for (Map.Entry<String, Log> map : mapInput.entrySet()) {
            sortedList.add(map.getValue());
        }
        Collections.sort(sortedList, (o1, o2) -> o2.getCount() - (o1.getCount()));
        return sortedList;
    }

    /**
     * Refactor the code to reduce the lines of code
     * @param matcher
     * @param logType
     * @param map
     */
    private void extracted(Matcher matcher, LogType logType, Map<String, Integer> map) {
        String text = matcher.group("text");
        try {
            Log log = Log.builder()
                    .timestamp(matcher.group("timestamp"))
                    .logType(LogType.valueOf(matcher.group("level")))
                    .threadName(matcher.group("thread"))
                    .message(matcher.group("text"))
                    .build();

            if (LogType.INFO.equals(logType)) {
                if(infoMap.containsKey(log.getMessage())){
                    log.setCount(infoMap.get(text).getCount()+1);
                } else {
                    log.setCount(1);
                }
                infoMap.put(text, log);
            } else if(LogType.WARN.equals(logType)) {
                if(warnMap.containsKey(log.getMessage())){
                    log.setCount(warnMap.get(text).getCount()+1);
                } else {
                    log.setCount(1);
                }
                warnMap.put(text, log);
            } else if(LogType.DEBUG.equals(logType)) {
                if(debugMap.containsKey(log.getMessage())){
                    log.setCount(debugMap.get(text).getCount()+1);
                } else {
                    log.setCount(1);
                }
                debugMap.put(text, log);
            } else if(LogType.ERROR.equals(logType)) {
                if(errorMap.containsKey(log.getMessage())){
                    log.setCount(errorMap.get(text).getCount()+1);
                } else {
                    log.setCount(1);
                }
                errorMap.put(text, log);
            }
        } catch (Exception e) {
            log.error("Error while counting occurance from logs {}", text);
        }
    }
}
