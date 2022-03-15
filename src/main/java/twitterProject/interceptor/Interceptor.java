package twitterProject.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import twitterProject.dao.UserDao;
import twitterProject.entity.User;
import twitterProject.exceptions.CommonException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Interceptor implements HandlerInterceptor {
    @Autowired
    UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("GET") && !request.getRequestURI().contains("Form")) {
            System.out.println("prehandle");
            boolean b = validateUser(request.getParameter("email"));
            System.out.println(b);
            if (!b) {
                throw new CommonException("email not found", HttpStatus.BAD_REQUEST);
            }
        }
        return true;
    }

    public boolean validateUser(String email) {
        User userByEmail = userDao.findByEmail(email);
        return userByEmail != null && userByEmail.getEmail() != null;
    }
}