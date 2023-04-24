package priv.onerice.ricenote.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统权限表
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
@Getter
@Setter
@TableName("sys_permission")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限代码
     */
    private String code;

    /**
     * 父类ID
     */
    private String parentId;

    /**
     * 创建人编号
     */
    private String creatorBy;

    /**
     * 创建人姓名
     */
    private String creatorName;

    /**
     * 创建日期 默认为当前时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改人编号
     */
    private String updatedBy;

    /**
     * 修改人姓名
     */
    private String updatedName;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime updatedTime;

    /**
     * 数据级别 选项：0=正常 1=审核中 2=被否决 -1=已删除 -2=草稿
     */
    private Byte dataLevel;
}
