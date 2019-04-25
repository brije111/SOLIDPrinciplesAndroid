package com.example.solidprinciplesandroid.lsp;


public class LSP {
    // violation of Liskov's Substitution principle

    public interface Car {
        public void startEngine();
    }

    public class Ferrari implements Car {

        @Override
        public void startEngine() {
            //logic ...
        }
    }

    public class Tesla implements Car{
        boolean IsCharged;
        @Override
        public void startEngine() {
            if (!IsCharged)
                return;
            //logic ...
        }

        public void TurnOnCar() {
            //logic
        }
    }
    // Make the call
    public void letStartEngine(Car car) {
        car.startEngine();
    }

    /*
    As you can see in the code above, there are two classes of cars. One fuel car and one electric car.
    The electric car can only start if it’s charged .
    The LetStartEngine method will not work if a car is electric and not charged.
    This breaks the LSP principle since it must be Charged to be able to start as the IsCharged (which also is part of the contract) won’t be set as in the base class.
     */

    //Solution
    //Modified
    // Make the call
    public void LetStartEngine(Car car) {
        if (car instanceof Tesla)
            ((Tesla)car).TurnOnCar();
        car.startEngine();
    }

    /*
    But this violates the Open/Closed Principle,
    so the proper way is to automatically turn on the car in the StartEngine method like below:
     */
    public class TeslaUpdated implements Car{
        boolean IsCharged;
        @Override
        public void startEngine() {
            if (!IsCharged)
                TurnOnCar();
            //logic ...
        }

        public void TurnOnCar() {
            //logic
        }
    }
}
