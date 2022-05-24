package com.meli.quasar.service.impl;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.meli.quasar.model.response.PositionResponse;
import com.meli.quasar.service.LocationService;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {

    private double[][] positions = new double[3][2];

    @Override
    public PositionResponse getLocation(double[] distances) {
        positions[0][0] = -500;
        positions[0][1] = -100;

        positions[1][0] = 100;
        positions[1][1] = -100;

        positions[2][0] = 500;
        positions[2][1] = 100;
        TrilaterationFunction trilaterationFunction = new TrilaterationFunction(positions, distances);
        NonLinearLeastSquaresSolver nSolver = new NonLinearLeastSquaresSolver(trilaterationFunction, new LevenbergMarquardtOptimizer());
        double[] positions = nSolver.solve().getPoint().toArray();
        return new PositionResponse(positions[0], positions[1]);
    }
}
