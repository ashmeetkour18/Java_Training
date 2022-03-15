package twitterProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import twitterProject.Utility.Utility;
import twitterProject.dao.FollowerDao;
import twitterProject.dao.UserDao;
import twitterProject.entity.Follower;
import twitterProject.entity.User;
import twitterProject.exceptions.CommonException;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FollowerController {
    @Autowired
    UserDao userDao;
    @Autowired
    FollowerDao followerDao;

    Utility utility = new Utility();

    @PostMapping("/followUser")
    public ModelAndView follow(@RequestParam MultiValueMap<String, String> requestBodyMap) {
        String userEmail = requestBodyMap.get("userEmail").get(0);
        String followerEmail = requestBodyMap.get("friendEmail").get(0);
        if (!"".equals(userEmail) && !"".equals(followerEmail)) {
            User user = userDao.findByEmail(userEmail);
            if (userEmail != followerEmail && userEmail.equals(user.getEmail())) {
                User followerUser = userDao.findByEmail(followerEmail);
                List<Follower> followersOfUser = followerDao.findAll(user.getId()).stream().filter
                        (follower -> follower.getFollowerUserId() == followerUser.getId()).collect(Collectors.toList());
                if (followersOfUser.size() == 0) {
                    Follower follower = new Follower(followerUser.getId(), user);
                    followerDao.create(follower);
                    ModelAndView modelAndView = new ModelAndView("profile");
                    modelAndView.getModel().put("email", userEmail);
                    modelAndView.getModel().put("name", user.getName());
                    return modelAndView;
                }
                throw new CommonException("You have already followed this user", HttpStatus.BAD_REQUEST);
            }
        }
        throw new CommonException("Invalid Data", HttpStatus.BAD_REQUEST);
    }
}
