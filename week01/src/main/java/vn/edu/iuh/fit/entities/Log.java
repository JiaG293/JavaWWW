package vn.edu.iuh.fit.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "log")
@NamedQueries({
        @NamedQuery(name = "Log.existsByNotes", query = "select (count(l) > 0) from Log l where l.notes = :notes"),
        @NamedQuery(name = "Log.findByNotes", query = "select l from Log l where l.notes = :notes")
})
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "account_id", nullable = false, length = 50)
    private String accountId;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "login_time", nullable = false)
    private Instant loginTime;

    @ColumnDefault("current_timestamp()")
    @Column(name = "logout_time", nullable = true)
    private Instant logoutTime;

    @Size(max = 250)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "notes", nullable = false, length = 250)
    private String notes;

    public Log(String username, String sessionId) {
    }

    public Log(String accountId, Instant loginTime, Instant logoutTime, String notes) {
        this.accountId = accountId;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.notes = notes;
    }

    public Log() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Instant getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Instant loginTime) {
        this.loginTime = loginTime;
    }

    public Instant getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Instant logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}