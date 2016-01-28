package com.jamesnetherton;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.FileInputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

@Path("/audio")
@Api( value = "audio", description = "Streams music tracks" )
public class AudioService {

    private static String AUDIO_HOME = System.getenv("HOME") + "/Music";

    @GET
    @Path("/track/{trackName}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @ApiOperation(
        value = "Streams an audio track",
        notes = "Returns a stream of bytes for the chosen audio track",
        response = StreamingOutput.class
    )
    public StreamingOutput streamTrack(@ApiParam(name = "trackName", value = "The name of the track to stream", required = true)
                                           @PathParam("trackName") String trackName) {
        return outputStream -> {
            try(FileInputStream inputStream = new FileInputStream(AUDIO_HOME + "trackName" + ".mp3")) {
                int nextByte = 0;
                while((nextByte  = inputStream.read()) != -1 ) {
                    outputStream.write(nextByte);
                }
            }
        };
    }
}
