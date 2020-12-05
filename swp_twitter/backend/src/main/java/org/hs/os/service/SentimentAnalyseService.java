package org.hs.os.service;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import org.hs.os.model.SentimentTyp;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class SentimentAnalyseService {

    public SentimentTyp analyse(String tweet) {
        SentimentTyp sentimentTyp = SentimentTyp.NEUTRAL;
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, parse, sentiment");
        StanfordCoreNLP nlp = new StanfordCoreNLP(props);
        Annotation annotation = nlp.process(tweet);
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            switch (RNNCoreAnnotations.getPredictedClass(tree)) {
                case 0:
                    sentimentTyp = SentimentTyp.SEHR_NEGATIV;
                    break;
                case 1:
                    sentimentTyp = SentimentTyp.NEGATIV;
                    break;
                case 3:
                    sentimentTyp = SentimentTyp.POSITIV;
                    break;
                case 4:
                    sentimentTyp = SentimentTyp.SEHR_POSITIV;
                    break;
                default:
                    sentimentTyp = SentimentTyp.NEUTRAL;
            }
        }

        return sentimentTyp;
    }
}
