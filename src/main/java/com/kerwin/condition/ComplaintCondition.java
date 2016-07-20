package com.kerwin.condition;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by Vega on 2016/5/21 0021.
 * Complaint condition
 */
@Data
@RequiredArgsConstructor
public class ComplaintCondition implements Serializable {
    private static final long serialVersionUID = -3770214563981050015L;
    Long id;
}
