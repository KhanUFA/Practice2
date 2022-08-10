package practice2;

public class Place {
    int code1;
    int code2;
    int code3;
    int code4;
    String name;
    String status;

    public Place(int code1, int code2, int code3, int code4, String name) {
        this.code1 = code1;//Регион
        this.code2 = code2;//Район
        this.code3 = code3;//
        this.code4 = code4;
        this.name = name;
    }

    public int getCode1() {
        return code1;
    }

    public int getCode2() {
        return code2;
    }

    public int getCode3() {
        return code3;
    }

    public int getCode4() {
        return code4;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
