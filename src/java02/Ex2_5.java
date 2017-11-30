import java.awt.image.IndexColorModel;

class Ex2_5{
    public static void main(String[] args) {
        System.out.println("2Hello world");
        System.out.println("Hello world2");
        System.out.println("Hello world3");
        System.out.println("Hello world4");
        System.out.println("Hello world5");
        System.out.println("Hello world");
        System.out.println("Hello world6");
        System.out.println("Hello world7");
        System.out.println("ほげ8");
        System.out.println("おらおら");
        System.out.println("おらおらおらおら");
        System.out.println("おらおらおら");
        Test3 t = new Test3();
        t.fuga = 3;
        int[] ora = new int[5];
        System.out.println(ora[5]);//エラー
        System.out.println(t.getFuga()+1);
        System.out.println(t.getHoge());        
    }
}