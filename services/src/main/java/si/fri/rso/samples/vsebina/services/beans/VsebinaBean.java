package si.fri.rso.samples.vsebina.services.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import org.eclipse.microprofile.metrics.annotation.Timed;
import si.fri.rso.samples.vsebina.lib.Artikel;
import si.fri.rso.samples.vsebina.lib.Kosarica;
import si.fri.rso.samples.vsebina.lib.Vsebina;
import si.fri.rso.samples.vsebina.lib.VsebinaArtikel;
import si.fri.rso.samples.vsebina.models.converters.VsebinaArtikelConverter;
import si.fri.rso.samples.vsebina.models.converters.VsebinaConverter;
import si.fri.rso.samples.vsebina.models.entities.VsebinaEntity;


@RequestScoped
public class VsebinaBean {

    private String ipArtikli = "20.126.195.188";
    private String portArtikli = "8080";

    private String ipKosarice = "20.71.20.248";
    private String portKosarice = "8080";


    private Logger log = Logger.getLogger(VsebinaBean.class.getName());

    @Inject
    private EntityManager em;

    public List<VsebinaArtikel> getArtikliForKosarica(Integer kosaricaId){
        TypedQuery<VsebinaEntity> query = em.createNamedQuery(
                "VsebinaEntity.getForKosaricaId", VsebinaEntity.class);
        query.setParameter("kosaricaId", kosaricaId);
        List<VsebinaEntity> resultList = query.getResultList();

        String artikliUrl = String.format("http://%s:%s/v1/artikli", ipArtikli, portArtikli);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(artikliUrl))
                .build();




        ObjectMapper objectMapper = new ObjectMapper();

        List<VsebinaArtikel> rezultat = null;
        System.out.println(artikliUrl);

        try {
            System.out.println("before response");
            CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.get());
            List<Artikel> artikli = Arrays.asList(objectMapper.readValue(response.get().body(), Artikel[].class));

            rezultat = new ArrayList<VsebinaArtikel>();
            for(int i = 0; i < resultList.size(); i++){
                VsebinaEntity ve = resultList.get(i);
                for(int j = 0; j < artikli.size(); j++){
                    Artikel a = artikli.get(j);
                    if(a.getArtikelId() == ve.getArtikelId()){
                        rezultat.add(VsebinaArtikelConverter.toDto(a,ve));
                    }
                }
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        return rezultat;
        //return resultList.stream().map(VsebinaConverter::toDto).collect(Collectors.toList());
    }

    public Kosarica postKosarica(Integer kosaricaId) {
        //String urlPath = "http://" + ipKosarice + ":" + portKosarice
        //URL url = new URL()
        return null;
    }

    public List<Vsebina> getVsebina() {

        TypedQuery<VsebinaEntity> query = em.createNamedQuery(
                "VsebinaEntity.getAll", VsebinaEntity.class);

        List<VsebinaEntity> resultList = query.getResultList();

        return resultList.stream().map(VsebinaConverter::toDto).collect(Collectors.toList());

    }

    @Timed
    public List<Vsebina> getVsebinaFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, VsebinaEntity.class, queryParameters).stream()
                .map(VsebinaConverter::toDto).collect(Collectors.toList());
    }

    public Vsebina getVsebina(Integer id) {

        VsebinaEntity vsebinaEntity = em.find(VsebinaEntity.class, id);

        if (vsebinaEntity == null) {
            throw new NotFoundException();
        }

        Vsebina vsebina = VsebinaConverter.toDto(vsebinaEntity);

        return vsebina;
    }

    public Vsebina createVsebina(Vsebina vsebina) {

        VsebinaEntity vsebinaEntity = VsebinaConverter.toEntity(vsebina);

        try {
            beginTx();
            em.persist(vsebinaEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (vsebinaEntity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return VsebinaConverter.toDto(vsebinaEntity);
    }

    public Vsebina putVsebina(Integer id, Vsebina vsebina) {

        VsebinaEntity c = em.find(VsebinaEntity.class, id);

        if (c == null) {
            return null;
        }

        VsebinaEntity updatedVsebinaEntity = VsebinaConverter.toEntity(vsebina);

        try {
            beginTx();
            updatedVsebinaEntity.setId(c.getId());
            updatedVsebinaEntity = em.merge(updatedVsebinaEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return VsebinaConverter.toDto(updatedVsebinaEntity);
    }

    public boolean deleteVsebina(Integer id) {

        VsebinaEntity Vsebina = em.find(VsebinaEntity.class, id);

        if (Vsebina != null) {
            try {
                beginTx();
                em.remove(Vsebina);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return false;
        }

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
