package si.fri.rso.samples.vsebina.models.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "vsebina")
@NamedQueries(value =
        {
                @NamedQuery(name = "VsebinaEntity.getAll",
                        query = "SELECT vs FROM VsebinaEntity vs"),
                @NamedQuery(name = "VsebinaEntity.getForKosaricaId",
                        query = "SELECT vs FROM VsebinaEntity vs WHERE vs.kosaricaId = :kosaricaId")
        })
public class VsebinaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "kosaricaId")
    private Integer kosaricaId;

    @Column(name = "artikelId")
    private Integer artikelId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKosaricaId() {
        return kosaricaId;
    }

    public void setKosaricaId(Integer kosaricaId) {
        this.kosaricaId = kosaricaId;
    }

    public Integer getArtikelId() {
        return artikelId;
    }

    public void setArtikelId(Integer artikelId) {
        this.artikelId = artikelId;
    }
}