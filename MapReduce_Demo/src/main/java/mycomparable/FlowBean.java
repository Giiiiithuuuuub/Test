package mycomparable;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-15 22:37
 */
public class FlowBean implements WritableComparable<FlowBean> {

    private Long upFlow;
    private Long downFlow;
    private Long sumFlow;

    public FlowBean() {
    }

    public Long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Long upFlow) {
        this.upFlow = upFlow;
    }

    public Long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Long downFlow) {
        this.downFlow = downFlow;
    }

    public Long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(Long sumFlow) {
        this.sumFlow = sumFlow;
    }

    public void setSumFlow(){
        this.sumFlow = upFlow + downFlow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlowBean)) return false;

        FlowBean flowBean = (FlowBean) o;

        if (upFlow != null ? !upFlow.equals(flowBean.upFlow) : flowBean.upFlow != null) return false;
        if (downFlow != null ? !downFlow.equals(flowBean.downFlow) : flowBean.downFlow != null) return false;
        return sumFlow != null ? sumFlow.equals(flowBean.sumFlow) : flowBean.sumFlow == null;
    }

    @Override
    public int hashCode() {
        int result = upFlow != null ? upFlow.hashCode() : 0;
        result = 31 * result + (downFlow != null ? downFlow.hashCode() : 0);
        result = 31 * result + (sumFlow != null ? sumFlow.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.upFlow = in.readLong();
        this.downFlow = in.readLong();
        this.sumFlow = in.readLong();
    }

    @Override
    public int compareTo(FlowBean o) {
        return -this.getSumFlow().compareTo(o.getSumFlow());
    }



}
