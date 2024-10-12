package com.example.validation.validation.wrapper;

import com.example.validation.beans.Car;
import com.example.validation.beans.LineBasedBean;
import com.example.validation.messages.severity.WarningGroup;
import jakarta.validation.constraints.AssertFalse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CarDriversAndOwnersValidationWrapper implements LineBasedBean {

    private final Car car;

    @AssertFalse(message = "There are more drivers than owners.", groups = WarningGroup.class)
    public boolean isMoreDriversThanOwners() {
        var numDrivers = Optional.ofNullable(car.getDrivers())
                .map(List::size)
                .orElse(0);
        var numOwners = Optional.ofNullable(car.getOwners())
                .map(List::size)
                .orElse(0);
        return numDrivers > numOwners;
    }

    @Override
    public int getLine() {
        return car.getLine();
    }
}
