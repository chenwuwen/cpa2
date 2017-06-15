package cn.kanyun.cpa.model;

/**
 * Created by Administrator on 2017/6/15.
 */
public class CpaRole {
    private int id;
    private String roleName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CpaRole cpaRole = (CpaRole) o;

        if (id != cpaRole.id) return false;
        if (roleName != null ? !roleName.equals(cpaRole.roleName) : cpaRole.roleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }
}