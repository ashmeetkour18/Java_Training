package twitterProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import twitterProject.Utility.Utility;
import twitterProject.dao.TweetDao;
import twitterProject.dao.UserDao;
import twitterProject.entity.Tweet;
import twitterProject.entity.User;
import twitterProject.exceptions.EmailNotFoundException;
import twitterProject.exceptions.TweetNotFoundException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TweetController {

    private final Map<String, User> userProfile = new HashMap<>();
    Utility utility = new Utility();
    @Autowired
    private UserDao userDao;
    @Autowired
    private TweetDao tweetDao;

    public TweetController(UserDao userDao) {
        List<User> list = userDao.readAll();
        for (User user : list) {
            userProfile.put(user.getEmail(), user);
        }
    }

    @GetMapping("/getTweets")
    public ModelAndView fetchTweets(@RequestParam String email) throws TweetNotFoundException, EmailNotFoundException {
        //Using global Exception
        if (email == null)
            throw new EmailNotFoundException();
        List<Tweet> tweetList = tweetDao.readByEmail(email);
        ModelAndView modelAndView = new ModelAndView("tweets");
        modelAndView.getModel().put("tweets", tweetList);
        modelAndView.getModel().put("email", email);
        modelAndView.getModel().put("name", userProfile.get(email).getName());
        if (tweetList.size() == 0)
            throw new TweetNotFoundException();
        return modelAndView;
    }

    //Local Exception
    @ExceptionHandler(value = TweetNotFoundException.class)
    public ModelAndView handleException(Exception exception) {
        return Utility.errorMessageModelAndView("Tweet Not found");
    }

    @PostMapping(value = "/tweetNew", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView addTweet(@RequestBody MultiValueMap<String, String> formData) {
        ModelAndView modelAndView = new ModelAndView("newtweet");
        String email = formData.get("email").get(0);
        String tweet = formData.get("tweet").get(0);
        if (email != "" && tweet != "") {
            User user = utility.userByEmailId(email, userDao);
            if (user == null)
                return Utility.errorMessageModelAndView("User doesn't exist");
            if (email.equals(user.getEmail())) {
                Tweet tweet1 = new Tweet(tweet, Timestamp.from(Instant.now()), user, email);
                tweetDao.create(tweet1);
                modelAndView.getModel().put("email", email);
                modelAndView.getModel().put("tweet", tweet);
                return modelAndView;
            }
            return Utility.errorMessageModelAndView("Tweet cannot be empty");
        }
        return Utility.errorMessageModelAndView("something went wrong");
    }

    @PostMapping(value = "/tweetHtml", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView tweetsUser() {
        return new ModelAndView("tweetsUser");
    }
}