package upb.webapp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import upb.entity.Libro;
@Path("/libros")
public class LibroWebApp {

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTrackInJSON(Libro libro) {
        String result = "Libro Guardado : " + libro;
        Database b = new Database();
        b.create(libro.getId(), libro.getName(), libro.getAutor());
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(libro)
                .build();
    }


    @GET()
    public String getLibrosUrl(){
        Libro libro = new Libro();
        libro.setName("Enter Sandman");
        libro.setAutor("Metallica");
        libro.setId(5);
        return libro.toString();
    }
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response getTrackInJSON() {
        Libro track = new Libro();
        track.setName("Enter Sandman");
        track.setAutor("Metallica");
        track.setId(5);
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(track)
                .build();
}


//    public  static void main(String [] args) {
//        new Database().create(15, "TESTING", "TESTING");
//        new Database().closeDataBase();
//    }
}
