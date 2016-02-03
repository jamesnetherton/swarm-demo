package com.jamesnetherton;

import java.io.FileInputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

@Path("/movie")
public class MovieService {

    private static String MOVIE_HOME = System.getProperty("user.home") + "/Videos/";

    @GET
    @Path("/{movieName}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public StreamingOutput streamMovie(@PathParam("movieName") String movieName) {
        return outputStream -> {
            try(FileInputStream inputStream = new FileInputStream(MOVIE_HOME + movieName + ".mp4")) {
                int nextByte = 0;
                while((nextByte  = inputStream.read()) != -1 ) {
                    outputStream.write(nextByte);
                }
            }
        };
    }
}
