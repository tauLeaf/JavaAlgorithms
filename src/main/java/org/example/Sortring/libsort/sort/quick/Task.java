package org.example.Sortring.libsort.sort.quick;

import org.example.Sortring.libsort.sort.Sorting;

// Жалкая попытка решить упражнения по быстрой сортировке
// 02.02.2026 8:03
public class Task {
    // Решение ChapGPT... Я не смог решить через partition обычный. Сука...
    public static void sort2key(Comparable[] a, int low, int high) {
        int i = low, j = high;
        Comparable v = a[low];
        while (i <= j) {
            if (a[i].compareTo(v) == 0) {
                i++;
            } else {
                Sorting.exch(a, i, j);
                j--;
            }
        }
    }

    private static int mySort2key(Comparable[] a) {
        // Разбиение на a[low .. i-1], a[i], a[i+1 .. high]
        int i = 0, j = a.length;    // Левый и правый индексы просмотра
        Comparable v = a[0];      // Центральный элемент
        while(true) {
            // Просмотр справа, просмотр слева, проверка на завершение и обмен
            while(Sorting.less(a[++i], v)) if(i == a.length-1) break;
            while(Sorting.less(v, a[--j])) if(j == 0) break;
            if(i >= j) break;
            Sorting.exch(a, i, j);
        }
        Sorting.exch(a, 0, j);    // Помещение v = a[j] на своё место
        return j;                   // так, что a[low .. j-1] <= a[j] <= a[j+1 .. high]
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[20];
        for(int i = 0; i < 20; i++) {
            a[i] = (i+1) % 2;
        }

        mySort2key(a);
        System.out.println(a);
    }
}

