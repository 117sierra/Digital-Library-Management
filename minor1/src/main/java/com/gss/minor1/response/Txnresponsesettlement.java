package com.gss.minor1.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class Txnresponsesettlement {
    private String txnid;
    private Integer settleamount;
}
