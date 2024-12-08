package gr.perisnik.cj.schoolapprest.rest;

import gr.perisnik.cj.schoolapprest.dto.CourseDTO;
import gr.perisnik.cj.schoolapprest.service.ICourseService;
import gr.perisnik.cj.schoolapprest.service.exceptions.CourseServiceException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

@Path("/courses")
public class CourseRestController {

    @Inject
    private ICourseService courseService;

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(MultivaluedMap<String, String> form, @Context UriInfo uriInfo) {
        try {
            CourseDTO courseDTO = new CourseDTO(
                    0,
                    form.getFirst("name"),
                    form.getFirst("description"),
                    Integer.parseInt(form.getFirst("credits")),
                    0
            );

            courseDTO = courseService.addCourse(courseDTO);

            if (courseDTO != null) {
                // Return 200 OK with the created CourseDTO
                UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
                return Response.created(uriBuilder.path(String.valueOf(courseDTO.getId())).build())
                        .entity(courseDTO)
                        .build();
            } else {
                // If for some reason the courserDTO is null (e.g., failure to add)
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Course could not be added.")
                        .build();
            }
        } catch (CourseServiceException e) {
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
