package cn.chfnb.pojo;

/**
 * 用户
 */
public class User {
    private Long userId;

    public User(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
