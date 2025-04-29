package ru.ilya.shopcraftercore.entity.goods;

import jakarta.persistence.*;
import ru.ilya.shopcraftercore.entity.auth.User;

import java.util.List;

@Entity
public class Store {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User owner;

    @OneToMany
    private List<Worker> workers;

    private String name;

    @OneToMany
    private List<Category> categories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public void addWorker(Worker worker) {
        workers.add(worker);
    }

    public void removeWorker(Worker worker) {
        workers.remove(worker);
    }
}
