package com.java.week1;

import java.util.*;

public class joykst96_표편집 {

    Stack<Row> history;
    Row cur;

    class Row {

        boolean status;
        Row prev;
        Row next;

        Row() {
            status = true;
        }

        void up(int n) {
            for (int i = 0; i < n; ++i) {
                Row start = cur;
                while (cur.prev != null) {
                    if (cur.prev.status) {
                        cur = cur.prev;
                        break;
                    }
                    cur = cur.prev;
                }
                if (cur.prev == null && !cur.status) {
                    cur = start;
                    break;
                }
            }
        }

        void down(int n) {
            for (int i = 0; i < n; ++i) {
                Row start = cur;
                while (cur.next != null) {
                    if (cur.next.status) {
                        cur = cur.next;
                        break;
                    }
                    cur = cur.next;
                }
                if (cur.next == null && !cur.status) {
                    cur = start;
                    break;
                }
            }
        }

        void clear() {
            Row start = cur;
            cur.status = false;
            history.push(cur);
            down(1);
            if (cur == start) up(1);
        }

        void restore() {
            history.pop().status = true;
        }

        Row next(Row next) {
            this.next = next;
            next.prev = this;
            return next;
        }

        String show() {
            StringBuilder sb = new StringBuilder();
            Row now = this;
            while (now.next != null) {
                sb.append(now.next.status ? "O" : "X");
                now = now.next;
            }
            return sb.toString();
        }

    }

    public String solution(int n, int k, String[] cmd) {
        history = new Stack<>();
        cur = null;
        Row pyo = new Row();
        Row head = pyo;
        for (int i = 0; i < n; ++i) {
            pyo = pyo.next(new Row());
            if (i == k) {
                cur = pyo;
            }
        }
        for (String command: cmd) {
            switch (command.charAt(0)) {
                case 'U': pyo.up(Integer.parseInt(command.substring(2))); break;
                case 'D': pyo.down(Integer.parseInt(command.substring(2))); break;
                case 'C': pyo.clear(); break;
                case 'Z': pyo.restore(); break;
            }
        }
        return head.show();
    }

    public static void main(String[] args) {
        joykst96_표편집 pg = new joykst96_표편집();
        System.out.println(pg.solution(
                8,
                2,
                List.of("D 2",
                        "C",
                        "U 3",
                        "C",
                        "D 4",
                        "C",
                        "U 2",
                        "Z",
                        "Z"
                ).toArray(String[]::new)
        ));
        System.out.println(pg.solution(
                8,
                2,
                List.of("D 2",
                        "C",
                        "U 3",
                        "C",
                        "D 4",
                        "C",
                        "U 2",
                        "Z",
                        "Z",
                        "U 1",
                        "C"
                ).toArray(new String[0])
        ));
    }
}
