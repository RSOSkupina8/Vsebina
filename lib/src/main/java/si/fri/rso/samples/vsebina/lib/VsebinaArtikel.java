package si.fri.rso.samples.vsebina.lib;

public class VsebinaArtikel {
    private Integer artikelId;
    private Integer vsebinaId;
    private Integer kosaricaId;
    private String name;
    private String store;
    private Float price;



    public Integer getVsebinaId(){
        return vsebinaId;
    }

    public void setVsebinaId(Integer vsebinaId){
        this.vsebinaId = vsebinaId;
    }

    public Integer getKosaricaId(){
        return kosaricaId;
    }

    public void setKosaricaId(Integer kosaricaId){
        this.kosaricaId = kosaricaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getArtikelId() {
        return artikelId;
    }

    public void setArtikelId(Integer artikelId) {
        this.artikelId = artikelId;
    }
}
