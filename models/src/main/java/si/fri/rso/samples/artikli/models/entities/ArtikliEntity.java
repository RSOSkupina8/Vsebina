package si.fri.rso.samples.artikli.models.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "artikli")
@NamedQueries(value =
        {
                @NamedQuery(name = "ArtikliEntity.getAll",
                        query = "SELECT im FROM ArtikliEntity im")
        })
public class ArtikliEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "product")
    private String product;

    @Column(name = "price")
    private Float price;

    @Column(name = "gross_weight")
    private Float gross_weight;

    @Column(name = "created")
    private Instant created;

    @Column(name = "uri")
    private String uri;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getGross_weight() {
        return gross_weight;
    }

    public void setGross_weight(Float gross_weight) {
        this.gross_weight = gross_weight;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}