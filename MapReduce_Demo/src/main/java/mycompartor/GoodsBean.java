package mycompartor;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-18 20:15
 */
public class GoodsBean implements WritableComparable<GoodsBean> {
    //0000001	Pdt_01	222.8

    private String id;
    private String pdt;
    private Double price;

    public GoodsBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPdt() {
        return pdt;
    }

    public void setPdt(String pdt) {
        this.pdt = pdt;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + "\t" + pdt + "\t" + price;
    }


    @Override
    public int compareTo(GoodsBean o) {

        return this.id.compareTo(o.id) == 0 ? -this.price.compareTo(o.price) : this.id.compareTo(o.id);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(pdt);
        out.writeDouble(price);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.id = in.readUTF();
        this.pdt = in.readUTF();
        this.price = in.readDouble();
    }
}
