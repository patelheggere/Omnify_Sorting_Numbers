package com.example.patelheggere.omnifysortingnumbers.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class SortingService extends Service {

    private final IBinder mBinder = new MyBinder();
    private int[] tempMergArr;
    private int[] array;

    public SortingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public int[] QuickSort(int [] inputArr)
    {
        if (inputArr == null || inputArr.length == 0) {
            return null;
        }
        this.array = inputArr;
        int length = inputArr.length;
        quickSort(0, length - 1);
        return array;
    }

    public int[] MergeSort(int[] array2)
    {
        int length = array2.length;
        array = array2;
        tempMergArr = new int[length];
        doMergeSort(0, length - 1);
        return array;
    }

    private void doMergeSort(int lowerIndex, int higherIndex)
    {
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            doMergeSort(lowerIndex, middle);
            doMergeSort(middle + 1, higherIndex);
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }
    private void mergeParts(int lowerIndex, int middle, int higherIndex)
    {

        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }

    }

    private void quickSort(int lowerIndex, int higherIndex) {

        int i = lowerIndex;
        int j = higherIndex;
        int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                i++;
                j--;
            }
        }
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }

    private void exchangeNumbers(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public class MyBinder extends Binder{
        public SortingService getService()
        {
            return SortingService.this;
        }
    }
}
