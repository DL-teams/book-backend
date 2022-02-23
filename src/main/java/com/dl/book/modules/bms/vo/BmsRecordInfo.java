package com.dl.book.modules.bms.vo;

import com.dl.book.modules.bms.model.BmsRecord;
import lombok.Data;

import java.io.Serializable;

@Data
public class BmsRecordInfo extends BmsRecord implements Serializable {

    private String bookname;

    private String username;

    private String number;
}
