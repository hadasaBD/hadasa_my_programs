package com.company;

import java.io.File;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
	    //Dog d = new Dog();
        //Dog d1 = Dog.getDog();
        //Dog d2 = Dog.getDog();

        /*
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape s = shapeFactory.getShape("circle");
        s.draw();
        */

        Shape circle = new Circle();
        Shape redCircle = new RedShapeDecorator(circle);

        Shape rectangle = new Rectangle();
        Shape redRectangle = new RedShapeDecorator(rectangle);


        circle.draw();
        redCircle.draw();



    }
}
class MotionSensor{

    private MotionListener listener;

    public void setListener(MotionListener listener) {
        this.listener = listener;
    }

    public void detectMotion(){
        if(listener != null)
            listener.motionDetected(123);
    }

    interface MotionListener{
        void motionDetected(int sensorId);
    }

}

class Dog{

    private Dog(){
        System.out.println("in Dog's constructor");
    }

    private static Dog d = null;

   public static Dog getDog(){
       if(d == null)
           d = new Dog();
       return d;
   }

}

interface Shape{
    void draw();
}
class Circle implements Shape{

    @Override
    public void draw() {
        System.out.println("draw Circle");
    }
}
class Square implements Shape{

    @Override
    public void draw() {
        System.out.println("draw Square");
    }
}

class Rectangle implements Shape{

    @Override
    public void draw() {
        System.out.println("draw Rectangle");
    }
}

class ShapeFactory extends AbstractFactory{
    @Override
    Color getColor(String color) {
        return null;
    }

    public Shape getShape(String shapeType){
        if(shapeType == null)
            return null;
        switch (shapeType){
            case "circle":
                return new Circle();
            case "rectangle":
                return new Rectangle();
            case "square":
                return new Square();
            default:
                return null;
        }
    }
}

abstract class ShapeDecorator implements Shape{

    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
    }
}

class RedShapeDecorator extends ShapeDecorator{

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }


    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border color: red");
    }
}

interface Color{
    void fill();
}

class Red implements Color{

    @Override
    public void fill() {
        System.out.println("filling in red");
    }
}
class Green implements Color{
    @Override
    public void fill() {
        System.out.println("filling in green");
    }
}
class Blue implements Color{
    @Override
    public void fill() {
        System.out.println("filling in blue");
    }
}
abstract class AbstractFactory{
    abstract Color getColor(String color);
    abstract Shape getShape(String shape);
}

class ColorFactory extends AbstractFactory{

    @Override
    Color getColor(String color) {
        if(color == null)
            return null;
        switch (color) {
            case "red":
                return new Red();
            case "blue":
                return new Blue();
            case "green":
                return new Green();
            default:
                return null;
        }
    }

    @Override
    Shape getShape(String shapeType) {
        return null;
    }
}

class FactoryProducer{
    public static AbstractFactory getFactory(String type){
        switch (type){
            case "shape":
                return new ShapeFactory();
            case "color":
                return new ColorFactory();
            default:
                return null;
        }

    }
}
interface EncryptionStrategy{
    int encrypt(File input, File Output);
}

class CaesarEncryption implements EncryptionStrategy{

    @Override
    public int encrypt(File input, File Output) {
        return 123;
    }
}

class RSAencryption implements EncryptionStrategy{

    @Override
    public int encrypt(File input, File Output) {
        return 456;
    }
}
class TripleDES implements EncryptionStrategy{

    @Override
    public int encrypt(File input, File Output) {
        return 345;
    }
}

class EncryptionContext{
    private EncryptionStrategy encryptionStrategy;

    public EncryptionContext(EncryptionStrategy encryptionStrategy) {
        this.encryptionStrategy = encryptionStrategy;
    }

    public int encrypt(File input, File output){
        return encryptionStrategy.encrypt(input, output);
    }
}

interface CalculateStrategy{
    int calculate(int x, int y);
}

class CalculateAddition implements CalculateStrategy{

    @Override
    public int calculate(int x, int y) {
        return x + y;
    }
}
class CalculateSubtraction implements CalculateStrategy{

    @Override
    public int calculate(int x, int y) {
        return x - y;
    }
}
class CalculateMultiplication implements CalculateStrategy{

    @Override
    public int calculate(int x, int y) {
        return x * y;
    }
}
class CalculationDivision implements CalculateStrategy{

    @Override
    public int calculate(int x, int y) {
        if(y != 0)
            return x / y;
        throw new ArithmeticException("y is zero");
    }
}

class CalculateContext{
    private CalculateStrategy strategy;

    public CalculateContext(CalculateStrategy strategy) {
        this.strategy = strategy;
    }

    public int doCalculation(int x, int y){
        return strategy.calculate(x, y);
    }
}