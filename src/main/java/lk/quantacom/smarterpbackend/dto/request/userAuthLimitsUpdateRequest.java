package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class userAuthLimitsUpdateRequest {

private Long id;

private String username;

private Double poAuthLimit;

private String poAuthNextUser;

private Double grnAuthLimit;

private String grnAuthNextUser;

 }