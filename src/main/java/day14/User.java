package day14;

import java.util.ArrayList;
import java.util.List;

class User {

    private String name;
    private Integer userid;
    private String password;
    private List<Follower> followers = new ArrayList<>();

    public User(String name, Integer userid, String password) {
        this.name = name;
        this.userid = userid;
        this.password = password;
    }

    public User(String name, Integer userid, String password, List<Follower> followers) {
        this.name = name;
        this.userid = userid;
        this.password = password;
        this.followers = followers;
    }

    public User() {
    }

    public User(String name, Integer userid, List<Follower> followers) {
        this.name = name;
        this.userid = userid;
        this.followers = followers;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userid=" + userid +
                ", password='" + password + '\'' +
                ", followers=" + followers +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public List<Follower> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Follower> followers) {
        this.followers = followers;
    }
}