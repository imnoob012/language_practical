package com.forgeon.membermanagement.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class AllTableDto {

    // ユーザーテーブル
    private List<Integer> userIds = new ArrayList<>();
    private List<String> userNames = new ArrayList<>();
    private List<String> roles = new ArrayList<>();

    // スキルテーブル
    private List<String> skillNames = new ArrayList<>();
    private List<String> skills = new ArrayList<>();
    private List<Integer> skillIds = new ArrayList<>();
    
    // コンストラクタ
    public AllTableDto() {}
}