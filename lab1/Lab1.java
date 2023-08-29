/*
*  Laboratory project #1
*  Kseniya Arbatova, 11.09.22
*/

public class Lab1 {
    public static void main(String args[]) {
        /* create c, an array of odd numbers */
        long с[] = {3,5,7,9,11,13,15,17,19,21};
        /* create x, an array of random values */
        double x[] = new double[18];
        int MIN = -8;
        int MAX = 6;
        for(int i = 0; i < 18; i++){
            double a = Math.random()*(MAX - MIN) + MIN;
            x[i] = a;
        }

        double [][] d = new double[10][18];
        /* nested loop to fill array y*/
        for (int i = 0; i < 10; i++){
            for(int j = 0; j<18; j++){
                /* count arg here to use later in math expressions */
                double arg = (x[j] - 1)/14;
                if(с[i] == 5){
                    d[i][j] = Math.atan(Math.pow(Math.E, Math.cbrt(-Math.sin(x[j]))));

                } else if (с[i] == 7 || с[i] == 9 || с[i] == 11 || с[i] == 15 || с[i] == 21) {
                    d[i][j] = Math.tan(Math.log(Math.acos((arg))));

                } else {
                    d[i][j] = Math.cbrt(Math.log(Math.acos(Math.pow(arg, 2))));

                }
            }
        }

        /* enhanced for loop goes through each line in y and helps us output a table */
        for (double[] doubles : d) {
            for (int j = 0; j < d[0].length; j++) {
                System.out.printf("%,.4f  ", doubles[j]);
            }
            System.out.print("\n");
        }
    }
}