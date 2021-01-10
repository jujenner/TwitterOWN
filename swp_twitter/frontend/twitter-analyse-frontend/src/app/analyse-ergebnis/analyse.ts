export class Analyse {
    constructor(readonly id: number, readonly sentimentType: SentimentType){

    }
}

export enum SentimentType {
    SEHR_POSITIV = 0,
    POSITIV = 1,
    NEUTRAL = 2,
    NEGATIV = 3,
    SEHR_NEGATIV = 4
}