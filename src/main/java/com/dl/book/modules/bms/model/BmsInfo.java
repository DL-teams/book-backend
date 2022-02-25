package com.dl.book.modules.bms.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 图书信息表
 * </p>
 *
 * @author zhangcm
 * @since 2022-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_info")
@ApiModel(value="BmsInfo对象", description="图书信息表")
public class BmsInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "图书编号")
    private String code;

    @ApiModelProperty(value = "条形码")
    private String barCode;

    @ApiModelProperty(value = "图书名称")
    private String name;

    @ApiModelProperty(value = "图书作者")
    private String author;

    @ApiModelProperty(value = "图书价格")
    private BigDecimal price;

    @ApiModelProperty(value = "总册数")
    private Integer number;

    @ApiModelProperty(value = "所属书架")
    private String bookcase;

    @ApiModelProperty(value = "出版日期")
    private Date publicationDate;

    @ApiModelProperty(value = "备注")
    private String comment;

    private Boolean isDeteled;


}
