public class UserAuthentication {
    private static UserAuthentication instance;
    private String currentUser;

    private UserAuthentication() {}

    public static UserAuthentication getInstance() {
        if (instance == null) {
            instance = new UserAuthentication();
        }
        return instance;
    }

    public boolean login(String username, String password) {
        if (username.equals("admin") && password.equals("password")) {
            currentUser = username;
            return true;
        }
        return false;
    }

    public String getCurrentUser() {
        return currentUser;
    }
}
