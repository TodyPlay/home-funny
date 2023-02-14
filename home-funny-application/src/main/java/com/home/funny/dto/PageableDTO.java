package com.home.funny.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

/**
 * 可分页的参数
 */
@Getter
@Setter
public class PageableDTO {
    private int page;
    private int size;
    private List<OrderDTO> orders;

    public PageRequest getPageRequest() {
        return PageRequest.of(page, size, Sort.by(orders == null ? Collections.emptyList() : orders.stream().map(v -> new Sort.Order(v.getDirection(), v.getProperty())).toList()));
    }

    @Getter
    @Setter
    private static class OrderDTO {
        String property;
        Sort.Direction direction;
    }
}
