package com.dl.book.modules.bms.response;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * BmsBookDetailResponse.
 * @author jiangmh
 */
@Data
public class BmsBookDetailResponse {

    private String code;

    private String barCode;

    private String name;

    private String author;

    private BigDecimal price;

    private Integer number;

    private String bookcase;

    private Date publicationDate;

    private String comment;

    private String categoryName;

    private String pressName;
}
