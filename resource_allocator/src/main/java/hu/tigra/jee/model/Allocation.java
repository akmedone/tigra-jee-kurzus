package hu.tigra.jee.model;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Ádám on 2016.10.05..
 */

@Entity
@XmlRootElement
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "Start"))
public class Allocation extends EqualsById implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    private String subject;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String start;

    @NotNull
    @NotEmpty

    private String beginTime;

    @NotNull
    @NotEmpty
    private String endTime;

    @NotNull
    @NotEmpty
    private String stop;

    @NotNull
    @NotEmpty
    private String room;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }
}
