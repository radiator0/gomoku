package game;

/**
 * Created by Mateusz on 2017-04-14.
 */

public enum Field{
    O(0),
    X(1),
    empty(null);

    Integer value;
    Field(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return value;
    }
}
