package com.enuygun.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ElementInfo {
    private String key;
    private String value;
    private String type;
    private int fileNameIndex;

}
