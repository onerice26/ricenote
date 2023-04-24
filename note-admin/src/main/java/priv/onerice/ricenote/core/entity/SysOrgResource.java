package priv.onerice.ricenote.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 组织资源表
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
@Getter
@Setter
@TableName("sys_org_resource")
public class SysOrgResource implements Serializable {

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
     * 资源ID
     */
    private String resourceId;
}
