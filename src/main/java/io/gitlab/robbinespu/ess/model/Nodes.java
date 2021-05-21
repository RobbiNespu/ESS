package io.gitlab.robbinespu.ess.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nodes")
public class Nodes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_node")
    @SequenceGenerator(name = "SEQ_node", sequenceName = "SEQ_node", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private int parent;
    private int child;
    private String parentName;
    private String childName;
}
