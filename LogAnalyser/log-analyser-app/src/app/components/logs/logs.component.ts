import {Component, OnInit} from '@angular/core';
import {Log} from "../../domain/log";
import {MatTableDataSource} from "@angular/material/table";
import {LogsService} from "../../service/logs.service";
import {ThemePalette} from "@angular/material/core";
import {MatRadioChange} from "@angular/material/radio";

export interface Task {
  name: string;
  completed: boolean;
  color: ThemePalette;
  subtasks?: Task[];
}

@Component({
  selector: 'app-logs',
  templateUrl: './logs.component.html',
  styleUrls: ['./logs.component.css']
})
export class LogsComponent implements OnInit{

  displayedColumns: string[] = ['timestamp','logType','threadName','message', 'count'];

  logs: Log[] = [];
  dataSource = new MatTableDataSource(this.logs);

  constructor(public logsService: LogsService) {
  }

  ngOnInit(): void {
    this.getLogs("INFO");
  }

  getLogs(input: string) {
    this.logsService.getLogs(input).subscribe(logs => {
      this.logs = logs.logs;

      this.dataSource = new MatTableDataSource(this.logs);
    });
  }

  // checked = false;
  // indeterminate = false;
  // labelPosition: 'before' | 'after' = 'after';
  // disabled = false;

logsGroup = [
    {"name": "INFO", ID: "info"},
    {"name": "WARN", ID: "warn"},
    {"name": "DEBUG", ID: "debug"},
    {"name": "ERROR", ID: "error"}
  ]
  chosenItem = this.logsGroup[0].name;

  radioChange(event: MatRadioChange) {
    this.logsService.getLogs(event.value).subscribe(logs => {
      this.logs = logs.logs;

      this.dataSource = new MatTableDataSource(this.logs);
    });
  }
}
