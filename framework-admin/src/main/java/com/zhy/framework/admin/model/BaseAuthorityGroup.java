package com.zhy.framework.admin.model;

import javax.persistence.*;

@Table(name = "base_authority_group")
public class BaseAuthorityGroup {
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
    @Column(name = "group_id")
    private Integer groupId;

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
     * @return group_id - 资源ID
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 设置资源ID
     *
     * @param groupId 资源ID
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}