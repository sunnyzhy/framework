package com.zhy.framework.admin.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "base_user")
public class BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名，全局唯一
     */
    private String username;

    private String password;

    private String name;

    private String birthday;

    private String address;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "tel_phone")
    private String telPhone;

    private String email;

    /**
     * 性别
1: 男
2: 女
     */
    private Boolean sex;

    /**
     * 类型
1: 超级管理员（全局唯一） 
2: 租户管理员（每个租户只有一个） 
3: 普通用户
     */
    private Boolean type;

    /**
     * 状态
0: 正常
1: 禁用(因操作失误而引起的系统自我保护)
2: 停用(离职)
     */
    private Boolean status;

    /**
     * 职务
     */
    @Column(name = "duty_type_id")
    private Integer dutyTypeId;

    private String description;

    @Column(name = "crt_time")
    private Date crtTime;

    @Column(name = "crt_user")
    private String crtUser;

    @Column(name = "crt_name")
    private String crtName;

    @Column(name = "crt_host")
    private String crtHost;

    @Column(name = "upd_time")
    private Date updTime;

    @Column(name = "upd_user")
    private String updUser;

    @Column(name = "upd_name")
    private String updName;

    @Column(name = "upd_host")
    private String updHost;

    @Column(name = "tenant_id")
    private String tenantId;

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
     * 获取用户名，全局唯一
     *
     * @return username - 用户名，全局唯一
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名，全局唯一
     *
     * @param username 用户名，全局唯一
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return mobile_phone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @param mobilePhone
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * @return tel_phone
     */
    public String getTelPhone() {
        return telPhone;
    }

    /**
     * @param telPhone
     */
    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取性别
1: 男
2: 女
     *
     * @return sex - 性别
1: 男
2: 女
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * 设置性别
1: 男
2: 女
     *
     * @param sex 性别
1: 男
2: 女
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    /**
     * 获取类型
1: 超级管理员（全局唯一） 
2: 租户管理员（每个租户只有一个） 
3: 普通用户
     *
     * @return type - 类型
1: 超级管理员（全局唯一） 
2: 租户管理员（每个租户只有一个） 
3: 普通用户
     */
    public Boolean getType() {
        return type;
    }

    /**
     * 设置类型
1: 超级管理员（全局唯一） 
2: 租户管理员（每个租户只有一个） 
3: 普通用户
     *
     * @param type 类型
1: 超级管理员（全局唯一） 
2: 租户管理员（每个租户只有一个） 
3: 普通用户
     */
    public void setType(Boolean type) {
        this.type = type;
    }

    /**
     * 获取状态
0: 正常
1: 禁用(因操作失误而引起的系统自我保护)
2: 停用(离职)
     *
     * @return status - 状态
0: 正常
1: 禁用(因操作失误而引起的系统自我保护)
2: 停用(离职)
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置状态
0: 正常
1: 禁用(因操作失误而引起的系统自我保护)
2: 停用(离职)
     *
     * @param status 状态
0: 正常
1: 禁用(因操作失误而引起的系统自我保护)
2: 停用(离职)
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 获取职务
     *
     * @return duty_type_id - 职务
     */
    public Integer getDutyTypeId() {
        return dutyTypeId;
    }

    /**
     * 设置职务
     *
     * @param dutyTypeId 职务
     */
    public void setDutyTypeId(Integer dutyTypeId) {
        this.dutyTypeId = dutyTypeId;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return crt_time
     */
    public Date getCrtTime() {
        return crtTime;
    }

    /**
     * @param crtTime
     */
    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * @return crt_user
     */
    public String getCrtUser() {
        return crtUser;
    }

    /**
     * @param crtUser
     */
    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    /**
     * @return crt_name
     */
    public String getCrtName() {
        return crtName;
    }

    /**
     * @param crtName
     */
    public void setCrtName(String crtName) {
        this.crtName = crtName;
    }

    /**
     * @return crt_host
     */
    public String getCrtHost() {
        return crtHost;
    }

    /**
     * @param crtHost
     */
    public void setCrtHost(String crtHost) {
        this.crtHost = crtHost;
    }

    /**
     * @return upd_time
     */
    public Date getUpdTime() {
        return updTime;
    }

    /**
     * @param updTime
     */
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    /**
     * @return upd_user
     */
    public String getUpdUser() {
        return updUser;
    }

    /**
     * @param updUser
     */
    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }

    /**
     * @return upd_name
     */
    public String getUpdName() {
        return updName;
    }

    /**
     * @param updName
     */
    public void setUpdName(String updName) {
        this.updName = updName;
    }

    /**
     * @return upd_host
     */
    public String getUpdHost() {
        return updHost;
    }

    /**
     * @param updHost
     */
    public void setUpdHost(String updHost) {
        this.updHost = updHost;
    }

    /**
     * @return tenant_id
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * @param tenantId
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}