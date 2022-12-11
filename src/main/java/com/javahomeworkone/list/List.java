package com.javahomeworkone.list;

import com.javahomeworkone.category.Category;
import com.javahomeworkone.user.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "lists")
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 25, nullable = false, name = "title")
    private String title;

    @Column(length = 150, nullable = false, name = "text")
    private String text;

    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "List{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
