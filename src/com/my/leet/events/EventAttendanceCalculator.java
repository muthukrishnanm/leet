package com.my.leet.events;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/*
* https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/discuss/1321045/Java-Priority-Queue
* */
public class EventAttendanceCalculator {

    public static int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        Queue<Integer> pq = new PriorityQueue<>(); //priority Queue by events expiry (end time)

        int d = 1, i = 0, n = events.length, res = 0;

        while(i < n || !pq.isEmpty()) {
            //remove exired events that we couldn't attend any more
            while(!pq.isEmpty() && pq.peek() < d) pq.poll();

            //add all the events happening on day d
            while(i < n && events[i][0] == d) {
                pq.offer(events[i++][1]);
            }
            //attend event which expires first
            if(!pq.isEmpty()) {
                pq.poll();
                res++;
            }

            d++;
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] events = {{1,2}, {2,3}, {3,4}, {1,2}};
        System.out.println(maxEvents(events));
    }
}
