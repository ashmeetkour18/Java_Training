package day14;

class Follower {
    private Integer followerUserId;

    public Follower() {
    }

    public Follower(Integer followerUserId) {
        this.followerUserId = followerUserId;
    }

    @Override
    public String toString() {
        return "Follower{" +
                "followerUserId=" + followerUserId +
                '}';
    }

    public Integer getFollowerUserId() {
        return followerUserId;
    }

    public void setFollowerUserId(Integer followerUserId) {
        this.followerUserId = followerUserId;
    }
}