package com.meli.quasar.service.impl;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.meli.quasar.config.ListSatellites;
import com.meli.quasar.exception.HelpMessageException;
import com.meli.quasar.model.response.PositionResponse;
import com.meli.quasar.service.LocationService;
import com.meli.quasar.util.Constants;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import java.util.stream.DoubleStream;

@Service
public class LocationServiceImpl implements LocationService {

    private final ListSatellites listSatellites;

    public LocationServiceImpl(ListSatellites listSatellites) {
        this.listSatellites = listSatellites;
    }

    @Override
    public PositionResponse getLocation(double[] distances) {
        try{
            TrilaterationFunction trilaterationFunction = new TrilaterationFunction(getPositions(), distances);
            NonLinearLeastSquaresSolver nSolver = new NonLinearLeastSquaresSolver(trilaterationFunction, new LevenbergMarquardtOptimizer());
            double[] positions = nSolver.solve().getPoint().toArray();
            return new PositionResponse(positions[0], positions[1]);
        }catch (Exception e){
            throw new HelpMessageException(Constants.EXCEPTION_POSITION);
        }
    }

    private double[][] getPositions(){
        return listSatellites.getList().stream()
                .map(data->new double[]{data.getPositionX(),data.getPositionY()})
                .toArray(double[][]::new);
    }
}
