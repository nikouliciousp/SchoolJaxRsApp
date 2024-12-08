package gr.perisnik.cj.schoolapprest.rest;

import gr.perisnik.cj.schoolapprest.dto.TeacherDTO;
import gr.perisnik.cj.schoolapprest.service.ITeacherService;
import gr.perisnik.cj.schoolapprest.service.exceptions.TeacherServiceException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

@Path("/teachers")
public class TeacherRestController {

    @Inject
    private ITeacherService teacherService;

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTeacher(MultivaluedMap<String, String> form, @Context UriInfo uriInfo) {
        try {

            // Create the TeacherDTO object
            TeacherDTO teacherDTO = new TeacherDTO(
                    0,
                    form.getFirst("last_name"),
                    form.getFirst("first_name"),
                    form.getFirst("email"),
                    form.getFirst("day_of_birth"),
                    form.getFirst("phone"),
                    0
            );

            // Add the teacher using the service
            teacherDTO = teacherService.addTeacher(teacherDTO);

            if (teacherDTO != null) {
                // Return 200 OK with the created TeacherDTO
                UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
                return Response.created(uriBuilder.path(String.valueOf(teacherDTO.getId())).build())
                        .entity(teacherDTO)
                        .build();
            } else {
                // If for some reason the teacherDTO is null (e.g., failure to add)
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Teacher could not be added.")
                        .build();
            }

        } catch (TeacherServiceException e) {
            // Handle any service layer exceptions
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Server Error: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            // Catch any other unexpected errors
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Unexpected error occurred: " + e.getMessage())
                    .build();
        }
    }
}
