package linked.linkedtest;

/**
 * Created by ingle on 3/26/2017.
 */

public class userID_CLASS {
    private static userID_CLASS myinstance;
    private static String email;

    private userID_CLASS(){}

    public void setEmail(String email_address){
        userID_CLASS.email = email_address;
    }

    public String getEmail(){
        return userID_CLASS.email;
    }

    public static synchronized userID_CLASS getInstance(){
        if(myinstance==null){
            myinstance = new userID_CLASS();
        }

        return myinstance;
    }
}
