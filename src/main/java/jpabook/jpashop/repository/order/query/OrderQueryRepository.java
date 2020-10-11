package jpabook.jpashop.repository.order.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager entityManager;

    public List<OrderQueryDto> findOrderQueryDto() {
        List<OrderQueryDto> orders = findOrders(); // query 1번 -> 결과 N개

        orders.forEach(o -> {
            List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId()); // query N 번
            o.setOrderItems(orderItems);
        });

        return orders;
    }

    private List<OrderItemQueryDto> findOrderItems(Long orderId) {
        return entityManager.createQuery("select new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, oi.item.name, oi.item.price, oi.count) " +
                "From OrderItem oi " +
                "join oi.item i " +
                "where oi.order.id = :orderId", OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    private List<OrderQueryDto> findOrders() {
        return entityManager.createQuery("select new jpabook.jpashop.repository.order.query.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " +
                "From Order o " +
                "join o.member m " +
                "join o.delivery d", OrderQueryDto.class)
                .getResultList();
    }
}
