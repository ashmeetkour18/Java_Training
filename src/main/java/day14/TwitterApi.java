package day14;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
public class TwitterApi {
    private static final List<Tweet> tweets = new ArrayList<>();
    private static final List<User> users = Arrays.asList(new User("user1", 123, Arrays.asList(new Follower(1234))), new User("user2", 13, Arrays.asList(new Follower(1235))));
    private static final Map<String, User> userProfile = new HashMap<>();

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> requestBodyMap) {
        String email = requestBodyMap.get("email");
        String name = requestBodyMap.get("name");
        String password = requestBodyMap.get("password");
        ResponseEntity<String> responseEntity = null;
        if (!userProfile.containsKey(email)) {
            System.out.println("email - " + email + " name- " + name);
            System.out.println(isValidEmail(email) + " " + containsInvalidChars(name));
            if (isValidEmail(email) && containsInvalidChars(name) && isValidPassword(password)) {
                System.out.println(new User(name, email.hashCode(), password));
                userProfile.put(email, new User(name, Math.abs(email.hashCode()), password));
                return new ResponseEntity<>("User added successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Invalid Input", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("User already exist", HttpStatus.CONFLICT);

    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pattern.matcher(email).matches();
    }

    private boolean containsInvalidChars(String name) {

        return ((name != null) && (!name.equals(""))
                && (name.matches("^[a-zA-Z]*$")));
    }

    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        if (password == null)
            return false;
        return pattern.matcher(password).matches();
    }

    @GetMapping("/tweets")
    public ResponseEntity<?> getTweets(@RequestParam String email, @RequestParam String password) {
        System.out.println("in getTweets");
        System.out.println(userProfile.get(email).getPassword().equals(password));
        System.out.println(userProfile.containsKey(email));
        System.out.println(userProfile.get(email));
        if (userProfile.containsKey(email) && userProfile.get(email).getPassword().equals(password)) {
            return new ResponseEntity<List<Tweet>>(tweets.stream()
                    .filter(tweet -> tweet.getUserId() == Math.abs(email.hashCode())).collect(Collectors.toList())
                    , HttpStatus.OK);
        }
        return new ResponseEntity<String>("user not match", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/tweet")
    public ResponseEntity<String> postTweet(@RequestBody Map<String, String> tweetBody) {
        if (userProfile.containsKey(tweetBody.get("email"))
                && userProfile.get(tweetBody.get("email")).getPassword().equals(tweetBody.get("password"))) {
            if (tweetBody.containsKey("tweet")) {
                Integer userId = userProfile.get(tweetBody.get("email")).getUserid();
                tweets.add(new Tweet(tweetBody.get("tweet"), userId, Timestamp.from(Instant.now())));
                return new ResponseEntity<String>("Tweet Successfully posted", HttpStatus.OK);
            }
            return new ResponseEntity<String>("No Tweets found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Invalid userId or password", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/follow")
    public ResponseEntity<String> follow(@RequestBody Map<String, String> requestBodyMap) {
        if (requestBodyMap.containsKey("userId") && requestBodyMap.containsKey("followerId")) {
            String userId = requestBodyMap.get("userId");
            String followerId = requestBodyMap.get("followerId");
            if (userId != followerId && userProfile.containsKey(userId) && userProfile.containsKey(followerId)) {
                userProfile.get(userId).getFollowers().add(new Follower(userProfile.get(followerId).getUserid()));
                System.out.println(userProfile.get(userId).getFollowers());
                return new ResponseEntity<String>("Following", HttpStatus.OK);
            }
            return new ResponseEntity<String>("User not found in Data", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    private ResponseEntity<String> updateRecord(@RequestBody Map<String, String> requestBodyMap) {
        String email = requestBodyMap.get("email");
        String name = requestBodyMap.get("name");
        String password = requestBodyMap.get("password");
        if (!containsInvalidChars(name)) {
            return new ResponseEntity<>("name contains invalid characters",
                    HttpStatus.BAD_REQUEST);
        } else if (userProfile.containsKey(email) && userProfile.get(email).getPassword().equals(password)) {
            User user = userProfile.get(email);
            String currName = user.getName();
            if (currName.equals(name)) {
                return new ResponseEntity<>("No change required",
                        HttpStatus.OK);
            } else {
                user.setName(name);
                userProfile.put(email, user);
                return new ResponseEntity<>("Successfully Updated",
                        HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("User doesn't exist",
                HttpStatus.NOT_FOUND);

    }

    //    User can delete account  -->DELETE
    @DeleteMapping("/delete")
    String deleteRecord(@RequestParam String email) {
        userProfile.remove(email);
        return email + " successfully deleted";
    }
}







