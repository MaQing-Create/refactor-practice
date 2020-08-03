package com.twu.refactoring;

public class Direction {
    private final char direction;

    enum directionChange{

        N('N','E', 'W'),S('S','W','E'),E('E','N','N'),W('W','S','S');

        private char direction;
        private char turnRight;
        private char turnLeft;

        directionChange(char direction, char turnRight,char turnLeft){
            this.direction = direction;
            this.turnLeft = turnLeft;
            this.turnRight = turnRight;
        }

        char getDirection(){
            return this.direction;
        }

        char getTurnRight(){
            return this.turnRight;
        }

        char getTurnLeft(){
            return this.turnLeft;
        }
    }

    public Direction(char direction) {
        this.direction = direction;
    }

    public Direction turnRight() {
        return turnLeftOrRight("Right");
    }

    public Direction turnLeft() {
        return turnLeftOrRight("Left");
    }

    private Direction turnLeftOrRight(String turn){
        for (directionChange directionBeforeChange:directionChange.values()){
            if (directionBeforeChange.getDirection() == direction){
                if (turn.equals("Right")) return new Direction(directionBeforeChange.getTurnRight());
                if (turn.equals("Left")) return new Direction(directionBeforeChange.getTurnLeft());
            }

        }
        throw new IllegalArgumentException();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Direction direction1 = (Direction) object;

        if (direction != direction1.direction) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) direction;
    }

    @Override
    public String toString() {
        return "Direction{direction=" + direction + '}';
    }
}
