package com.dl.book.modules.bms.dto;

import com.dl.book.domain.PageQuery;
import lombok.Data;

@Data
public class BmsCategoryParam extends PageQuery {

    private String code;

    private String name;
}
