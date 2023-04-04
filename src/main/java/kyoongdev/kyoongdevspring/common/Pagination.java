package kyoongdev.kyoongdevspring.common;

import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
public class Pagination {
    boolean hasNext;
    boolean hasPrev;
    Long totalCount;
    int page;
    int size;

    Pagination(Pageable pageable, Long totalCount) {
        this.hasNext = ((long) (pageable.getPageNumber() + 1) * pageable.getPageSize()) < totalCount;
        this.hasPrev = pageable.getPageNumber() > 1;
        this.totalCount = totalCount;
        this.page = pageable.getPageNumber();
        this.size = pageable.getPageSize();
    }
}
