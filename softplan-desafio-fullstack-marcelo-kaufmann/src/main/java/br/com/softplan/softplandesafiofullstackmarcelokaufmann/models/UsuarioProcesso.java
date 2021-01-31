package br.com.softplan.softplandesafiofullstackmarcelokaufmann.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_usuario_processo")
public class UsuarioProcesso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UsuarioProcesso id;

    @ManyToOne
    @MapsId("usuario_id")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @MapsId("processo_id")
    @JoinColumn(name = "processo_id")
    private Processo processo;

    @Column(name = "data")
    private Date data;

    public UsuarioProcesso(Usuario usuario, Processo processo, Date data) {
        this.usuario = usuario;
        this.processo = processo;
        this.data = data;
    }
}
