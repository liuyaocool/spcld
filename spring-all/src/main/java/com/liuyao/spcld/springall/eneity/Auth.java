package com.liuyao.spcld.springall.eneity;

public class Auth {

    private Integer roleId;
    private String useeId;
    private String roleName;

    public Auth() {
        System.out.println("auth created");
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUseeId() {
        return useeId;
    }

    public void setUseeId(String useeId) {
        this.useeId = useeId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "roleId=" + roleId +
                ", useeId='" + useeId + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
