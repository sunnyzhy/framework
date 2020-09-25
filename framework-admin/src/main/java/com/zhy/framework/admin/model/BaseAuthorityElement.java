package com.zhy.framework.admin.model;

import javax.persistence.*;

@Table(name = "base_authority_element")
public class BaseAuthorityElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 资源ID
     */
    @Column(name = "element_id")
    private Integer elementId;

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

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取资源ID
     *
     * @return element_id - 资源ID
     */
    public Integer getElementId() {
        return elementId;
    }

    /**
     * 设置资源ID
     *
     * @param elementId 资源ID
     */
    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }
}