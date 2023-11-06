package org.lib.minor1.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CreateReturnTxnRequest {
    private String studentContact;
    private String bookNo;

}
