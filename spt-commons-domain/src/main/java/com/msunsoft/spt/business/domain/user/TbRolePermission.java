package com.msunsoft.spt.business.domain.user;

import javax.persistence.*;

@Table(name = "user.tb_role_permission")
public class TbRolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色 ID
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 权限 ID
     */
    @Column(name = "permission_id")
    private Long permissionId;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取角色 ID
     *
     * @return role_id - 角色 ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色 ID
     *
     * @param roleId 角色 ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取权限 ID
     *
     * @return permission_id - 权限 ID
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * 设置权限 ID
     *
     * @param permissionId 权限 ID
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}