package br.com.softplan.softplandesafiofullstackmarcelokaufmann.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Modelo para vinculo de usuários com processos
 *
 * @author Marcelo Augusto Kaufmann
 * @since   31/01/2021
 * @version 1.0
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario_processo")
public class UsuarioProcesso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UsuarioProcesso id;

    @ManyToOne
    @MapsId("usuario_id")
    @JoinColumn(name = "usuario_id", referencedColumnName = "login")
    private Usuario usuario;

    @ManyToOne
    @MapsId("processo_id")
    @JoinColumn(name = "processo_id", referencedColumnName = "id")
    private Processo processo;

    @Column(name = "data")
    private Date data;

    public UsuarioProcesso(Usuario usuario, Processo processo, Date data) {
        this.usuario = usuario;
        this.processo = processo;
        this.data = data;
    }
}
