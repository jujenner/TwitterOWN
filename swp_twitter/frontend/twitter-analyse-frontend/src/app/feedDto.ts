export class FeedDto {
  constructor(readonly id: number, readonly keyword: string,
    readonly erstelltAm: number, readonly suchIntervall: number,
  readonly status: string, readonly suchDauer: number) {
  }
}
