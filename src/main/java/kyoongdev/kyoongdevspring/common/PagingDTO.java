package kyoongdev.kyoongdevspring.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Data
@NoArgsConstructor
@SuperBuilder
public class PagingDTO<T> {

    public List<T> data;

    public Pagination paging;


    public static Pagination getPagination(Pageable pageable, Long totalCount) {
        return new Pagination(pageable, totalCount);
    }
}
