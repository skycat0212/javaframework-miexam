package kr.ac.jejunu.userdao;

public class DaoFactory {
    public UserDao getUserDao() {
        return new UserDao(getConnection());
    }

    private JejuConnectionMaker getConnection() {
        return new JejuConnectionMaker();
    }
}
