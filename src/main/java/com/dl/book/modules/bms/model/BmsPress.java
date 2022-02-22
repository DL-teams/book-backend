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
 * 出版社
 * </p>
 *
 * @author zhangcm
 * @since 2022-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bms_press")
@ApiModel(value="BmsPress对象", description="出版社")
public class BmsPress implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "出版社编号")
    private String code;

    @ApiModelProperty(value = "出版社名称")
    private String name;

    @ApiModelProperty(value = "是否显示 0 否 1 是")
    private Integer showStatus;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "删除标识")
    private Boolean isDeleted;


}
