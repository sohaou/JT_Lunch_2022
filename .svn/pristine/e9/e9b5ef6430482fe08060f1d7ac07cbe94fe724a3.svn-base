package egovframework.jtLunch.cmmn.security.DTO;

import java.util.ArrayList;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails {
    
	//db컬럼에 맞춰 시큐리티 vo작성
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_tel;
    private String user_auth;
    private String access_date;
    private String join_date;
    private boolean enabled;
    private String department;
    private String team;
    
	/**
	 * @param eNABLED the eNABLED to set
	 */

    
    
    
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(user_auth));
        return auth;
    }
 
    public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public String getUser_auth() {
		return user_auth;
	}

	public void setUser_auth(String user_auth) {
		this.user_auth = user_auth;
	}

	public String getAccess_date() {
		return access_date;
	}

	public void setAccess_date(String access_date) {
		this.access_date = access_date;
	}

	public String getJoin_date() {
		return join_date;
	}

	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
    public String getUsername() {
        return user_id;
    }
    
	@Override
	public String getPassword() {
		return user_pw;
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
	
	public void setPassword(String User_pw) {
		user_pw = User_pw;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "CustomUserDetails [user_id=" + user_id + ", user_pw=" + user_pw + ", user_name=" + user_name
				+ ", user_tel=" + user_tel + ", user_auth=" + user_auth + ", access_date=" + access_date
				+ ", join_date=" + join_date + ", enabled=" + enabled + ", department=" + department + ", team=" + team
				+ "]";
	}
	
	
}
