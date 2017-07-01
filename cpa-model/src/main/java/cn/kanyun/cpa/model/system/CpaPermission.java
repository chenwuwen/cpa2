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
    private String permissionCode;
    private String permissionDescription;
    private Set RolePermissions = new HashSet<>();

    public CpaPermission(){}

    public CpaPermission(int id, String permissionCode,String permissionDescription, Set rolepermissions) {
        this.id = id;
        this.permissionCode = permissionCode;
        this.permissionDescription=permissionDescription;
        RolePermissions = rolepermissions;
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
    @Column(name = "permission_code", nullable = true, length = 255)
    public String getPermissionCode() {
        return permissionCode;
    }
    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    @Basic
    @Column(name = "permission_description", nullable = true,length = 255)
    public String getPermissionDescription() {
        return permissionDescription;
    }
    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    public Set getRolePermissions() {
        return RolePermissions;
    }
    public void setRolepermissions(Set rolepermissions) {
        RolePermissions = rolepermissions;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CpaPermission that = (CpaPermission) o;

        if (!permissionCode.equals(that.permissionCode)) return false;
        if (!permissionDescription.equals(that.permissionDescription)) return false;
        return RolePermissions.equals(that.RolePermissions);
    }

    @Override
    public int hashCode() {
        int result = permissionCode.hashCode();
        result = 31 * result + permissionDescription.hashCode();
        result = 31 * result + RolePermissions.hashCode();
        return result;
    }


}
