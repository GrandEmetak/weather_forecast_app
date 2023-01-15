package weather.forecast.repository;

import org.springframework.stereotype.Repository;

import weather.forecast.model.Weather;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class WeatherRepo {

    private final Map<Integer, Weather> weathers = new ConcurrentHashMap<>();

    {
        weathers.put(1, new Weather(1, "Msc", 20));
        weathers.put(2, new Weather(2, "SPb", 17));
        weathers.put(3, new Weather(3, "Bryansk", 23));
        weathers.put(4, new Weather(4, "Smolensk", 12));
        weathers.put(5, new Weather(5, "Kiev", 31));
        weathers.put(6, new Weather(6, "Minsk", 35));
    }

    public Weather findById(Integer id) {
        return weathers.get(id);
    }

    public List<Weather> all() {
        var t = weathers.values().stream().collect(Collectors.toList());
        return t;
    }

    /**
     * вернуть максимальную температуру среди городов
     *
     * @return
     */
    public int findByHotTemprature() {
        var rsl = all();
        var max = rsl.stream()
                .map(weather -> weather.getTemperature())
                .max(Comparator.naturalOrder()).get();
        return max;
    }

    /**
     * city with the highest temperature
     *
     * @return
     */
    public Weather hottest() {
        Comparator<Weather> employeeNameComparator1
                = Comparator.comparingInt(Weather::getTemperature).reversed();

        Weather rsl = null;
        var list = all();
        list.sort(employeeNameComparator1);

        return list.get(0);
    }

    /**
     * cityGreatThen int max temperature
     *
     * @param max temperature
     * @return List<String> citys name
     */
    public List<String> cityGreatThen(int max) {
        List<String> citys = new ArrayList<>();
        var rsl = all();
        citys = rsl.stream()
                .filter(weather -> weather.getTemperature() > max)
                .map(weather -> weather.getCity()
                        .replaceAll(weather.getCity(), weather.getCity() + System.lineSeparator()))
                .collect(Collectors.toList());
        return citys;
    }
}
