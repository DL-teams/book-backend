package com.dl.book.modules.bms.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 图书出版社关系映射表
 * </p>
 *
 * @author zhangcm
 * @since 2022-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_press_mapping")
@ApiModel(value="BmsPressMapping对象", description="图书出版社关系映射表")
public class BmsPressMapping implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "图书id")
    private Integer bookId;

    @ApiModelProperty(value = "出版社id")
    private Integer pressId;


}
