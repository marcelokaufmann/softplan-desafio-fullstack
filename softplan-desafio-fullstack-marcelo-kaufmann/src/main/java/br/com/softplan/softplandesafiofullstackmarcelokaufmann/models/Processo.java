package br.com.softplan.softplandesafiofullstackmarcelokaufmann.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Modelo para informações de processos
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
@Table(name = "processo")
public class Processo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "descricao")
    private String descricaoProcesso;

    @Column(name = "parecer")
    private String parecerProcesso;

    @Column(name = "pendente")
    private String pendenteParecer;

    @OneToMany(mappedBy = "processo")
    private List<UsuarioProcesso> usuariosProcesso;

    public Processo(String descricaoProcesso, String parecerProcesso, String pendenteParecer) {
        this.descricaoProcesso = descricaoProcesso;
        this.parecerProcesso = parecerProcesso;
        this.pendenteParecer = pendenteParecer;
    }
}
