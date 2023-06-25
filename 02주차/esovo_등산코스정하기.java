package day0307;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution_등산코스정하기2 {

	public static void main(String[] args) {
		int[] s = solution(7,
				new int[][] { { 1, 2, 5 }, { 1, 4, 1 }, { 2, 3, 1 }, { 2, 6, 7 }, { 4, 5, 1 }, { 5, 6, 1 }, {6, 7, 1} },
				new int[] { 3, 7 }, new int[] { 1, 5 });
		System.out.println(Arrays.toString(s));
	}

	static class Vertex implements Comparable<Vertex>{
		int vertex, weight;

		public Vertex(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.weight-o.weight;
		}
	}
	
	/*
	 * 다익스트라
	 */
	public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		List<Integer> summitList = new ArrayList<>();
		for(int i=0; i<summits.length; i++) {
			summitList.add(summits[i]-1);
		}
		
		List<Vertex>[] adjList = new ArrayList[n];
		for(int i=0; i<n; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < paths.length; i++) {
			int from = paths[i][0] - 1;
			int to = paths[i][1] - 1;
			int weight = paths[i][2];
			adjList[from].add(new Vertex(to, weight));
			adjList[to].add(new Vertex(from, weight));
		}
		
		int[] D = new int[n];
		Arrays.fill(D, Integer.MAX_VALUE);

		// pq에 시작점 넣기
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		for(int i=0; i<gates.length; i++) {
			D[gates[i]-1] = 0;
			pq.offer(new Vertex(gates[i]-1, D[gates[i]-1]));
		}
		

		while (!pq.isEmpty()) {
			Vertex cur = pq.poll();
			int vertex = cur.vertex;
			int weight = cur.weight;
			
			if(D[vertex] < weight) continue;
			for(int i=0; i<adjList[vertex].size(); i++) {
				int v = adjList[vertex].get(i).vertex;
				int w = Math.max(weight, adjList[vertex].get(i).weight);
				
				// 가지치기 안해주면 시간초과
				if(D[v] <= Math.max(D[vertex], w)) continue;
				
				D[v] = Math.min(D[v], w);
				if(!summitList.contains(v)) {// 산봉우리가 아닌 경우 
					pq.add(new Vertex(v, w));
				}
			}
		}
		
		// intensity가 최소가 되는 시점의 intensity와 산봉우리 구하기
		int summit = 0;
		int intensity = Integer.MAX_VALUE;
		for(int i=0; i<summits.length; i++) {
			if(intensity > D[summits[i]-1]) {
				intensity = D[summits[i]-1];
				summit = summits[i];
			}
			// intensity 같은 경우 번호가 낮은 산봉우리 선택
			else if(intensity == D[summits[i]-1]) { 
				summit = Math.min(summit, summits[i]);
			}
		}
		
		return new int[] {summit, intensity};
	}
}
