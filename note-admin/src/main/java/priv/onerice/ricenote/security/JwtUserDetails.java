package priv.onerice.ricenote.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import priv.onerice.ricenote.base.RiceConst;
import priv.onerice.ricenote.core.entity.SysUser;
import priv.onerice.ricenote.utils.ObjectUtil;

import java.util.Collection;

/**
 * @author onerice
 * @date 2023/4/22
 * @apiNote
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtUserDetails implements UserDetails, CredentialsContainer {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    private Collection<? extends GrantedAuthority> authorities;
    private SysUser user;

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !ObjectUtil.equal(user.getDataLevel(), RiceConst.USER_TYPE_LOCK);
    }

    /**
     * 认证完成后的操作
     */
    @Override
    public void eraseCredentials() {
        user.setPassword(null);
    }
}
