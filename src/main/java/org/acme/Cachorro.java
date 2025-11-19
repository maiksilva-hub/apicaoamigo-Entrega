package org.acme;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
public class Cachorro extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    public Long id;

    @NotBlank(message = "O nome do cachorro não pode ser vazio")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    public String nome;

    @Past(message = "A data de nascimento deve ser no passado")
    public LocalDate dataDeNascimento;

    @NotBlank(message = "O local de resgate é obrigatório")
    @Size(max = 80)
    public String localDeResgate;

    // Agora LAZY + JsonIgnore (corrige o loop e o erro 500)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_cachorro_id")
    @JsonIgnore
    public FichaCachorro ficha;

    @OneToMany(mappedBy = "cachorro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    public List<Adocao> adocoes = new ArrayList<>();

    public Cachorro() {}

    public Cachorro(Long id, String nome, LocalDate dataDeNascimento, String localDeResgate, FichaCachorro ficha) {
        this.id = id;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.localDeResgate = localDeResgate;
        this.ficha = ficha;
    }
}
