import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LogsComponent} from "./components/logs/logs.component";

const routes: Routes = [
  {path: '', redirectTo: '/logs', pathMatch: 'full'},
  {path: 'logs', component: LogsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
