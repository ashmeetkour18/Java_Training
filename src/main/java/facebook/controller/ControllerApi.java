package facebook.controller;

import facebook.dao.FriendDao;
import facebook.dao.UserDao;
import facebook.entity.Friend;
import facebook.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class ControllerApi {
    private final Map<String, User> userProfile = new HashMap<>();
    private final Map<Integer, List<Integer>> friend = new HashMap<>();
    @Autowired
    private UserDao userDao;
    @Autowired
    private FriendDao friendDao;

    public ControllerApi(UserDao userDao) {
        List<User> list = userDao.readAll();
        for (User user : list) {
            userProfile.put(user.getEmail(), user);
        }
    }

    /* public ControllerApi(FriendDao friendDao)
     {
         List<Friend> list=friendDao.readAll();
         for(Friend friend1:list){
 friend.put(friend1.getFriendId(),);
         }
     }*/
    @PostMapping(value = "/registerHtml", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView registerHtml() {
        ModelAndView modelAndView = new ModelAndView("registerPage");
        return modelAndView;
    }

    @GetMapping("/loginForm")
    public ModelAndView loginForm() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView login(@RequestBody MultiValueMap<String, String> formData) {
        if (!isUserValid(formData)) {
            System.out.println(formData);
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.getModel().put("error", "Invalid credentials");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("fbProfile");
        String email = formData.get("email").get(0);
        String name = userProfile.get(email).getName();
        modelAndView.getModel().put("name", name);
        modelAndView.getModel().put("email", email);
        return modelAndView;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView registerNew(@RequestBody MultiValueMap<String, String> formData) {
        ModelAndView modelAndView = new ModelAndView("newUsers");
        //System.out.println(formData);
        String email = formData.get("email").get(0);
        String name = formData.get("name").get(0);
        String password = formData.get("password").get(0);
        ModelAndView modelAndView1 = new ModelAndView("error");
        if (userByEmailId(email) == null) {
            if (isValidEmail(email) && containsInvalidChars(name) && isValidPassword(password)) {
                User user = new User(name, email, password);
                userDao.create(user);
                userProfile.put(email, user);
                modelAndView.getModel().put("email", email);
                modelAndView.getModel().put("name", name);
                modelAndView.getModel().put("password", password);
                return modelAndView;
            }
            modelAndView1.getModel().put("error", "Invalid data");
            return modelAndView1;
        }
        modelAndView1.getModel().put("error", "User already exist");
        return modelAndView1;
    }

    @PostMapping("/friend")
    @ResponseBody
    public ModelAndView followUsers(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String friendid = requestBody.get("friendId");
        String password = requestBody.get("password");
        Integer friend_id = userProfile.get(email).getId();
        Integer user_id = userProfile.get(friendid).getId();
        /*System.out.println("email "+ email);
        System.out.println("friendid "+friendid);
        System.out.println(friend_id+" mee");
        System.out.println(user_id+" user");*/
        ModelAndView modelAndView = new ModelAndView("follower");
        ModelAndView modelAndView1 = new ModelAndView("error");
        List<Friend> list = friendDao.readAll();
        System.out.println(list);
        if (!userProfile.containsKey(email)) {
            modelAndView1.getModel().put("error", "User doesn't exist");
            return modelAndView1;
        }
        if (friendid.equals(email)) {
            modelAndView1.getModel().put("error", "You cannot friend yourself");
            return modelAndView1;
        }

        if (userProfile.get(email) != null && userProfile.get(email).getPassword().equals(password)) {
            if (list.contains(email)) {
                friend.get(friend_id).add(user_id);
                int id = userProfile.get(friend_id).getId();
                Friend friend = new Friend();
                friend.setFriendId(user_id);
                friendDao.createMultiple(id, friend);
                System.out.println("added bu if");
            } else {
                List<Integer> list1 = new ArrayList<>();
                list1.add(user_id);
                friend.put(friend_id, list1);
                User user = userProfile.get(friendid);
                Friend friend = new Friend(friend_id, user);
                friendDao.create(friend);
                System.out.println("added by else");
            }
            modelAndView.getModel().put("userId", user_id);
            modelAndView.getModel().put("friendId", friend_id);
            return modelAndView;
        } else {
            modelAndView1.getModel().put("error", "Something went wrong");
            return modelAndView1;
        }

    }

    private ModelAndView errorMessageModelAndView(String message) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.getModel().put("message", message);
        return modelAndView;
    }

    private boolean isUserValid(MultiValueMap<String, String> map) {
        String email = map.get("email").get(0);
        String password = map.get("password").get(0);
        User user = userProfile.get(email);
        //System.out.println(user.getPassword()+" get poassword");
        //System.out.println(password);
        return user.getPassword().equals(password);
    }

    private boolean containsInvalidChars(String name) {
        return ((name != null) && (!name.equals("")) && (name.matches("^[a-zA-Z]*$")));
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\." + "[a-zA-Z0-9_+&-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;

        return pat.matcher(email).matches();
    }
    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        Pattern pattern = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        return pattern.matcher(password).matches();
    }

    private User userByEmailId(String email) {
        List<User> resultList = userDao.readByEmail(email);
        if (resultList.size() > 0) {
            User singleResult = resultList.get(0);
            return singleResult;
        }
        return null;
    }

}
