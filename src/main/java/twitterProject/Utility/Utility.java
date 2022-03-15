package twitterProject.Utility;

import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.ModelAndView;
import twitterProject.dao.TweetDao;
import twitterProject.dao.UserDao;
import twitterProject.entity.Tweet;
import twitterProject.entity.User;

import java.util.List;
import java.util.regex.Pattern;


public class Utility {


    public static ModelAndView errorMessageModelAndView(String message) {
        System.out.println("in error message");
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.getModel().put("message", message);
        return modelAndView;
    }

    public boolean isUserValid(MultiValueMap<String, String> map, UserDao userDao) {
        String email = map.get("email").get(0);
        String password = map.get("password").get(0);
        System.out.println(email + " email");
        System.out.println(password + " password");
        User user = userDao.findByEmail(email);
        if (user != null)
            return user.getPassword().equals(password);
        else
            return false;
    }

    public boolean containsInvalidChars(String name) {
        return ((name != null) && (!name.equals("")) && (name.matches("^[a-zA-Z]*$")));
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\." + "[a-zA-Z0-9_+&-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;

        return pat.matcher(email).matches();
    }

    public User userByEmailId(String email, UserDao userDao) {
        List<User> resultList = userDao.readByEmail(email);
        System.out.println(resultList);
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    public List<Tweet> tweetByUserId(Integer id, TweetDao tweetDao) {
        return tweetDao.readByUserId(id);

    }

    public boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        if (password == null)
            return false;
        return pattern.matcher(password).matches();

    }
}