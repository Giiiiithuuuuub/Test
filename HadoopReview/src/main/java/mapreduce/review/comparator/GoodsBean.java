package mapreduce.review.comparator;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-03 21:18
 */
public class GoodsBean implements WritableComparable<GoodsBean> {

    private String id;
    private Double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public GoodsBean() {
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeDouble(price);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.id = in.readUTF();
        this.price = in.readDouble();
    }

    @Override
    public String toString() {
        return id + "\t" + price;
    }

    @Override
    public int compareTo(GoodsBean o) {
        if (this.id.equals(o.id)){
            return -Double.compare(this.price,o.price);
        }else {
            return this.id.compareTo(o.id);
        }
    }
}
