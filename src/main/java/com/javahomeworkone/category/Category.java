package com.javahomeworkone.category;

import com.javahomeworkone.list.List;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 45, name = "name")
    private String name;


    private Boolean enabled;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<List> lists;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<com.javahomeworkone.list.List> getLists() {
        return lists;
    }

    public void setLists(Set<com.javahomeworkone.list.List> lists) {
        this.lists = lists;
    }
}
