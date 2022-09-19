/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.application.repositories;

import com.ionutgradinaru.xlsx.domain_model.BookingTransaction;
import com.ionutgradinaru.xlsx.infrastructure.api.dto.BookingTransactionFilterDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BookingTransactionSpecification {

  private BookingTransactionSpecification() {
    // nothing on purpose; to ensure that nobody creates an instance of it
  }

  public static Specification<BookingTransaction> getTransactions(final BookingTransactionFilterDto filter) {
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      Optional.ofNullable(filter.getTeam())
          .ifPresent(value -> predicates.add(criteriaBuilder.equal(root.get("team"), value)));

      Optional.ofNullable(filter.getProduct())
          .ifPresent(value -> predicates.add(criteriaBuilder.equal(root.get("product"), value)));

      Optional.ofNullable(filter.getBookingType())
          .ifPresent(value -> predicates.add(criteriaBuilder.equal(root.get("bookingType"), value)));

      if (Objects.nonNull(filter.getStartDate()) && Objects.nonNull(filter.getEndDate())) {
        predicates.add(criteriaBuilder.between(root.get("bookingDate"), filter.getStartDate(), filter.getEndDate()));
      } else if (Objects.nonNull(filter.getStartDate())) {
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("bookingDate"), filter.getStartDate()));
      } else if (Objects.nonNull(filter.getEndDate())) {
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("bookingDate"), filter.getEndDate()));
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
