public class FizzBuzzArray{
    public static void main(String[] args) {
        int[] fbArray = new int[20];
        for(int i = 0;i< fbArray.length;i++){
            fbArray[i] = i + 1;
        }
        for(int i = 0;i< fbArray.length;i++){
            if(fbArray[i]%15==0){
                System.out.println("FizzBuzz");
            }else if(fbArray[i]%3==0){
                System.out.println("Fizz");
            }else if(fbArray[i]%5==0){
                System.out.println("Buzz");
            }else{
                System.out.println(fbArray[i]);
            }
        }
    }
}