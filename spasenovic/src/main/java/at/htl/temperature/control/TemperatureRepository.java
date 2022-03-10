package at.htl.temperature.control;

import at.htl.temperature.entity.Temperature;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Month;

@ApplicationScoped
public class TemperatureRepository implements PanacheRepositoryBase<Temperature,Integer> {
    public void readTemperatureFile(String filename) {

        String location = "";

        try (var s = Files.lines(Path.of(filename))){
                location = s.skip(4)
                        .findFirst()
                        .get()
                        .trim()
                        .split(":")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (var s = Files.lines(Path.of(filename))){
            String finalLocation = location;
            s.skip(70)
                    .forEach(line -> parseTemp(line, finalLocation));

        } catch (IOException e) {
            e.printStackTrace();
        }




        System.out.println(location);
    }

    @Transactional
    public  void parseTemp(String line, String location) {
        String[] splitted = line.split(";");
        int count = 1;



            for (int i = 0; i < 12; i++) {
                for (int j = 1; j < splitted.length; j++) {
                    if (count != 13) {
                        persist(new Temperature(
                                        Integer.parseInt(splitted[0]) * 100 + count,
                                        location,
                                        Month.of(count).toString(),
                                        Double.parseDouble(splitted[j]) / 10,
                                        Integer.parseInt(splitted[0])
                                )
                        );

                        count++;
                    }
                }
            }

    }

}
