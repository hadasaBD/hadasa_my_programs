package com.company;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hackeru on 3/21/2017.
 */
public class User {
    public String userName;
    public String password;
    Map<String, String> dataUser = new HashMap<>();


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {
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
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(obj == this)
            return true;
        if(obj instanceof User){
            User other = (User)obj;
            return this.userName.equals(other.userName)
                    && this.password.equals(other.password);
        }
        return false;
    }

    /**
     * userName must contain lower case letters and digits only
     * all the digits must appear at the end of the string
     * must not begin with a digit
     * length must be 3 to 20
     * @param userName
     * @return
     */
    static boolean isUserNameValid(String userName) {
        if (userName.length() < 3 || userName.length() > 20)
            return false;
        byte[] userNameBytes = userName.getBytes();
        boolean digitFound = false;
        for (int i = 0; i < userNameBytes.length; i++) {
            int charCode = userNameBytes[i];
            boolean isDigit = charCode >= 48 && charCode <= 57;
            boolean isSmallLetter = charCode >= 97 && charCode <= 122;
            if (isDigit) {
                if (i == 0)
                    return false;
                digitFound = true;
            } else {
                if (digitFound)
                    return false;
            }
            if (!isDigit && !isSmallLetter)
                return false;
        }
        return true;
    }

    static boolean isPasswordValid(String userName){
        if(userName.length() < 3 || userName.length() > 12)
            return false;
        byte[] userNameBytes = userName.getBytes();
        for (int i = 0; i < userNameBytes.length; i++) {
            int charCode = userNameBytes[i];
            if(charCode<48 || charCode>126)
                return false;
        }
        return true;
    }

    static User getUser(Map<String,String>  dataUser,String userName ){
        Collection<String> valus
        for(User u : dataUser.){
            if(u.getUserName().toUpperCase().equals(userName.toUpperCase()))
                return u;
        }
        return null;
    }

}
