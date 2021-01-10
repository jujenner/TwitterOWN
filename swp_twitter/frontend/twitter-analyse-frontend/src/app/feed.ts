import { TwitterStatus } from './twitter-status';

export class Feed {
  constructor(readonly id: number, readonly keyword: string,
    readonly erstelltAm: Date, readonly suchintervall: Date,
  readonly status: string, readonly dauer: Date, readonly twitterStatus: TwitterStatus[]) {
  }
}
