import myArrayListImplementation.MyArrayList;

public class TestArrayList {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(4);
        System.out.println(list.toString());
    }
}
