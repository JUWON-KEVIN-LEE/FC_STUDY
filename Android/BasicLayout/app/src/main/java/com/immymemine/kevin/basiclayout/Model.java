package com.immymemine.kevin.basiclayout;

import com.immymemine.kevin.basiclayout.design.Interface.IModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quf93 on 2017-09-15.
 */

public class Model implements IModel{
    List<String> tempExpList = new ArrayList<>();
    List<String> tempResList = new ArrayList<>();
    @Override
    public void update(String expression, String result) {
        tempExpList.add(expression);
        tempResList.add(result); // 앱을 켜서 계산을 하는 도중에 결과값이 계산될 때마다 저장해놓고
    }
    @Override
    public void save(String expressionAndResult) { // 저장 공간 연결하고
        // 실제 저장소에 저장... 다음에 앱을 열었을 때도 볼 수 있도록
    }
}
