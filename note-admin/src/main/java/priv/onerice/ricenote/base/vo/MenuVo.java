package priv.onerice.ricenote.base.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class MenuVo implements Serializable {
    /**
     * 编号
     */
    private String id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 资源类型 0菜单目录，1菜单资源，2外链
     */
    private String type;

    /**
     * 路由地址
     */
    private String router;

    /**
     * 子菜单
     */
    private List<MenuVo> children;
}
