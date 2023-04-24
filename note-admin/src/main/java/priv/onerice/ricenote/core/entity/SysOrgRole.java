package priv.onerice.ricenote.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统组织角色表
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
@Getter
@Setter
@TableName("sys_org_role")
public class SysOrgRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 组织ID
     */
    private String orgId;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 父类ID
     */
    private String parentId;

    /**
     * 备注
     */
    private String remark;

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
