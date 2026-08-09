package top.charles;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Boss {

    // 总额：常量，序列化/反序列化都参与
    @Expose
    private double total;

    // 花费：会计告诉的，从 JSON 读，不序列化出去
    @Expose(serialize = false, deserialize = true)
    private double cost;

    // 利润：自己算的，序列化给外面看，不从 JSON 读
    @Expose(serialize = true, deserialize = false)
    private double profit;

    public Boss(double total) {
        this.total = total;
    }
    // 这个一定要写
    public double getProfit() {
        this.profit = total - cost;
        return profit;
    }
}