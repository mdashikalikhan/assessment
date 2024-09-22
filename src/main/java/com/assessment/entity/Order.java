package com.assessment.entity;

import com.assessment.entity.converter.BooleanToIntegerConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="order_details")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @Column(name="order_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name="order_amount", nullable = false)
    @NonNull
    @Min(value = 1,message = "Minimum amount is 1")
    private BigDecimal orderAmount;

    @Column(name="order_details", nullable = false)
    @NonNull
    @NotEmpty(message = "Order details must required")
    private String orderDetails;

    @Column(name="order_date", nullable = false)
    @NonNull
    @NotNull(message = "Order date must required")
    private LocalDate orderDate;

    @Column(name="order_active", nullable = false)
    @Convert(converter = BooleanToIntegerConverter.class)
    @NonNull
    private boolean orderActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @NonNull
    @JsonIgnore
    @ToString.Exclude
    private Customer customer;

    /*@Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderAmount=" + orderAmount +
                ", orderDetails='" + orderDetails + '\'' +
                ", orderDate=" + orderDate +
                ", orderActive=" + orderActive +
                '}';
    }*/
}
