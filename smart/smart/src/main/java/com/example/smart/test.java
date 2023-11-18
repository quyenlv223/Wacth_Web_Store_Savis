    package com.example.smart;

    import java.util.Scanner;

    public class test {

        public static long check(long x)
        {
            if (x == 0) return 1;
            long d = 1;
            for (long i = 1; i <= x; i++)
            {
                d = d * i;
            }
            return d;
        }
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            long n;
            n = sc.nextLong();
            if (n == 1){
                System.out.println("0");
            } else {
                long x = 1;
                long d = 1;
                while (x < n && d >= 0)
                {
                    x = x * d;
                    d++;
                }
                long t = 9;
                d = Math.min(t, d);
                String s = "";
                while (n != 0 && d > 0)
                {
                    while (n - check(d) < 0 && d > 0)
                    {
                        d--;
                    }
                    n = n - check(d);
                    char z = (char) ((char)d+48);
                    s +=z;
                }


                String result = "";
                for (int i = s.length() - 1; i >= 0; i--) {
                    result+=s.charAt(i);
                }
                System.out.println(result);
            }



        }
    }
