package kr.ac.jejunu.userdao;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    static UserDao userDao;

    @BeforeAll
    static public void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void get() throws SQLException {
        Integer id = 1;
        String name = "hulk";
        String password = "1234";

        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException {
        String name = "헐크";
        String password = "1592";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);

        assertThat(user.getId(), greaterThan(0));

        User insertedUser = userDao.get(user.getId());

        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));

    }

    @Test
    public void update() throws SQLException {
        String name = "헐크";
        String password = "1592";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);

        user.setName("헐크크크");
        user.setPassword("2948");

        userDao.update(user);

        User updatedUser = userDao.get(user.getId());

        assertThat(updatedUser.getId(), is(user.getId()));
        assertThat(updatedUser.getName(), is(user.getName()));
        assertThat(updatedUser.getPassword(), is(user.getPassword()));

    }

    @Test
    public void delete() throws SQLException {
        String name = "헐크";
        String password = "159211";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);

        userDao.delete(user.getId());

        User deletedUser = userDao.get(user.getId());

        assertThat(deletedUser, nullValue());
    }

//    @Test
//    public void hallaGet() throws SQLException, ClassNotFoundException {
//        Integer id = 1;
//        String name = "hulk";
//        String password = "1234";
//        UserDao userDao = new UserDao(new HallaConnectionMaker());
//        User user = userDao.get(id);
//        assertThat(user.getId(), is(id));
//        assertThat(user.getName(), is(name));
//        assertThat(user.getPassword(), is(password));
//    }
//
//    @Test
//    public void hallaInsert() throws SQLException, ClassNotFoundException {
//        String name = "헐크";
//        String password = "1592";
//
//        User user = new User();
//        user.setName(name);
//        user.setPassword(password);
//
//        UserDao userDao = new UserDao(new HallaConnectionMaker());
//        userDao.insert(user);
//
//        assertThat(user.getId(), greaterThan(0));
//
//        User insertedUser = userDao.get(user.getId());
//
//        assertThat(insertedUser.getId(), is(user.getId()));
//        assertThat(insertedUser.getName(), is(user.getName()));
//        assertThat(insertedUser.getPassword(), is(user.getPassword()));
//
//    }
}
