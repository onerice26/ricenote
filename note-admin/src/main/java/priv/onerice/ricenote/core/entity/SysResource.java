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
 * 系统资源表
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
@Getter
@Setter
@TableName("sys_resource")
public class SysResource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源类型 0菜单目录，1菜单资源，2外链
     */
    private String type;

    /**
     * 路由地址
     */
    private String router;

    /**
     * 父类ID
     */
    private Long parentId;

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
