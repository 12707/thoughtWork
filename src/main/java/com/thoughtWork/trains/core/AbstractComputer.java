package com.thoughtWork.trains.core;

import com.thoughtWork.trains.domain.TripNode;
import com.thoughtWork.trains.exception.NoSuchRouteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.thoughtWork.trains.util.RouteNodesUtil.initialize;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public abstract class AbstractComputer<U, T, R> implements IComputer<U, T, R> {

    protected List<TripNode> tripNodes;

    @Value("${input.trips}")
    private String graph;

    public AbstractComputer(){

    }

    public AbstractComputer(ApplicationArguments arguments) {
        Arrays.stream(arguments.getSourceArgs()).forEach(argument -> {
            if (new File(argument).exists()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(argument)));
                if (br != null) {
                    try {
                        String temp = null;
                        while ((temp = br.readLine()) != null) {
                            tripNodes.addAll(initialize(temp));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        if (tripNodes == null || tripNodes.isEmpty()) {
            prepareRoutesData(initialize(graph));
        }
    }

    @Override
    public void prepareRoutesData(List<TripNode> tripNodes) {
        this.tripNodes = Collections.unmodifiableList(tripNodes);
    }

    @Override
    public U compute(T t, R r) {
        throw new NoSuchRouteException();
    }

    @Override
    public U compute(T t) {
        return compute(t, null);
    }
}
