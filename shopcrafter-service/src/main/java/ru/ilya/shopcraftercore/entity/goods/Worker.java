package ru.ilya.shopcraftercore.entity.goods;

import ru.ilya.shopcraftercore.entity.auth.User;
import jakarta.persistence.*;

@Entity
public class Worker {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Store store;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
