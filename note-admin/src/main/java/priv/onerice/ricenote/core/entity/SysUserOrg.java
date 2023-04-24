package priv.onerice.ricenote.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户组织表
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
@Getter
@Setter
@TableName("sys_user_org")
public class SysUserOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 组织ID
     */
    private String orgId;
}
