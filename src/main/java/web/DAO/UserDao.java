package web.DAO;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllPeople();

    User getUserById(int id);

    void save(User user);

    void updateUser(int id, User newUser);

    void deleteUserById(int id);


}



