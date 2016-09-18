package com.thoughtWork.trains.controller;

import com.thoughtWork.trains.response.RouteDistanceResponse;
import com.thoughtWork.trains.service.ITrainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
@Controller("/trainInfo")
public class TrainInfoController {
    @Autowired
    private ITrainInfoService trainInfoService;

    @GetMapping("/{route}/distance")
    public RouteDistanceResponse calcRouteDistance(@PathVariable String route){
        return null;
    }
}
