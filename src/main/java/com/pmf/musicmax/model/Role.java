package com.pmf.musicmax.model;


import org.hibernate.annotations.CollectionType;
import org.hibernate.annotations.Type;
import org.hibernate.type.ArrayType;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String hiddenTabs;


    public String  getHiddenTabs() {
        return hiddenTabs;
    }

    public void setHiddenTabs(String  hiddenTabs) {
        this.hiddenTabs = hiddenTabs;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
