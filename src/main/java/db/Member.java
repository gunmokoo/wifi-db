package db;

public class Member {
    private String memberType;
    private String userId;
    private String password;
    private String name;

    public String getMemberType() {
        return memberType;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}
