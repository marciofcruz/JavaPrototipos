package br.marciofcruz.apsmetodosordenacao.testes;

import java.util.Arrays;


class RadixSort
{
    public static void radix_sort_uint(int[] a, int bits)
    {
        int[] b = new int[a.length];
        int[] b_orig = b;

        int rshift = 0;
        for (int mask = ~(-1 << bits); mask != 0; mask <<= bits, rshift += bits) {
            int[] cntarray = new int[1 << bits];
            for (int p = 0; p < a.length; ++p) {
                int key = (a[p] & mask) >> rshift;
                ++cntarray[key];
            }

    		for (int i = 1; i < cntarray.length; ++i)
                        cntarray[i] += cntarray[i-1];

            for (int p = a.length-1; p >= 0; --p) {
                int key = (a[p] & mask) >> rshift;
                --cntarray[key];
                b[cntarray[key]] = a[p];
            }

            int[] temp = b; b = a; a = temp;
        }

        if (a == b_orig)
            System.arraycopy(a, 0, b, 0, a.length);
    }
    public static void main(String[] args)
    {
        int[] a = {
            123,432,654,3123,654,2123,543,131,653,123,
            533,1141,532,213,2241,824,1124,42,134,411,
            491,341,1234,527,388,245,1992,654,243,987};

        System.out.println("Before radix sort:");
        System.out.println(Arrays.toString(a));

        radix_sort_uint(a, 4);

        System.out.println("After radix sort:");
        System.out.println(Arrays.toString(a));
    }
}

