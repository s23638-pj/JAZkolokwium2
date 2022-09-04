package com.example.jazs23638nbp.service;

import com.example.jazs23638nbp.entity.Entity;
import com.example.jazs23638nbp.repository.Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Scanner;

@org.springframework.stereotype.Service
public class Service {

    private Repository repository;

    public Service(Repository repository){
        this.repository = repository;
    }


    /**
     * Funkcja komunikuje sie z api i liczy srednia cene dla zlota z przedziału czasu
     * @param startDate - od kiedy ma pobrac dane
     * @param endDate - do kiedy ma pobrac dane
     * @return - zwraca stringa z danymi z klady Entity
     */

    public Double getExchangeData(LocalDate startDate, LocalDate endDate) {
        URL url;
        try {
            url = new URL("http://api.nbp.pl/api/cenyzlota/" + startDate + "/" + endDate);
//            url = new URL("http://api.nbp.pl/api/cenyzlota");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
//            responseCode = 404;
            if (responseCode != 200) {
                if (responseCode == 400) {
                    throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
                }
                if (responseCode == 404) {
                    throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
                }
                if (responseCode == 500) {
                    throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
                }

            } else {
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                    System.out.println(inline);
                }


                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray data_obj = (JSONArray) parser.parse(inline);
//                System.out.println(inline);
                Double sum = 0.0;
                for(int i = 0; i < data_obj.size(); i++) {
//                    JSONArray rates = (JSONArray) data_obj.get(i);
//                    //{"data":"2013-11-12","cena":128.67}
                    sum += (double)(((JSONObject)data_obj.get(i)).get("cena"));
                }
                return sum / data_obj.size();
            }
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public double getAverageRate(JSONArray rates) {
        double sum = 0;
        for (int i = 0; i < rates.size(); i++) {
            JSONObject obj = (JSONObject) rates.get(i);
            double wartosc = Double.parseDouble(obj.get("cena").toString());
            sum += wartosc;
        }
        return sum / rates.size();
    }

    public Entity save(Entity entity){
        return repository.save(entity);
    }


    /**
     * Fukcja pobiera z bazy informacje i zwraca je w postaci stringa
     * @param id - id elementu w bazie
     * @return - zwraca stringa z danymi z klady Entity
     */
    public Entity getID(long id) {
        return repository.findById(id).get();
    }



}
