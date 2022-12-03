package si.fri.rso.samples.artikli.lib;

import java.time.Instant;

public class Artikli {

    private Integer artikelId;
    private String name;
    private String product;
    private Float price;
    private Float gross_weight;
    private Instant created;
    private String uri;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getArtikelId() {
        return artikelId;
    }

    public void setArtikelId(Integer artikelId) {
        this.artikelId = artikelId;
    }

}
