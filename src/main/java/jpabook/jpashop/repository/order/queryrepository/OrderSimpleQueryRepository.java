package jpabook.jpashop.repository.order.queryrepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager entityManager;

    public List<OrderSimpleQueryDto> findOrderDTOs() {
        return entityManager.createQuery("select new jpabook.jpashop.repository.order.queryrepository.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " +
                        "From Order o join o.member m join o.delivery d",
                OrderSimpleQueryDto.class)
                .getResultList();
    }
}
