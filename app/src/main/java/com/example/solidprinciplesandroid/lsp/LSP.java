package com.example.solidprinciplesandroid.lsp;

//Example -1
interface Bird{
    void fly();
}

class Duck implements Bird{
    @Override
    public void fly() { }
}

class Ostrich implements Bird{
    @Override
    public void fly() { }
}
//The code looks fine but if we see the behavior, Duck can fly but Ostrich can't.
// So It's voilates the Liskov principle. Child class breaks the parent class type definition because of fly() method.
//Here is the solution
interface BirdUpdated{
    //properties of bird class
}
interface FlyingBird extends BirdUpdated{
    void fly();
}
class DuckUpdated implements FlyingBird{
    @Override
    public void fly() { }
}
class OstrichUpdated implements BirdUpdated{ }

//we made a new interface FlyingBird by extending Bird and moved the method fly() in this new interface.

//Example -2
class Rectangle{
    int height;
    int width;

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}

class Square extends Rectangle{
    //implementation of square
    int side;

    public void setSide(int side) {
        this.side = side;
    }
}

//Here Both Rectangle & Square both got many common properties. But there is change in behavior.
//Here he can set height & width differently on square object by calling
// setHeight() & setWidth() methods but in behavior it's not true,
// Square must have same height & width.
//To Overcome this problem, we can do this

class RectangleUpdated{
    private int height;
    private int width;

    public RectangleUpdated(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
class SquareUpdated extends RectangleUpdated{
    //implementation of square
    private int side;

    public SquareUpdated(int side) {
        super(side, side);
    }
    public int getSide() {
        return side;
    }
}

//Example -3
//from a 2D game project
class Base{
    int tiles[][];
    int height;
    int width;

    //methods
    void addTile(int x, int y){}
    void getUnit(int x, int y){}
}
//Not we want to make this game 3D
class ThreeDGame extends Base{
    int _3dTile[][][];
    int zAxis;

    //method
    void addTile(int x, int y, int z){}
    void getUnit(int x, int y, int z){}
}

//This looks fine for an instance but if we look into this deeply we can call addTile(int x, int y) from the instance of ThreeDGame, And that's wrong.
//For adding tile in ThreeDGame game we must call addTile(int x, int y, int z) not addTile(int x, int y).
//So here clearly it voilates the Liskov principle as child class breaks parent class type definition.
//one of the solution can be
class ThreeDGameModified{
    Base baseX;
    Base baseY;
    Base baseZ;
}


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
