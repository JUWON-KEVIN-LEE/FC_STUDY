package com.immymemine.kevin.remotebbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int niceSubsequence(int[] a) {
        int result = 0;
        boolean temp = true;
        Arrays.sort(a);
        for(int i=0; i<a.length; i++) {
            for(int j=i; j<i+a[i]-1; j++) {
                if( !(a[j+1] - a[j] == 1) ){
                    temp = false;
                }
            }
        }

        return result;
    }

}
