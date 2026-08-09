package top.charles;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    @SerializedName(
            value = "ename",
            alternate = {"employee_name"}
    )
    protected String name;

    @SerializedName("sal")
    protected double salary;
}