package weather.forecast.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import weather.forecast.model.Weather;
import weather.forecast.repository.WeatherRepo;

@Service
public class WeatherService {

    private WeatherRepo weatherRepo;

    public WeatherService(WeatherRepo weatherRepo) {
        this.weatherRepo = weatherRepo;
    }

    /**
     * findById
     * @param id int
     * @return
     */
    public Mono<Weather> findById(Integer id) {
        return Mono.justOrEmpty(weatherRepo.findById(id));
    }

    public Flux<Weather> all() {
        return Flux.fromIterable(weatherRepo.all());
    }

    /**
     * метод, который вернет город с максимальной температурой. /hottest
     * @return
     */
    public Mono<Weather> findByHottest() {
        return Mono.justOrEmpty(weatherRepo.hottest());
    }

    /**
     * cityGreatThen int max temperature
     * @param maxTemp
     * @return
     */
    public Flux<String> cityGreatThen(int maxTemp) {
        return Flux.fromIterable(weatherRepo.cityGreatThen(maxTemp));
    }
}
