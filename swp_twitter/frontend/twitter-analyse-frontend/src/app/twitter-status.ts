import { Analyse } from './analyse-ergebnis/analyse';

export class TwitterStatus{
    constructor(readonly id: number, readonly tweetId: number, readonly nutzerNamen: string, readonly erstelltAm: Date, readonly ergebnis: Analyse){
    }
}