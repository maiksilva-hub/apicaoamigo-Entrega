package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
public class FichaCachorro extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    public Long id;

    @Size(max = 2000, message = "A descrição da história não pode ultrapassar 2000 caracteres")
    @Column(length = 2000)
    public String descricaoHistoria;

    @Size(max = 200, message = "O temperamento principal não pode ultrapassar 200 caracteres")
    public String temperamentoPrincipal;

    public String habilidadesEspeciais;

    public FichaCachorro() {}

    public FichaCachorro(String descricaoHistoria, String temperamentoPrincipal, String habilidadesEspeciais) {
        this.descricaoHistoria = descricaoHistoria;
        this.temperamentoPrincipal = temperamentoPrincipal;
        this.habilidadesEspeciais = habilidadesEspeciais;
    }
}
