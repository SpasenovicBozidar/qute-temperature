package at.htl.temperature.boundary;


import at.htl.temperature.control.TemperatureRepository;
import at.htl.temperature.entity.Temperature;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.jboss.resteasy.annotations.Form;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
import java.util.stream.Collectors;

@Path("temperature")
public class TemperatureRessource {


    @Inject
    TemperatureRepository repo;


    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance temperature(List<Temperature> temperatures);
    }


    @GET
    public TemplateInstance getTemperatures() {
        return Templates.temperature(repo.listAll());
    }

    @POST
    public TemplateInstance postTemperaturesByMonth(@FormParam("month") String month) {

        List<Temperature> temperatures = repo.listAll();

        temperatures = temperatures
                .stream()
                .filter(
                        temperature -> temperature.getMonth().equals(month)
                )
                .collect(Collectors.toList());


        return Templates.temperature(temperatures);
    }

}
