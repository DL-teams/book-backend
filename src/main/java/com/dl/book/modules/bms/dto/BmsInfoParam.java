package com.dl.book.modules.bms.dto;

import com.dl.book.domain.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jiangfendou
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BmsInfoParam extends PageQuery {

    private String name;

    private String author;

    private Integer pressId;

    private Integer categoryId;

}
