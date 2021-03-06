/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.seyren.api.jaxrs;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.seyren.core.domain.ParkingLot;

@Path("/")
public interface ParkingLotsResource {

    /**
     * Allows the retrieval of a collection of checks via three distinct algorithms:
     * <ul>
     *     <li>states is non-null and non-empty: will filter all checks by both the states supplied as well as
     *     the value of the enabled parameter.</li>
     *     <li>both fields and regexes are non-null and non-empty: will filter all checks by applying the regex
     *     at index i of regexes to the field at index i of fields.  Length of fields and regexes must be
     *     identical.  If the method parameter "enabled" is specified, then it will override any regex value of
     *     field enabled provided.</li>
     *     <li>default: get all checks as filtered by the presence and value of the enabled parameter.</li>
     * </ul>
     *
     * @param states legal check states @see com.seyren.core.domain.AlertType
     * @param enabled optional, if present, will filter any of the three algorithms
     * @param name (Presently unused)
     * @param fields an ordered list of @see com.seyren.core.domain.Check fields.  If present and non-empty, will
     *               be combined with the correspondingly ordered list of regexes in order to restrict the checks
     *               returned.
     * @param regexes an ordered list of regexes that will be compiled into @java.util.regex.Pattern objects.  If
     *                present and non-empty, will be combined with the correspondingly ordered list of fields in
     *                order to filter the objects returned.
     *
     * @return JSON response containing @see com.seyren.core.domain.Check identified by the parameters supplied.
     */
    @GET
    @Path("/parkinglots")
    @Produces(MediaType.APPLICATION_JSON)
    Response getParkinglots(
                       @QueryParam("username") String username,
                       @QueryParam("coorx") double x,
                       @QueryParam("coory") double y,
                       @QueryParam("radius") double radius);

    @POST
    @Path("/parkinglots")
    @Consumes(MediaType.APPLICATION_JSON)
    Response createParkinglot(ParkingLot pl);
    
    @GET
    @Path("/parkinglots/{plId}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getParkinglot(@PathParam("plId") String plId);

    @GET
    @Path("/reserve")
    @Produces(MediaType.APPLICATION_JSON)
    Response reservePark(
        @QueryParam("username") String username, 
        @QueryParam("parkID") int parkID);


    // @PUT
    // @Path("/parkinglots/{plId}")
    // @Consumes(MediaType.APPLICATION_JSON)
    // @Produces(MediaType.APPLICATION_JSON)
    // Response updateParkinglot(@PathParam("plId") String plId, ParkingLot pl);
    
    // @DELETE
    // @Path("/parkinglots/{plId}")
    // Response deleteParkinglot(@PathParam("plId") String plId);
    
}
