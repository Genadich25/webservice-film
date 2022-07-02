package com.webservicewithapi.webservicefilm.models;

import java.util.Arrays;
import java.util.Objects;

public class BaseResult {

    private Movie[] results;

    public BaseResult() {
    }

    public Movie[] getResults() {
        return results;
    }

    public void setResults(Movie[] results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseResult that = (BaseResult) o;
        return Arrays.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(results);
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "results=" + Arrays.toString(results) +
                '}';
    }
}
