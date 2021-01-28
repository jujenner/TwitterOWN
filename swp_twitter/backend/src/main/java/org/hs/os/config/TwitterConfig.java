package org.hs.os.config;

import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Component
public class TwitterConfig {

    // Methode zur Identifizierung bei Twitter
    // Quelle: http://twitter4j.org/en/configuration.html
    public Twitter getTwitter() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder()
                .setOAuthConsumerKey(TwitterProperties.CONSUMER_KEY)
                .setOAuthConsumerSecret(TwitterProperties.CONSUMER_SECRET)
                .setOAuthAccessToken(TwitterProperties.ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TwitterProperties.ACCESS_TOKEN_SECRET);

        return new TwitterFactory(configurationBuilder.build()).getInstance();
    }
}


