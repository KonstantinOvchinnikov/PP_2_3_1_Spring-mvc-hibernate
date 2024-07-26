package web.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;


import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> showAllUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User ");
        return query.getResultList();
    }

    @Override
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().persist(user);;
    }

    @Override
    public void deleteUser(User user) {
        String hql = "delete from User where id=:id";
        sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", user.getId()).executeUpdate();
    }

    @Override
    public void updateUser(User user) {
        String hql = "update User "
                + "SET name = :name "
                + ", surname = :surname "
                + ", age = :age "
                + ", email =: email"
                + " where id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("name", user.getName());
        query.setParameter("surname", user.getSurname());
        query.setParameter("age", user.getAge());
        query.setParameter("email", user.getEmail());
        query.setParameter("id", user.getId());
        query.executeUpdate();
    }

    @Override
    public User showUser(int id) {
        String hql = "from User where id=:id";
        return (User) sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", id).getSingleResult();
    }

}
