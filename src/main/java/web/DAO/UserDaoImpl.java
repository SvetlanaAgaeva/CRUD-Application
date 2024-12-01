package web.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllPeople() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
        System.out.println("EntityManager: " + entityManager);
        entityManager.persist(user);
        System.out.println("User saved: " + user);
    }

    @Override
    public void updateUser(int id, User newUser) {
        User userToUpdate = entityManager.find(User.class, id);
        if (userToUpdate != null) {
            userToUpdate.setName(newUser.getName());
            userToUpdate.setSurName(newUser.getSurName());
            entityManager.merge(userToUpdate);
        }
    }

    @Override
    public void deleteUserById(int id) {
        User userToDelete = entityManager.find(User.class, id);
        if (userToDelete != null) {
            entityManager.remove(userToDelete);
        }
    }
}

