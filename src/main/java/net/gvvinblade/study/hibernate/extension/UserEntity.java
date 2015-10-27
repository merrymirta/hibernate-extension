package net.gvvinblade.study.hibernate.extension;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Created by Igor_Seliverstov on 10/27/2015.
 *
 */
@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    @SequenceGenerator(name = "EMP_SEQ", allocationSize = 25)
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
