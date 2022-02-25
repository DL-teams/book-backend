package com.dl.book.modules.bms.request;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;


/**
 * BmsBookAddRequest.
 * @author jiangmh
 */
@Data
public class BmsBookUpdateRequest {

    @NotBlank
    private String code;

    @NotBlank
    private String barCode;

    @NotBlank
    private String name;

    @NotBlank
    private String author;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer number;

    @NotBlank
    private String bookcase;

    @NotNull
    private Date publicationDate;

    private String comment;

    @NotNull
    private Integer categoryId;

    @NotNull
    private Integer pressId;

}
