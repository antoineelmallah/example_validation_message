package com.example.validation.messages;

import com.example.validation.beans.Brand;
import com.example.validation.beans.Car;
import com.example.validation.beans.Model;
import com.example.validation.beans.Person;
import com.example.validation.messages.severity.ErrorGroup;
import com.example.validation.messages.severity.WarningGroup;
import com.example.validation.validation.wrapper.CarDriversAndOwnersValidationWrapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MessagesProcessorTest {

    private static List<Car> cars;

    @BeforeAll
    static void setup() {
        cars = new ArrayList<>();
        cars.add(Car.builder()
                        .line(1)
                .build());
        cars.add(Car.builder()
                .brand(Brand.builder().build())
                .model(Model.builder().build())
                .drivers(List.of(
                        Person.builder()
                            .birthdayDate(LocalDate.now())
                            .build(),
                        Person.builder()
                                .birthdayDate(LocalDate.now())
                                .build()
                        ))
                .owners(List.of(Person.builder()
                        .cpf("12345")
                        .build()))
                .line(2)
                .build());
        cars.add(Car.builder()
                .model(Model.builder().build())
                .drivers(Collections.emptyList())
                .owners(Collections.emptyList())
                .line(3)
                .build());
    }

    @Test
    void processErrors() {
        List<Message> messages = MessagesProcessor.process(cars, ErrorGroup.class);
        assertThat(messages)
                .isNotNull()
                .hasSize(16)
                .allMatch(message -> MessageType.ERROR.equals(message.getType()));
    }

    @Test
    void processWarnings() {
        List<Message> messages = MessagesProcessor.process(cars, WarningGroup.class);
        assertThat(messages)
                .isNotNull()
                .hasSize(6)
                .allMatch(message -> MessageType.WARNING.equals(message.getType()));
    }

    @Test
    void processWrapperWarnings() {
        var wrapperCars = cars.stream()
                .map(CarDriversAndOwnersValidationWrapper::new)
                .toList();
        List<Message> messages = MessagesProcessor.process(wrapperCars, WarningGroup.class);
        assertThat(messages)
                .isNotNull()
                .hasSize(1)
                .allMatch(message -> MessageType.WARNING.equals(message.getType()))
                .extracting(Message::getMessage)
                .contains("There are more drivers than owners.");
    }

}