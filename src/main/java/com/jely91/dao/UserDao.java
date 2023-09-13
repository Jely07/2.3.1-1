package com.jely91.dao;

import com.jely91.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    private List<User> users;
    private static int COUNT;
    {
        users=new ArrayList<>();
        users.add(new User(++COUNT,"Pom","Pimkin","sales",300));
        users.add(new User(++COUNT,"Pik","Pim","IT",900));
        users.add(new User(++COUNT,"Puk","Pimk","HR",800));
        users.add(new User(++COUNT,"Zak","Ko","sales",900));

    }
    public List<User> getUsers(){
        return users;
    }
    public User getUser(int id){
        return users.stream().filter(user -> user.getId()== id).findAny().orElse(null);
    }

    public void save(User user) {
        user.setId(++COUNT);
        users.add(user);
    }

    public void update(int id, User user) {
        User userTobeUpdate = getUser(id);
        userTobeUpdate.setName(user.getName());
        userTobeUpdate.setSurname(user.getSurname());
        userTobeUpdate.setDepartment(user.getDepartment());
        userTobeUpdate.setSalary(user.getSalary());
    }

    public void delete(int id) {
        users.removeIf(user -> user.getId()==id);
    }
}
