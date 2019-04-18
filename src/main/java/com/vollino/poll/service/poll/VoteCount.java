package com.vollino.poll.service.poll;

import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * @author Bruno Vollino
 */
public class VoteCount {

    private String option;
    private Long count;

    public VoteCount(String option, Long count) {
        this.option = option;
        this.count = count;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteCount that = (VoteCount) o;
        return Objects.equals(getOption(), that.getOption()) &&
                Objects.equals(getCount(), that.getCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOption(), getCount());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("option", option)
                .add("count", count)
                .toString();
    }
}
