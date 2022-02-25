package com.dl.book.modules.bms.dto;

import com.dl.book.domain.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BmsPressParam extends PageQuery {

    private String code;

    private String name;
}
