import { AnalyseDto } from './analyse-ergebnis/analyse-dto';

export class TwitterStatusDto{
    constructor(readonly id: number, readonly tweetId: number, readonly nutzerNamen: string, readonly erstelltAm: number, readonly ergebnis: AnalyseDto){}
}