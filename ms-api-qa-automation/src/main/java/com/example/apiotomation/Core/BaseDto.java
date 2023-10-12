package com.example.apiotomation.Core;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDto implements Serializable {
    private Integer pkId;
}
