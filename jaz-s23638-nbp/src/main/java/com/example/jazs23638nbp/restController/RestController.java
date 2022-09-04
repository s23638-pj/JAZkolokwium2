package com.example.jazs23638nbp.restController;

import com.example.jazs23638nbp.entity.Entity;
import com.example.jazs23638nbp.service.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalTime;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private Service service;

    public RestController(Service service) {
        this.service = service;
    }

    /**
     * Endpoint zapisuje wartosc gdybyToDzialalo do bazy
     * @return zwracany jest obiekt Entity
     */
    @GetMapping("/hi")
    public Entity getPage() {
        Entity entityTest = new Entity();
        entityTest.setDeskaRatunku("XD");
        entityTest.setRequestDate(LocalDate.now());
        entityTest.setRequestTime(LocalTime.now());

        //exchangeRates.setStartDate(startDate);

        service.save(entityTest);

        return entityTest;
    }


//    @GetMapping("/goldnow")
//    public String pricenow() {
//        Double averageRate = service.getExchangeData();
//        Entity entity = new Entity();
//        entity.setRequestDate(LocalDate.now());
//        entity.setRequestTime(LocalTime.now());
//        service.save(entity);
//        String str = "Sredni kurs dla złota teraz to ";
//        return str;
//    }

    /**
     * Endpoint zapisuje srednia cene zlota w podanym zakresie czasu
     * @param startDate Data poczatkowa
     * @param endDate Data koncowa
     * @return zwracany jest obiekt Entity
     */
    @GetMapping("/gold/{startDate}/{endDate}")
    public String adres(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        Double averageRate = service.getExchangeData(startDate, endDate);//String.valueOf(LocalDate.of(2021, 11, 11)));

//        if (averageRate != null) {
        Entity entity = new Entity();
        entity.setStartDate(startDate);
        entity.setRequestDate(LocalDate.now());
        entity.setRequestTime(LocalTime.now());
        entity.setRate(averageRate);
        //exchangeRates.setStartDate(startDate);


        service.save(entity);

        String str = "Sredni kurs dla złota od " + startDate + " do " + endDate +
                " to " + averageRate;
        return str;

//        } else {
//            return "Something went wrong";
//
//
//        }
    }


    /**
     * Endpoint pobiera z bazy obiekt o podanym id
     * @param id id elementu w bazie
     * @return zwracany jest obiekt Entity
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Entity> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getID(id));
    }

}
