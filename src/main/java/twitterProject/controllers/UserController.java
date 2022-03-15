package twitterProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import twitterProject.Utility.Utility;
import twitterProject.dao.UserDao;
import twitterProject.entity.User;
import twitterProject.exceptions.CommonException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {


    private final Map<String, User> userProfile = new HashMap<>();
    @Value("${followers.allowed}")
    boolean allowed;
    Utility utility = new Utility();
    @Autowired
    private UserDao userDao;

    public UserController(UserDao userDao) {
        List<User> list = userDao.readAll();
        for (User user : list) {
            userProfile.put(user.getEmail(), user);
        }
    }

    @GetMapping("/loginForm")
    public ModelAndView loginForm() {
        return new ModelAndView("login");
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView login(@RequestBody MultiValueMap<String, String> formData) {

        if (!utility.isUserValid(formData, userDao)) {
            throw new CommonException("Wrong Credentials", HttpStatus.BAD_REQUEST);
        }
        ModelAndView modelAndView = new ModelAndView("profile");
        String email = formData.get("email").get(0);
        String name = userProfile.get(email).getName();
        modelAndView.getModel().put("name", name);
        modelAndView.getModel().put("email", email);
        return modelAndView;
    }

    @GetMapping("/displayUserDetails")
    public ModelAndView getUserDetails(@RequestParam String email) {
        //System.out.println(followShow+" value of followshow");
        ModelAndView modelAndView = new ModelAndView("usersTwitter");
        System.out.println(email);
        if (userProfile.isEmpty())
            allAccDetails();
        List<User> users = new ArrayList<>();
        for (Map.Entry entry : userProfile.entrySet()) {
            users.add((User) entry.getValue());
        }
        if (users.size() > 0) {
            modelAndView.getModel().put("userEmail", email);
            modelAndView.getModel().put("users", users);
            modelAndView.getModel().put("allowed", allowed);
            return modelAndView;
        }
        throw new CommonException("No user Exist", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/fetchUsers")
    Map<String, User> allAccDetails() {
        List<Object[]> list = userDao.read();
        for (int i = 0; i < list.size(); i++) {
            Object[] arr = list.get(i);
            User user = new User(arr[0].toString(), arr[1].toString(), arr[2].toString());
            userProfile.put(arr[1].toString(), user);
        }
        return userProfile;
    }

    @PostMapping(value = "/registerHtml", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView registerHtml() {
        return new ModelAndView("registerPage");
    }

    @PostMapping(value = "/registerNew", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView registerNew(@RequestBody MultiValueMap<String, String> formData) {
        ModelAndView modelAndView = new ModelAndView("newUsers");
        String email = formData.get("email").get(0);
        String name = formData.get("name").get(0);
        String password = formData.get("password").get(0);
        if (utility.isValidEmail(email) && utility.containsInvalidChars(name) && utility.isValidPassword(password)) {
            User user = new User(name, email, password);
            userDao.create(user);
            modelAndView.getModel().put("email", email);
            modelAndView.getModel().put("name", name);
            modelAndView.getModel().put("password", password);
            return modelAndView;
        }
        throw new CommonException("Invalid data provided", HttpStatus.BAD_REQUEST);
    }
}