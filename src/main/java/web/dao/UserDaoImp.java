package web.dao;


import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext(unitName = "entityManagerFactoryBean")
    private EntityManager entityManager;

    @Override
    public List<User> showAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
        ;
    }

    @Override
    public void deleteUser(User user) {
        String hql = "delete from User where id=:id";
        entityManager.createQuery(hql).setParameter("id", user.getId()).executeUpdate();
    }

    @Override
    public void updateUser(User user) {
        String hql = "update User "
                + "SET name = :name "
                + ", surname = :surname "
                + ", age = :age "
                + ", email =: email"
                + " where id = :id";
        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("name", user.getName());
        query.setParameter("surname", user.getSurname());
        query.setParameter("age", user.getAge());
        query.setParameter("email", user.getEmail());
        query.setParameter("id", user.getId());
        query.executeUpdate();
    }

    @Override
    public User showUser(int id) {
        String hql = "select u from User u where id=:id";
        return entityManager.createQuery(hql, User.class).setParameter("id", id).getSingleResult();
    }
}
