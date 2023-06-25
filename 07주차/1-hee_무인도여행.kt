package com.kotlin.week6

import java.util.LinkedList
import java.util.Queue

class Solution {

    var R:Int = 0;
    var C:Int = 0;
    var land:MutableList<IntArray> = mutableListOf<IntArray>();
    var isVisited:MutableList<BooleanArray> = mutableListOf<BooleanArray>();
    val dx = listOf<Int>(-1,1,0,0);
    val dy = listOf<Int>(0,0,-1,1);

    fun solution(maps: Array<String>): IntArray {
        R = maps.size;
        C = maps[0].length;

        land = MutableList(R) { IntArray(C) }
        isVisited = MutableList(R) { BooleanArray(C) }

        val x  = R-1;
        val y = C-1;

        for(i in 0..x){
            val x:String = maps[i];
            for(j in 0..y){
                val c:Char = x[j];
                land[i][j] = transNumber(c);
                // print("${land[i][j]} \t")
            }
            // println("\n")
        }

        var result:ArrayList<Int> = ArrayList<Int>();


        for(i in 0..x){
            for(j in 0..y){
                if(!isVisited[i][j] && land[i][j]>0){
                    result.add(bfs(intArrayOf(i, j)))
                }
            }
        }
        result.sort()
        return if(result.size>0) result.toIntArray() else intArrayOf(-1)
    }

    fun transNumber(c:Char):Int{
        return if(c=='X') 0 else (c-'0')
    }

    fun bfs(pos:IntArray):Int{

        var que:Queue<IntArray> = LinkedList<IntArray>();
        isVisited[pos[0]][pos[1]] = true;
        que.offer(pos);

        var sum:Int = 0;

        while(!que.isEmpty()){
            val cur:IntArray = que.poll();
            sum += land[cur[0]][cur[1]]

            for(d in 0..3){
                val nx = cur[0] + dx[d];
                val ny = cur[1] + dy[d];
                if(isOut(nx, ny)) continue;
                if(isVisited[nx][ny]) continue;
                if(land[nx][ny]==0) continue;

                isVisited[nx][ny] = true;
                que.offer(intArrayOf(nx, ny));
            }
        }

        return sum;
    }

    fun isOut(dx:Int, dy:Int):Boolean{
        return dx < 0 || dy < 0 || dx >= R || dy >= C
    }


}


fun main(args: Array<String>) {


    val sol = Solution()
    val ans = sol.solution(arrayOf<String>("X591X","X1X5X","X231X", "1XXX1"));

    for(i in ans){
        println(i)
    }

}
