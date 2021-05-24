package io.robbinespu.ess.model;

import io.robbinespu.ess.util.CustomSeqGeneratorIdForUser;
import io.robbinespu.ess.util.DbAuditModels;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nodes")
public class Nodes extends DbAuditModels {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_node")
    @GenericGenerator(
            name = "SEQ_node",
            strategy = "io.robbinespu.ess.util.CustomSeqGeneratorIdForUser",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = CustomSeqGeneratorIdForUser.INCREMENT_PARAM, value = "50"),
                    @org.hibernate.annotations.Parameter(name = CustomSeqGeneratorIdForUser.VALUE_PREFIX_PARAMETER, value = "node_"),
                    @org.hibernate.annotations.Parameter(name = CustomSeqGeneratorIdForUser.NUMBER_FORMAT_PARAMETER, value = "%03d")})
    private String id;
    private int parent;
    private int child;
    private String parentName;
    private String childName;
}
