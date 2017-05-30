package com.bnb.volleyball.player;

import com.bnb.volleyball.Position;

public class Positions {
    public Positions(final Position first, final Position second, final Position third,
                     final Position fourth, final Position fifth, final Position sixth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
    }

    private final Position first, second, third, fourth, fifth, sixth;

    public Position getFirst() {
        return first;
    }

    public Position getSecond() {
        return second;
    }

    public Position getThird() {
        return third;
    }

    public Position getFourth() {
        return fourth;
    }

    public Position getFifth() {
        return fifth;
    }

    public Position getSixth() {
        return sixth;
    }
}
