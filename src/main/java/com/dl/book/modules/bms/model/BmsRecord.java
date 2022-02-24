package com.dl.book.modules.bms.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 借阅还书记录表
 * </p>
 *
 * @author zhangcm
 * @since 2022-02-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_record")
@ApiModel(value="BmsRecord对象", description="借阅还书记录表")
public class BmsRecord implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    private Integer status;

    private Date startDate;

    private Date endDate;

    private Integer cardId;

    private Integer bookId;

    private Boolean isDeteled;
}
