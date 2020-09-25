package com.zhy.framework.admin.model;

import javax.persistence.*;

@Table(name = "base_authority_field")
public class BaseAuthorityField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }
}