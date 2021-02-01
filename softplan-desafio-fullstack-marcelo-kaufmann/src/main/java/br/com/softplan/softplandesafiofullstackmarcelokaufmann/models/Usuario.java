package br.com.softplan.softplandesafiofullstackmarcelokaufmann.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nome")
    private String nomeUsuario;

    @Column(name = "login")
    private String loginUsuario;

    @Column(name = "senha")
    private String senhaUsuario;

    @Column(name = "tipo")
    private String tipoUsuario;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioProcesso> usuarioProcessos;

    public Usuario(String nomeUsuario, String loginUsuario, String senhaUsuario, String tipoUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.loginUsuario = loginUsuario;
        this.senhaUsuario = senhaUsuario;
        this.tipoUsuario = tipoUsuario;
    }
}
