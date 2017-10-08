public class Demo {
    public static void main(String args[]) {
        int sum=0,mul=1;
        for(int i=1;i<=10;i++) {
            for(int j=i;j<=i;j++) {
                mul=mul*j;
            }
            sum=sum+mul;
        }
        System.out.println(sum);
    }
}
