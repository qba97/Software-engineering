package model;

import javax.persistence.*;

@Entity
@Table(name = "[pizzeria].[dbo].[user]")
public class User extends AbstractModel {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String answer;

    @Column(nullable = false)
    private String nick;

    @OneToOne
    private Address address;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    private Question question;

    @Column(nullable = false)
    private long createTimestamp;

    @Column(nullable = false)
    private long updateTimestamp;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean loggedIn;

    @Column(nullable = false)
    private boolean admin;

    public User() {
    }

    public User(String nick, Address address, String password, long createTimestamp, long updateTimestamp, boolean active, boolean loggedIn, boolean admin) {
        this.nick = nick;
        this.address = address;
        this.password = password;
        this.createTimestamp = createTimestamp;
        this.updateTimestamp = updateTimestamp;
        this.active = active;
        this.loggedIn = loggedIn;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(long create_timestamp) {
        this.createTimestamp = create_timestamp;
    }

    public long getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(long update_timestamp) {
        this.updateTimestamp = update_timestamp;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}