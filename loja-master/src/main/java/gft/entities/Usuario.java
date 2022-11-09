package gft.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @JsonIgnore
    private String senha;

    @ManyToOne
    private Perfil perfil;

    @ManyToOne
    private Filial filial;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.perfil);
    }

    @Override
    @JsonIgnore
    public String getPassword() {

        return getSenha();
    }

    @Override
    public String getUsername() {

        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {

        return "Usuario [id=" + id + ", email=" + email + ", perfil=" + perfil + "]";
    }

    public Filial getFilial() {

        return filial;
    }

    public void setFilial(Filial filial) {

        this.filial = filial;
    }

    public Usuario(Long id, String email, String senha, Perfil perfil, Filial filial) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        this.filial = filial;
    }

    public Usuario() {
    }


}
