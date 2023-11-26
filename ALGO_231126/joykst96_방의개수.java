import java.util.*;

class Solution {
    static class Location {
        int x;
        int y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Location move(int dx, int dy) {
            return new Location(x + dx, y + dy);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Location)) return false;
            return this.x == ((Location) o).x && this.y == ((Location) o).y;
        }
    }
    
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
    
    public int solution(int[] arrows) {
        Map<Location, Set<Location>> map = new HashMap<>();
        Location current = new Location(0, 0);
        int count = 0;
        for (int d: arrows) {
            for (int i = 0; i < 2; ++i) {
                Location next = current.move(dx[d], dy[d]);
                if (!map.containsKey(next)) {
                    map.computeIfAbsent(next, k -> new HashSet<>()).add(current);
                    map.computeIfAbsent(current, k -> new HashSet<>()).add(next);
                } else {
                    if (!map.get(next).contains(current)) {
                        map.get(next).add(current);
                        map.get(current).add(next);
                        ++count;
                    }
                }
                current = next;
            }
        }
        return count;
    }
    
    private static int hash(int x, int y) {
        return Objects.hash(x, y);
    }
}