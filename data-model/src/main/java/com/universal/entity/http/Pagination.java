package com.universal.entity.http;

public class Pagination {
    private int start;
    private int count;

    public Pagination(int start, int count) {
        super();
        this.start = start;
        this.count = count;
    }

    public Pagination() {
        super();
    }

    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

}
