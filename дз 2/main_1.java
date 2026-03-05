public class ExpressionEvaluator {
    public static void main(String[] commandLineArgs) {
        String calculation1 = "A B +";
        System.out.println("A+B: " + MathProcessor.compute(calculation1, 8, 3)); 

        String calculation2 = "A B *";
        System.out.println("A * B: " + MathProcessor.compute(calculation2, 5, 2)); 

        String calculation3 = "A B -";
        System.out.println("A - B: " + MathProcessor.compute(calculation3, 6, 3)); 

        String calculation4 = "A B * A -";
        System.out.println("A * B - A: " + MathProcessor.compute(calculation4, 5, 15)); 
    }
}
