package com.thoughtWork.trains.controller;

import com.thoughtWork.trains.service.ITrainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
@RestController
@RequestMapping("/trainInfo")
public class TrainInfoController {
    @Autowired
    private ITrainInfoService trainInfoService;

    /**
     * /distance/A-C-E
     *
     * @param route
     * @return
     */
    @GetMapping("/distance/{route}")
    public String calcRouteDistance(@PathVariable("route") String route) {
        return trainInfoService.calcRouteDistance(route, false);
    }

    /**
     * /shortest/distance/C-C
     *
     * @param route
     * @return
     */
    @GetMapping("/shortest/distance/{route}")
    public String calcRouteShortestDistance(@PathVariable String route) {
        return trainInfoService.calcRouteDistance(route, true);
    }

    /**
     * /trips/CC/maximum/4
     *
     * @param trip
     * @param rule
     * @param limit
     * @return
     */
    @GetMapping("/trips/{trip}/{rule}/{limit}")
    public String calcTripNumber(@PathVariable String trip, @PathVariable String rule, @PathVariable int limit) {
        return trainInfoService.calcTripNumber(trip, rule, limit);
    }
}
