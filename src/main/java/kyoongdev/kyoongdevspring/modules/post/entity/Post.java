package kyoongdev.kyoongdevspring.modules.post.entity;


import jakarta.persistence.*;
import kyoongdev.kyoongdevspring.modules.user.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
@Table
@NoArgsConstructor
@SuperBuilder
@DynamicUpdate
public class Post {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;


    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(optional = true)
    @JoinColumn(name = "USER_ID")
    private User user;
}
