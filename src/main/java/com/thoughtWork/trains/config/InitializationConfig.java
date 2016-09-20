package com.thoughtWork.trains.config;

import com.thoughtWork.trains.domain.TripNode;
import org.springframework.boot.ApplicationArguments;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.thoughtWork.trains.util.RouteNodesUtil.initialize;

/**
 * Created by Administrator on 2016/9/21 0021.
 */

public class InitializationConfig {
    private List<TripNode> tripNodeList = new ArrayList<>();

    private String graph = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

    public InitializationConfig(ApplicationArguments arguments) {
        Arrays.stream(arguments.getSourceArgs()).forEach(argument -> {
            if (new File(argument).exists()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(argument)));
                if (br != null) {
                    try {
                        String temp = null;
                        while ((temp = br.readLine()) != null) {
                            tripNodeList.addAll(initialize(temp));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ;
                    }
                }
            }
        });

        if (tripNodeList.isEmpty()) {
            tripNodeList = initialize(graph);
        }
    }

    public List<TripNode> getTripNodeList() {
        return tripNodeList;
    }
}
