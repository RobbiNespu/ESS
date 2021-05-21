package io.gitlab.robbinespu.ess.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "slots")
public class Slots {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_slot")
    @SequenceGenerator(name = "SEQ_slot", sequenceName = "SEQ_slot", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String name;
    private int classId;
    private int subjectId;
    private boolean booked;
    private boolean active;
    private Date date;

}
