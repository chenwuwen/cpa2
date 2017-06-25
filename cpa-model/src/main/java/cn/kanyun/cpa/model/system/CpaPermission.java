package cn.kanyun.cpa.model.system;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/15.
 */
@Entity
@Table(name = "cpa_permission", schema = "cpa", catalog = "")
public class CpaPermission implements java.io.Serializable {
    private int id;
    private String permission;


    private Set RolePermissions = new HashSet<>();

    public CpaPermission(){};

    public CpaPermission(int id, String permission, Set rolePermissions) {
        this.id = id;
        this.permission = permission;
        RolePermissions = rolePermissions;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "permission", nullable = true, length = 255)
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Set getRolePermissions() {
        return RolePermissions;
    }

    public void setRolePermissions(Set rolePermissions) {
        RolePermissions = rolePermissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CpaPermission that = (CpaPermission) o;

        if (id != that.id) return false;
        if (permission != null ? !permission.equals(that.permission) : that.permission != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        return result;
    }
}
