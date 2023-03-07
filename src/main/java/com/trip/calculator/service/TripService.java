package com.trip.calculator.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class TripService {

    private static HashMap<Integer, Double> distances = new HashMap<>();
    private static HashMap<String, Integer> ids = new HashMap<>();

    private void parseJSON() {
        try (FileReader reader = new FileReader("src/main/resources/interchanges.json")) {
            JSONObject mainObj = (JSONObject) new JSONParser().parse(reader);

            JSONArray jsonArray = new JSONArray();
            jsonArray.add(mainObj.get("locations"));

            Iterator<Map.Entry> iterator = ((Map) jsonArray.iterator().next()).entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry jKeyVal = iterator.next();

                JSONObject jInfo = (JSONObject) jKeyVal.getValue();

                String name = (String) jInfo.get("name");

                JSONArray array = (JSONArray) jInfo.get("routes");

                JSONObject jobj = (JSONObject) array.get(0);

                double dist = Double.parseDouble(jobj.get("distance").toString());

                int id = Integer.parseInt((String) jKeyVal.getKey());

                distances.put(id, dist);
                ids.put(name, id);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public double getDistance(String start, String end) {
        if (ids.get(start) == null || ids.get(end) == null) {
            return 0.0d;
        }

        int id1 = ids.get(start);
        int id2 = ids.get(end);

        if (id1 > id2) {
            int temp = id1;
            id1 = id2;
            id2 = temp;
        }

        double total = 0.0d;

        for (int i = id1; i < id2; i++) {
            if (distances.containsKey(i)) {
                total += distances.get(i);
            }
        }

        return total;
    }

    public double getCost(String start, String end) {
        parseJSON();
        double total = getDistance(start, end);

        return total * 0.25;
    }
}
