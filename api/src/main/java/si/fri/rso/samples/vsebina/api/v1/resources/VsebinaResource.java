package si.fri.rso.samples.vsebina.api.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.rso.samples.vsebina.lib.Artikel;
import si.fri.rso.samples.vsebina.lib.Vsebina;
import si.fri.rso.samples.vsebina.services.beans.VsebinaBean;
import si.fri.rso.samples.vsebina.services.clients.AmazonRekognitionClient;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;


@Log
@ApplicationScoped
@Path("/vsebina")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VsebinaResource {

    private Logger log = Logger.getLogger(VsebinaResource.class.getName());

    @Inject
    private VsebinaBean vsebinaBean;


    @Context
    protected UriInfo uriInfo;

    @Inject
    private AmazonRekognitionClient amazonRekognitionClient;

    @Operation(description = "Get all vsebina vsebina.", summary = "Get all vsebina")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "List of image vsebina",
                    content = @Content(schema = @Schema(implementation = Vsebina.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}
            )})
    @GET
    public Response getVsebina() {

        List<Vsebina> vsebina = vsebinaBean.getVsebinaFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(vsebina).build();
    }

    @Operation(description = "Get vsebina for idKosarica.", summary = "Gets all rows for kosaricaId")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Get rows for kosaricaId",
                    content = @Content(schema = @Schema(implementation = Vsebina.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}
            )})
    @GET
    @Path("/kosarica/{kosaricaId}")
    public Response getVsebinaForKosarica(@Parameter(description = "kosarica ID.", required = true)
                                              @PathParam("kosaricaId") Integer kosaricaId){
        List<Artikel> vsebina = vsebinaBean.getArtikliForKosarica(kosaricaId);

        if (vsebina == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(vsebina).build();

    }


    @Operation(description = "Get vsebina for vsebina.", summary = "Get vsebina for vsebina")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Image vsebina",
                    content = @Content(
                            schema = @Schema(implementation = Vsebina.class))
            )})
    @GET
    @Path("/{vsebinaId}")
    public Response getVsebina(@Parameter(description = "vsebina ID.", required = true)
                                     @PathParam("vsebinaId") Integer vsebinaId) {

        Vsebina vsebina = vsebinaBean.getVsebina(vsebinaId);

        if (vsebina == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(vsebina).build();
    }

    @Operation(description = "Add vsebina vsebina.", summary = "Add vsebina")
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "vsebina successfully added."
            ),
            @APIResponse(responseCode = "405", description = "Validation error .")
    })
    @POST
    public Response createVsebina(@RequestBody(
            description = "DTO object with vsebina vsebina.",
            required = true, content = @Content(
            schema = @Schema(implementation = Vsebina.class))) Vsebina vsebina) {

        if (vsebina.getKosaricaId() == null || vsebina.getArtikelId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            vsebina = vsebinaBean.createVsebina(vsebina);
        }

        return Response.status(Response.Status.CREATED).entity(vsebina).build();

    }


    @Operation(description = "Update data for an item.", summary = "Update vsebina")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "vsebina successfully updated."
            )
    })
    @PUT
    @Path("{vsebinaId}")
    public Response putVsebina(@Parameter(description = "Vsebina ID.", required = true)
                                     @PathParam("vsebinaId") Integer vsebinaId,
                               @RequestBody(
                                             description = "DTO object with vsebina vsebina.",
                                             required = true, content = @Content(
                                             schema = @Schema(implementation = Vsebina.class)))
                               Vsebina vsebina){

        vsebina = vsebinaBean.putVsebina(vsebinaId, vsebina);

        if (vsebina == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.NOT_MODIFIED).build();

    }

    @Operation(description = "Delete vsebina for vsebina.", summary = "Delete vsebina")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "vsebina successfully deleted."
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Not found."
            )
    })
    @DELETE
    @Path("{vsebinaId}")
    public Response deleteVsebina(@Parameter(description = "vsebina ID.", required = true)
                                      @PathParam("vsebinaId") Integer vsebinaId){

        boolean deleted = vsebinaBean.deleteVsebina(vsebinaId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
