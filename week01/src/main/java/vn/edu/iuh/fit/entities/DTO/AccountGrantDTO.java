package vn.edu.iuh.fit.entities.DTO;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.Role;

public class AccountGrantDTO {
    private Account account;
    private Role role; // Thay đổi từ String thành Role
    private Boolean isGrant;

    public AccountGrantDTO(Account account, Role role, Boolean isGrant) {
        this.account = account;
        this.role = role; // Thay đổi từ roleId thành role
        this.isGrant = isGrant;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getGrant() {
        return isGrant;
    }

    public void setGrant(Boolean grant) {
        isGrant = grant;
    }

    public AccountGrantDTO() {
    }
}
