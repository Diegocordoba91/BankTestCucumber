package utilities;

public class GlobalVariables {
    
    private static GlobalVariables instance;
    private String url;
    private String name;
    private String lastName;
    private String userName;
    private String password;
    private int idUser;

    private GlobalVariables() {}

    public static GlobalVariables getInstance(){
        if(instance==null)
            instance = new GlobalVariables();

        return instance;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return this.name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    

}
