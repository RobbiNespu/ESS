package io.gitlab.robbinespu.ess.model;

import io.gitlab.robbinespu.ess.util.CustomSeqGeneratorIdForUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ess_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
//    @GenericGenerator(name = "ess_generator", strategy = "io.gitlab.robbinespu.ess.util.CustomSeqGeneratorIdForUser",
//            parameters = {
//                    @Parameter(name = CustomSeqGeneratorIdForUser.INCREMENT_PARAM, value = "50"),
//                    @Parameter(name = CustomSeqGeneratorIdForUser.VALUE_PREFIX_PARAMETER, value = "B_"),
//                    @Parameter(name = CustomSeqGeneratorIdForUser.NUMBER_FORMAT_PARAMETER, value = "%05d") })
//    @Column(name = "id", updatable = false, nullable = false)
    @GenericGenerator(
            name = "book_seq",
            strategy =
                    "io.gitlab.robbinespu.ess.util.CustomSeqGeneratorIdForUser",
            parameters = {
                    @Parameter(name =
                            CustomSeqGeneratorIdForUser.INCREMENT_PARAM, value = "50"),
                    @Parameter(name =
                            CustomSeqGeneratorIdForUser.VALUE_PREFIX_PARAMETER, value = "B_"),
                    @Parameter(name =
                            CustomSeqGeneratorIdForUser.NUMBER_FORMAT_PARAMETER, value = "%05d")})
    private String id;

    @NotEmpty(message = "name is required")
    @NotNull(message = "cannot be null")
    @NotBlank(message = "cannot be blank")
    private String name;
    private String department;
    @Column(nullable = true, name = "email")
    private String email;
    private String username;
    private String password;

    private Long rolesID;
}
