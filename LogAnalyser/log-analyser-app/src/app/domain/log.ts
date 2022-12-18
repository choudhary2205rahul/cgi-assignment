export class Log {
  constructor(
    public timestamp: string,
    public logType: string,
    public threadName: string,
    public message: string,
    public count: number,
  ){

  }
}
