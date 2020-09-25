package com.zhy.framework.admin.model;

import javax.persistence.*;

@Table(name = "base_authority_menu")
public class BaseAuthorityMenu {
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
    @Column(name = "menu_id")
    private Integer menuId;

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
     * @return menu_id - 资源ID
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * 设置资源ID
     *
     * @param menuId 资源ID
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}