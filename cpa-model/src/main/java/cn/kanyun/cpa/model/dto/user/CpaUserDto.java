package cn.kanyun.cpa.model.dto.user;


import cn.kanyun.cpa.model.entity.BaseEntity;

/**
 * Created by Administrator on 2017/7/11.
 */
public class CpaUserDto extends BaseEntity {

    private String userName;
    private String password;
    private String email;
    private String validateCode; //验证码
    private String isRememberMe;

    public String getIsRememberMe() {
        return isRememberMe;
    }
    public void setIsRememberMe(String isRememberMe) {
        this.isRememberMe = isRememberMe;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }


}
